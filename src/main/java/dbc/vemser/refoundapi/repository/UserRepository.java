package dbc.vemser.refoundapi.repository;

import dbc.vemser.refoundapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findByNameContainingIgnoreCase(String name);

    Optional<UserEntity> findByEmail (String email);
}
