package dbc.vemser.refoundapi.enums;

import lombok.Getter;

@Getter
public enum Status {
    ABERTO("aberto"),
    APROVADOG("aprovado-gestor"),
    REPROVADOG("reprovado-gestor"),
    REPROVADOF("reprovado-financeiro"),
    FECHADO("fechado-pago");

    private final String name;

    Status(String nome) {
        this.name = nome;
    }

}
