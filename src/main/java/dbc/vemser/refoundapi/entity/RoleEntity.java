package dbc.vemser.refoundapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "roles")
public class RoleEntity implements Serializable, GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_role_seq")
    @SequenceGenerator(name = "roles_id_role_seq", sequenceName = "roles_id_role_seq", allocationSize = 1)
    @Column(name = "id_role")
    private Integer idRole;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roleEntities")
    private Set<UserEntity> userEntities;

    @Override
    public String getAuthority() {
        return role;
    }
}
