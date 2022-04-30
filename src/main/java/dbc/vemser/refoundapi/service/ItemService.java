package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemUpdateDTO;
import dbc.vemser.refoundapi.entity.ItemEntity;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.exception.BusinessRuleException;
import dbc.vemser.refoundapi.repository.ItemRepository;
import dbc.vemser.refoundapi.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final RefundRepository refundRepository;

    @Autowired
    private RefundService refundService;

    private final ObjectMapper objectMapper;

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public ItemDTO create(Integer idRefund, ItemCreateDTO itemCreate) {
        log.info("Chamada de método:: CREATE ITEM!");
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(itemCreate.getName());
        itemEntity.setValue(Double.parseDouble(itemCreate.getValue()));
        RefundEntity r = refundRepository.getById(idRefund);
        itemEntity = setPhoto(itemEntity, itemCreate);
        itemEntity.setIdRefund(idRefund);
        itemEntity.setRefundEntity(r);
        itemEntity.setDate(LocalDate.parse(itemCreate.getDateItem(), FORMATTER));

        ItemEntity itemCreated = itemRepository.save(itemEntity);
        refundService.addItemValue(r, itemEntity.getValue());

        return buildItemDTO(itemCreated);
    }

    //TODO - arrumar update
    public ItemDTO update(Integer id, ItemUpdateDTO itemAtt) {
        log.info("Chamada de método:: UPDATE ITEM!");

        ItemEntity itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found!"));


        itemFound.setName(itemAtt.getName());
        itemFound.setDate(LocalDate.parse(itemAtt.getDateItem(), FORMATTER));
        itemFound.setValue(Double.parseDouble(itemAtt.getValue()));

        if (itemAtt.getImage() != null) {
            ItemCreateDTO itemCreateDTO = new ItemCreateDTO();
            itemCreateDTO.setImage(itemAtt.getImage());
            itemFound = setPhoto(itemFound, itemCreateDTO);
        }

        ItemEntity itemEntity = itemRepository.save(itemFound);
        refundService.calculeRefundValue(itemFound.getIdRefund());

        return buildItemDTO(itemEntity);
    }

    public List<ItemDTO> list() {
        log.info("Chamada de método:: LIST ITEM!");
        return itemRepository.findAll().stream()
                .map(this::buildItemDTO)
                .collect(Collectors.toList());
    }

    public ItemDTO getItemById (Integer id) throws BusinessRuleException{
        ItemEntity itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException("Item not found!"));

        return buildItemDTO(itemFound);
    }

    public ItemDTO delete(Integer id) {
        log.info("Chamada de método:: DELETE ITEM!");
        ItemEntity itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found!"));
        itemRepository.delete(itemFound);
        refundService.removeItemValue(itemFound.getIdRefund(), itemFound.getValue());
        return buildItemDTO(itemFound);
    }

    public ItemDTO buildItemDTO(ItemEntity item){

        return ItemDTO.builder()
                .idItem(item.getIdItem())
                .name(item.getName())
                .dateItem(item.getDate().format(FORMATTER))
                .value(item.getValue())
                .imageString(Base64.getEncoder().encodeToString(item.getImage()))
                .build();
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
