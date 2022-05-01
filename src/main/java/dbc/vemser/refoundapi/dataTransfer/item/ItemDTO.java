package dbc.vemser.refoundapi.dataTransfer.item;

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
