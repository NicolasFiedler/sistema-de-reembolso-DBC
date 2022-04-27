package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.item.ItemDTO;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundDTO;
import dbc.vemser.refoundapi.dataTransfer.refund.RefundUpdateDTO;
import dbc.vemser.refoundapi.dataTransfer.user.UserDTO;
import dbc.vemser.refoundapi.entity.ItemEntity;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.entity.RoleEntity;
import dbc.vemser.refoundapi.entity.UserEntity;
import dbc.vemser.refoundapi.enums.Status;
import dbc.vemser.refoundapi.exception.BusinessRuleException;
import dbc.vemser.refoundapi.repository.RefundRepository;
import dbc.vemser.refoundapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;

    private final ItemService itemService;

    private final ObjectMapper objectMapper;

    private final UserRepository userRepository;

    private final DateTimeFormatter ITEM_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final DateTimeFormatter REFUND_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public RefundDTO create(Integer idUser, RefundCreateDTO refundCreate) {
        log.info("Chamada de método:: CREATE REFUND!");

        RefundEntity refundEntity = objectMapper.convertValue(refundCreate, RefundEntity.class);
        refundEntity.setDate(LocalDateTime.now(ZoneId.of("UTC-03:00")));
        refundEntity.setStatus(Status.ABERTO);

        UserEntity u = userRepository.getById(idUser);
        refundEntity.setUserEntity(u);
        refundEntity.setIdUser(idUser);

        RefundEntity refundCreated = refundRepository.save(refundEntity);

        Double sum = 0.;
        Set<ItemEntity> itemEntitySet = new HashSet<>();
        for (ItemCreateDTO item : refundCreate.getItems()) {
            ItemDTO itemDTO = itemService.create(refundCreated.getIdRefund(), item);
            ItemEntity itemEntity = objectMapper.convertValue(itemDTO, ItemEntity.class);
            itemEntity.setRefundEntity(refundCreated);
            itemEntity.setDate(LocalDate.parse(itemDTO.getDateItem(), ITEM_FORMATTER));
            itemEntitySet.add(itemEntity);
            sum += item.getValue();
        }

        refundCreated.setValue(sum);
        refundCreated.setItemEntities(itemEntitySet);
        refundCreated = refundRepository.save(refundEntity);

        RefundDTO refundDTO = objectMapper.convertValue(refundCreated, RefundDTO.class);
        refundDTO.setDate(refundCreated.getDate().format(REFUND_FORMATTER));
        refundDTO.setItems(itemEntitySet.stream()
                .map(itemEntity -> objectMapper.convertValue(itemEntity, ItemDTO.class))
                .collect(Collectors.toSet()));

        return refundDTO;
    }

    public Page<RefundDTO> list(Integer idUser, Integer requestPage, Integer sizePage) {
        log.info("Chamada de método:: List Refund!");

        UserEntity userEntity = userRepository.getById(idUser);
        Pageable pageable = PageRequest.of(requestPage,sizePage, Sort.by("status").ascending().and(Sort.by("date").descending()));

        return switch (roleToNumeric(userEntity)) {
            case 1 -> refundRepository.findAll(pageable)
                    .map(this::prepareDTO);

            case 2 -> refundRepository.findByStatus(Status.APROVADOG, pageable)
                    .map(this::prepareDTO);

            case 3 -> refundRepository.findByStatus(Status.ABERTO, pageable)//.stream()
                    .map(this::prepareDTO);

            case 4 -> new PageImpl<> (userEntity.getRefundEntities().stream()
                    .map(this::prepareDTO)
                    .collect(Collectors.toList()), pageable, userEntity.getRefundEntities().stream()
                    .map(this::prepareDTO).toList().size());

            default -> null;
        };
    }

    private RefundDTO prepareDTO (RefundEntity refundEntity) {
        RefundDTO refundDTO = objectMapper.convertValue(refundEntity, RefundDTO.class);
        refundDTO.setName(userRepository.getById(refundEntity.getIdUser()).getName());
        refundDTO.setItems(refundEntity.getItemEntities().stream()
                .map(itemEntity -> {
                    ItemDTO itemDTO = objectMapper.convertValue(itemEntity, ItemDTO.class);
                    itemDTO.setDateItem(itemEntity.getDate().format(ITEM_FORMATTER));
                    return itemDTO;
                })
                .collect(Collectors.toSet()));
        refundDTO.setDate(refundEntity.getDate().format(REFUND_FORMATTER));
        return refundDTO;
    }

    private Integer roleToNumeric (UserEntity userEntity) {
        for (RoleEntity role : userEntity.getRoleEntities()) {
            return role.getIdRole();
        }
        return 0;
    }

    //TODO - conferir de quem eh o ticket antes de atualizar
    public RefundDTO update(Integer id, RefundUpdateDTO refundAtt) throws Exception {
        RefundEntity refundFound = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found!"));
        refundFound.setStatus(refundAtt.getStatus());
        refundFound.setTitle(refundAtt.getTitle());
        refundFound.setValue(refundAtt.getValue());
        RefundEntity refundEntity = refundRepository.save(refundFound);
        return objectMapper.convertValue(refundEntity, RefundDTO.class);
    }

    public RefundDTO delete(Integer idRefund, Integer idUser) throws BusinessRuleException {

        RefundEntity refundFounded = refundRepository.findByIdRefundAndIdUserAndStatus(idRefund, idUser, Status.ABERTO)
                .orElseThrow(() -> new BusinessRuleException("Invalid operation"));
        RefundDTO refundDTO = prepareDTO(refundFounded);

        refundRepository.delete(refundFounded);
        return refundDTO;
    }

    public Page<RefundDTO> orderByDate(Integer requestPage, Integer sizePage){
        Pageable pageable = PageRequest.of(requestPage,sizePage, Sort.by("status").ascending().and(Sort.by("date").descending()));
        return refundRepository.findAll(pageable)
                .map(this::prepareDTO);
    }
}
