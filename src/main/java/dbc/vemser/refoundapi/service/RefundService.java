package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.RefundCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.RefundDTO;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final ObjectMapper objectMapper;


    public RefundDTO create(RefundCreateDTO refundCreate) {
        log.info("Chamada de método:: CREATE REFUND!");
        RefundEntity refundEntity = objectMapper.convertValue(refundCreate, RefundEntity.class);
        refundEntity.setDate(LocalDateTime.now());
        RefundEntity refundCreated = refundRepository.save(refundEntity);
        return objectMapper.convertValue(refundCreate, RefundDTO.class);
    }

    public List<RefundDTO> list() {
        log.info("Chamada de método:: List Refund!");
        return refundRepository.findAll().stream()
                .map(refundEntity -> objectMapper.convertValue(refundEntity, RefundDTO.class))
                .collect(Collectors.toList());
    }

    public RefundDTO update(Integer id, RefundCreateDTO refundAtt) throws Exception {
        RefundEntity refundFound = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found!"));
        refundFound.setStatus(refundAtt.getStatus());
        refundFound.setTitle(refundAtt.getTitle());
        refundFound.setValue(refundAtt.getValue());
        RefundEntity refundEntity = refundRepository.save(refundFound);
        return objectMapper.convertValue(refundEntity, RefundDTO.class);
    }

    public RefundDTO delete(Integer id) throws Exception {
        RefundEntity refundFound = refundRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Refund not found"));
        refundRepository.delete(refundFound);
        return objectMapper.convertValue(refundFound, RefundDTO.class);
    }
}
