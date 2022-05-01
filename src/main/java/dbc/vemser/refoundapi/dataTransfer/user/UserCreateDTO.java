package dbc.vemser.refoundapi.dataTransfer.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDTO {

    @NotEmpty
    @Size(min = 2, max = 50, message = "o nome deve conter entre 2 e 40 caracteres")
    private String name;
    @Email
    private String email;

    private String password;

    @JsonIgnore
    private MultipartFile image;
}
