package com.errand.temp.service;

import com.errand.temp.domain.Cafe;
import com.errand.temp.repository.jdbc.CafeSaveDto;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CafeService {
    Cafe save(CafeSaveDto saveDto);
    void update(Long cafeId, CafeUpdateDto updateDto);

    Optional<Cafe> findById(Long cafeId);
    List<Cafe> findByAll(CafeSearchCond searchCond);
}
