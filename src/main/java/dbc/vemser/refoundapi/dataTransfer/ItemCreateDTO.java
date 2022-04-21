package dbc.vemser.refoundapi.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemCreateDTO {
    @NotEmpty
    @Size(min = 2, max = 60 , message = "o nome deve conter entre 2 a 40 caracteres")
    private String name;

    private LocalDateTime data;
    @NotNull
    @DecimalMin(value = "0.01", message = "Não é permitido números negativos.")
    private Double value;
    private byte[] image;
}
