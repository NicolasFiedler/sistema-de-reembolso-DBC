package dbc.vemser.refoundapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dbc.vemser.refoundapi.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @Column(name = "id_user", insertable = false, updatable = false)
    private Integer idUser;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private UserEntity userEntity;

    @JsonIgnore
    @OneToMany(mappedBy = "refundEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ItemEntity> itemEntities;
}
