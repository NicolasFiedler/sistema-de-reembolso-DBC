package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.ItemCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.ItemDTO;
import dbc.vemser.refoundapi.entity.ItemEntity;
import dbc.vemser.refoundapi.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper objectMapper;


    public ItemDTO create(ItemCreateDTO itemCreate, MultipartFile file) throws Exception {
        log.info("Chamada de método:: CREATE ITEM!");
        itemCreate.setImage(file.getBytes());
        ItemEntity itemEntity = objectMapper.convertValue(itemCreate, ItemEntity.class);
        ItemEntity itemCreated = itemRepository.save(itemEntity);
        ItemDTO itemDTO = objectMapper.convertValue(itemCreated, ItemDTO.class);
        return itemDTO;
    }

    public ItemDTO update(Integer id, ItemCreateDTO itemAtt) {
        log.info("Chamada de método:: UPDATE ITEM!");
        ItemEntity itemFound = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found!"));
        itemFound.setImage(itemAtt.getImage());
        itemFound.setName(itemAtt.getName());
        itemFound.setDate(itemAtt.getDate());
        itemFound.setValue(itemAtt.getValue());
        ItemEntity itemEntity = itemRepository.save(itemFound);
        ItemDTO itemDTO = objectMapper.convertValue(itemEntity, ItemDTO.class);
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
        ItemDTO itemDTO = objectMapper.convertValue(itemFound, ItemDTO.class);
        return itemDTO;
    }
}
