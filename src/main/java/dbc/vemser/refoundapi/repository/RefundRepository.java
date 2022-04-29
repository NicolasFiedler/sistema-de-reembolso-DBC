package dbc.vemser.refoundapi.repository;

import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity, Integer> {
    Page<RefundEntity> findByStatus (Status status, Pageable pageable);

    Optional<RefundEntity> findByIdRefundAndIdUserAndStatus(Integer idRefund, Integer idUser, Status aberto);

    Optional<RefundEntity> findByIdRefundAndIdUser(Integer id, Integer idUser);
}
