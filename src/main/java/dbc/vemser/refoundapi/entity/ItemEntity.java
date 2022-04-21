package dbc.vemser.refoundapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ItemEntity {
    private Integer idItem;
    private String name;
    private LocalDateTime data;
    private Double value;
    @Lob
    private byte[] image;
}
