package dbc.vemser.refoundapi.enums;

public enum Status {
    ABERTO("aberto"), APROVADOG("aprovado-gestor"), REPROVADOG("reprovado-gestor"),
    REPROVADOF("reprovado-financeiro"), FECHADO("fechado-pago");

    private final String nome;

    Status(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
