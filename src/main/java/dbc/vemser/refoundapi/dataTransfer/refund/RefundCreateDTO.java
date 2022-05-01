package dbc.vemser.refoundapi.dataTransfer.refund;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class RefundCreateDTO {
    @NotBlank
    @Size(min = 2, max = 60, message = "o titulo de reembolso deve conter entre 2 a 40 caracteres")
    private String title;
}
