package dbc.vemser.refoundapi.dataTransfer.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserUpdateDTO {
    private String password;
    @JsonIgnore
    private MultipartFile image;
}
