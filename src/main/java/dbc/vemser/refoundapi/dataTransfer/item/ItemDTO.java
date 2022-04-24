package dbc.vemser.refoundapi.dataTransfer.item;

import dbc.vemser.refoundapi.dataTransfer.item.ItemCreateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO extends ItemCreateDTO {
    private Integer idItem;
}
