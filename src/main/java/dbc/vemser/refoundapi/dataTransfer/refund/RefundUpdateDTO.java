package dbc.vemser.refoundapi.dataTransfer.refund;

import dbc.vemser.refoundapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefundUpdateDTO {
    private String title;
    private Double value;
    private Status status;

}
