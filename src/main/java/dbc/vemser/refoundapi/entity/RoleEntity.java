package dbc.vemser.refoundapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
