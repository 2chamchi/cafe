package com.errand.temp.repository.jdbc;

import com.errand.temp.domain.Cafe;

import java.util.List;
import java.util.Optional;

public interface CafeRepository {

    Cafe save(Cafe cafe);
    void update(Long cafeId, CafeUpdateDto updateDto);
    Optional<Cafe> findById(Long cafeId);
    List<Cafe> findAll(CafeSearchCond cond);
}
