package dbc.vemser.refoundapi.dataTransfer.user;

import dbc.vemser.refoundapi.entity.RoleEntity;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private Integer idUser;
    private String name;
    private String email;
    private Set<RoleEntity> roleEntities;
    private String image;

}
