package dbc.vemser.refoundapi.dataTransfer.refund;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundUpdateDTO {
    private String title;
    private Integer status;

}
