package dbc.vemser.refoundapi.dataTransfer.item;

import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    private Integer idItem;
    private String dateItem;
    private String name;
    private Double value;
    private String imageString;
}
