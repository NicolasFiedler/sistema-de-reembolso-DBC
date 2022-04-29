package dbc.vemser.refoundapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Status {
    ABERTO(0, "aberto"),

    APROVADOG(1, "aprovado-gestor"),

    REPROVADOG(2, "reprovado-gestor"),

    REPROVADOF(3, "reprovado-financeiro"),

    FECHADO(4, "fechado-pago");

    private final Integer id;
    private final String name;


    public static Status ofType(Integer type) {
        return Arrays.stream(Status.values())
                .filter(tp -> tp.getId().equals(type))
                .findFirst()
                .get();
    }

}
