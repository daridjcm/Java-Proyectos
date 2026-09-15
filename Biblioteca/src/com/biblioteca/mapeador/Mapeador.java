package com.biblioteca.mapeador;

import java.util.List;

public interface Mapeador<E, R> {
    R toDto(E Entidad);
    List<R> toDoList(List<E> entidades);
}
