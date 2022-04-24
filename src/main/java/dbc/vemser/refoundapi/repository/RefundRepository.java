package dbc.vemser.refoundapi.repository;

import dbc.vemser.refoundapi.entity.RefundEntity;
import dbc.vemser.refoundapi.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity, Integer> {
    List<RefundEntity> findByStatus (Status status);
}
