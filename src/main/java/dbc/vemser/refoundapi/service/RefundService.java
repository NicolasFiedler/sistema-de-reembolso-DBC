package dbc.vemser.refoundapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dbc.vemser.refoundapi.dataTransfer.RefundCreateDTO;
import dbc.vemser.refoundapi.dataTransfer.RefundDTO;
import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.repository.RefundRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefundService {

    private final RefundRepository refundRepository;
    private final ObjectMapper objectMapper;


    public RefundDTO create(RefundCreateDTO refundCreate){
        log.info("Chamada de método:: CREATE REFUND!");
        RefundEntity refundEntity = objectMapper.convertValue(refundCreate,RefundEntity.class);
        RefundEntity refundCreated = refundRepository.save(refundEntity);
        RefundDTO refundDTO = objectMapper.convertValue(refundCreate, RefundDTO.class);
        return refundDTO;
    }

    public List<RefundDTO> list(){
        log.info("Chamada de método:: List Refund!");
        return refundRepository.findAll().stream()
                .map(refundEntity -> objectMapper.convertValue(refundEntity,RefundDTO.class))
                .collect(Collectors.toList());
    }

    public RefundDTO update(Integer id, RefundCreateDTO refundAtt)throws Exception{
    RefundEntity refundFound = refundRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("Refund not found!"));
    refundFound.setDate(refundAtt.getDate());
    refundFound.setStatus(refundAtt.getStatus());
    refundFound.setTitle(refundAtt.getTitle());
    refundFound.setValue(refundAtt.getValue());
    RefundEntity refundEntity = refundRepository.save(refundFound);
    RefundDTO refundDTO = objectMapper.convertValue(refundEntity,RefundDTO.class);
    return refundDTO;
    }

    public RefundDTO delete(Integer id) throws Exception{
        RefundEntity refundFound = refundRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Refund not found"));
        refundRepository.delete(refundFound);
        RefundDTO refundDTO = objectMapper.convertValue(refundFound, RefundDTO.class);
        return refundDTO;
    }
}
