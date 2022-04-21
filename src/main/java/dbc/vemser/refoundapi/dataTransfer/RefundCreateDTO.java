package dbc.vemser.refoundapi.dataTransfer;

import dbc.vemser.refoundapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefundCreateDTO {
    private String title;
    private LocalDateTime date;
    private Status status;
    private Double value;
}
