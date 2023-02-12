package com.errand.temp.repository.memory;

import com.errand.temp.domain.Cafe;
import com.errand.temp.repository.jdbc.CafeRepository;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemoryCafeRepository implements CafeRepository {


    private static final Map<Long, Cafe> cafes = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Cafe save(Cafe cafe) {
        cafe.setCafeId(++sequence);
        cafes.put(cafe.getCafeId(), cafe);
        return cafe;
    }

    @Override
    public void update(Long cafeId, CafeUpdateDto updateDto) {
        Cafe findCafe = findById(cafeId).orElseThrow(null);
        findCafe.setCafeName(updateDto.getCafeName());
    }

    @Override
    public Optional<Cafe> findById(Long cafeId) {
        return Optional.ofNullable(cafes.get(cafeId));
    }

    @Override
    public List<Cafe> findAll(CafeSearchCond cond) {
        String cafeName = cond.getCafeName();
        return cafes.values().stream()
                .filter(cafe -> {
                    if(ObjectUtils.isEmpty(cafeName)){
                        return true;
                    }
                    return cafe.getCafeName().contains(cafeName);
                })
                .filter(cafe -> {
                    if(cafeName == null){
                        return true;
                    }
                    return cafeName.equals(cafe.getCafeName());
                })
                .collect(Collectors.toList());
    }

    public void clearCafe(){
        cafes.clear();
    }
}
