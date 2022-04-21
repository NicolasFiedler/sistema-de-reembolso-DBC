package dbc.vemser.refoundapi.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefundDTO extends RefundCreateDTO{
    private Integer idRefund;
}
