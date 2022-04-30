package dbc.vemser.refoundapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {
    ABERTO(0, "aberto", "Nova solicitação de reembolso."),

    APROVADOG(1, "aprovado-gestor", "Solicitação de reembolso aprovada pelo gestor."),

    REPROVADOG(2, "reprovado-gestor", "Solicitação de reembolso reprovada."),

    REPROVADOF(3, "reprovado-financeiro", "Solicitação de reembolso reprovada."),

    FECHADO(4, "fechado-pago", "Solicitação de reembolso aprovada!");

    private final Integer id;
    private final String name;
    private final String subject;


    public static Status ofType(Integer type) {
        return Arrays.stream(Status.values())
                .filter(tp -> tp.getId().equals(type))
                .findFirst()
                .get();
    }

}
