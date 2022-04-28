package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserDTO;
import dbc.vemser.refoundapi.entity.ItemEntity;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.entity.UserEntity;
import dbc.vemser.refoundapi.repository.ItemRepository;
import dbc.vemser.refoundapi.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final RefundRepository refundRepository;
    private final ObjectMapper objectMapper;

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public ItemDTO create(Integer idRefund, ItemCreateDTO itemCreate) {
        log.info("Chamada de método:: CREATE ITEM!");
        ItemEntity itemEntity = objectMapper.convertValue(itemCreate, ItemEntity.class);
        RefundEntity r = refundRepository.getById(idRefund);
        itemEntity = setPhoto(itemEntity, itemCreate);
        itemEntity.setIdRefund(idRefund);
        itemEntity.setRefundEntity(r);
        itemEntity.setDate(LocalDate.parse(itemCreate.getDateItem(), FORMATTER));

        ItemEntity itemCreated = itemRepository.save(itemEntity);

//        ItemDTO itemDTO = objectMapper.convertValue(itemCreated, ItemDTO.class);
//        itemDTO.setDateItem(itemCreated.getDate().format(FORMATTER));
        return buildItemDTO(itemCreated);
    }

    public ItemDTO update(Integer id, ItemCreateDTO itemAtt) {
        log.info("Chamada de método:: UPDATE ITEM!");

        ItemEntity itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found!"));


        itemFound.setName(itemAtt.getName());
        itemFound.setDate(LocalDate.parse(itemAtt.getDateItem(), FORMATTER));
        itemFound.setValue(itemAtt.getValue());

        ItemEntity itemEntity = itemRepository.save(itemFound);

        ItemDTO itemDTO = objectMapper.convertValue(itemEntity, ItemDTO.class);
        itemDTO.setDateItem(itemEntity.getDate().format(FORMATTER));
        return itemDTO;
    }

    public ItemDTO list() {
        log.info("Chamada de método:: LIST ITEM!");
        return (ItemDTO) itemRepository.findAll().stream()
                .map(itemEntity -> objectMapper.convertValue(itemEntity, ItemDTO.class))
                .collect(Collectors.toList());
    }

    public ItemDTO delete(Integer id) {
        log.info("Chamada de método:: DELETE ITEM!");
        ItemEntity itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found!"));
        itemRepository.delete(itemFound);
        return objectMapper.convertValue(itemFound, ItemDTO.class);
    }

    private ItemDTO buildItemDTO(ItemEntity item){
        ItemDTO itemDTO = ItemDTO.builder()
                .idItem(item.getIdItem())
                .name(item.getName())
                .value(item.getValue())
                .build();

        if (item.getImage() != null){
            itemDTO.setImageString(Base64.getEncoder().encodeToString(item.getImage()));
        }
        return itemDTO;
    }

    private ItemEntity setPhoto(ItemEntity itemEntity, ItemCreateDTO itemCreate){
        try{
            MultipartFile coverPhoto = itemCreate.getImage();
            if (coverPhoto != null) {
                itemEntity.setImage(coverPhoto.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemEntity;
    }
}
