package dbc.vemser.refoundapi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity(name = "item")
public class ItemEntity {

    @Id
    @Column(name = "id_item")
    private Integer idItem;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "value")
    private Double value;

    @Lob
    @Column(name = "attachment")
    private byte[] image;
}
