package dbc.vemser.refoundapi.dataTransfer.user;

import dbc.vemser.refoundapi.entity.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO extends UserCreateDTO{

    private Integer idUser;
    private Set<RoleEntity> roleEntities;
}
