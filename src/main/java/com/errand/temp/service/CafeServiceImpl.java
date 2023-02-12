package com.errand.temp.service;

import com.errand.temp.domain.Cafe;
import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.jdbc.CafeSaveDto;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CafeServiceImpl implements CafeService {

    private final CafeRepository cafeRepository;

    public CafeServiceImpl(@Qualifier("jdbcCafeRepository") CafeRepository cafeRepository){
        this.cafeRepository = cafeRepository;
    }

    @Override
    public Cafe save(CafeSaveDto saveDto) {
        Cafe cafe = new Cafe();
        cafe.setCafeName(saveDto.getCafeName());
        return cafeRepository.save(cafe);
    }

    @Override
    public void update(Long cafeId, CafeUpdateDto updateDto) {
        cafeRepository.update(cafeId, updateDto);
    }

    @Override
    public Optional<Cafe> findById(Long cafeId) {
        return cafeRepository.findById(cafeId);
    }

    @Override
    public List<Cafe> findByAll(CafeSearchCond searchCond) {
        return cafeRepository.findAll(searchCond);
    }
}
