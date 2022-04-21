package dbc.vemser.refoundapi.dataTransfer;

import dbc.vemser.refoundapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefundCreateDTO {
    @NotBlank
    @Size(min = 2, max = 60 , message = "o titulo de reembolso deve conter entre 2 a 40 caracteres")
    private String title;
    private LocalDateTime data;
    @NotNull
    private Status status;
    @NotNull
    @DecimalMin(value = "0.01", message = "Não é permitido números negativos.")
    private Double value;
}
