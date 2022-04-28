package dbc.vemser.refoundapi.dataTransfer.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateDTO {
    @NotEmpty
    @Size(min = 2, max = 60, message = "o nome deve conter entre 2 a 60 caracteres")
    private String name;

    private String dateItem;
    @NotNull
    @DecimalMin(value = "0.01", message = "Não é permitido números negativos.")
    private Double value;

    @JsonIgnore
    private MultipartFile image;
}
