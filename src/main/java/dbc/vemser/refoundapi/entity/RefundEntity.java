package dbc.vemser.refoundapi.entity;

import dbc.vemser.refoundapi.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "refund")
public class RefundEntity {

    @Id
    @Column(name = "id_refund")
    private Integer idRefund;

    @Column(name = "title")
    private String title;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Column(name = "value")
    private Double value;
}
