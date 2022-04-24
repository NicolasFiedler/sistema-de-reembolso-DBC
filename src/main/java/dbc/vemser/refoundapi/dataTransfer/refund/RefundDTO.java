package dbc.vemser.refoundapi.dataTransfer.refund;

import dbc.vemser.refoundapi.dataTransfer.item.ItemDTO;
import dbc.vemser.refoundapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefundDTO {
    private Integer idRefund;
    private String title;
    private Double value;
    private String date;
    private Status status;

    private Set<ItemDTO> items;
}
