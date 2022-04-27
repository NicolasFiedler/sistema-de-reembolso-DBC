package dbc.vemser.refoundapi.dataTransfer.user;

import dbc.vemser.refoundapi.entity.RoleEntity;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    private String password;
    private Set<RoleEntity> roleEntities;
    private String image;

}
