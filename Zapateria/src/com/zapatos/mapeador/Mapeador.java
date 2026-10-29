package com.zapatos.mapeador;

import java.util.List;

public interface Mapeador<E, R> {
    R toDto(E Entidad);
    List<R> toDtoList(List<E> entidades);
}
