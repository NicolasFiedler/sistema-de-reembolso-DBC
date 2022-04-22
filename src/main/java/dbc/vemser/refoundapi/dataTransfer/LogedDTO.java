package dbc.vemser.refoundapi.dataTransfer;

import dbc.vemser.refoundapi.entity.RoleEntity;
import io.swagger.models.auth.In;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogedDTO {
    private Integer id;
    private String name;
    private String email;
    private byte[] image;
    private Set<RoleEntity> roles;
    private String token;
}
