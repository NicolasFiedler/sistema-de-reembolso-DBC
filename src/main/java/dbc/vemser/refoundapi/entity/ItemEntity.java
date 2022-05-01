package dbc.vemser.refoundapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity(name = "item")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_item_seq")
    @SequenceGenerator(name = "item_id_item_seq", sequenceName = "item_id_item_seq", allocationSize = 1)
    @Column(name = "id_item")
    private Integer idItem;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "value")
    private Double value;

    @Column(name = "attachment")
    private byte[] image;

    @Column(name = "id_refund", insertable = false, updatable = false)
    private Integer idRefund;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_refund", referencedColumnName = "id_refund")
    private RefundEntity refundEntity;
}
