package dbc.vemser.refoundapi.dataTransfer.auth;

import dbc.vemser.refoundapi.entity.RoleEntity;
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
    private String image;
    private Set<RoleEntity> roles;
    private String token;
}
