package dbc.vemser.refoundapi.entity;

import dbc.vemser.refoundapi.enums.Status;
import jdk.jshell.Snippet;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundEntity {
    private Integer idRefund;
    private String title;
    private LocalDateTime data;
    private Status status;
    private Double value;
}
