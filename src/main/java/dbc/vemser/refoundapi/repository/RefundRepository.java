package dbc.vemser.refoundapi.repository;

import dbc.vemser.refoundapi.entity.RefundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefundRepository extends JpaRepository<RefundEntity, Integer> {
}
