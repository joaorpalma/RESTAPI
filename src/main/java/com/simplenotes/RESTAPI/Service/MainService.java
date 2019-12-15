package com.simplenotes.RESTAPI.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MainService<T> {
    List<T> getAll();
    T add (T o);
    T update (T o);
    T getById (int id);
    T deleteById (int id);
}
