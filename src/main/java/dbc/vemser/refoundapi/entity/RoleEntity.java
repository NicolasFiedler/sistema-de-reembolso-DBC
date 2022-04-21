package dbc.vemser.refoundapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class RoleEntity {

    @Id
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "roleEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<UserEntity> userEntities;
}
