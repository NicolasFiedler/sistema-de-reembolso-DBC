package dbc.vemser.refoundapi.dataTransfer.refund;

import dbc.vemser.refoundapi.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundUpdateDTO {
    private String title;
    private Integer status;

}
