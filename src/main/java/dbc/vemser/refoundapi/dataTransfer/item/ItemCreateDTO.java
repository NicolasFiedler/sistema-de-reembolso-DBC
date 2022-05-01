package dbc.vemser.refoundapi.dataTransfer.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCreateDTO {

    @NotEmpty
    @NotNull
    @Size(min = 2, max = 60, message = "o nome deve conter entre 2 a 60 caracteres")
    private String name;

    @NotEmpty
    @NotNull
    private String dateItem;

    @NotNull
    @DecimalMin(value = "0.01", message = "Não é permitido números negativos.")
    private String value;

    @NotNull
    @JsonIgnore
    private MultipartFile image;
}
