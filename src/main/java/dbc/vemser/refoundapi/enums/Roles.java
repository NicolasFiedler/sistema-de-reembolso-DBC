package dbc.vemser.refoundapi.enums;

import lombok.Getter;

@Getter
public enum Roles {
    ROLE_ADMIN(1),
    ROLE_FINANCEIRO(2),
    ROLE_GESTOR(3),
    ROLE_COLABORADOR(4);


    private final Integer id;

    Roles(Integer id) {
        this.id = id;
    }
}
