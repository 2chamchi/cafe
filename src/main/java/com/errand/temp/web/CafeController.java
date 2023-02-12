package com.errand.temp.web;

import com.errand.temp.repository.jdbc.CafeSaveDto;
import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jdbc.CafeUpdateDto;
import com.errand.temp.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping("/cafe")
    public ResponseEntity saveCafe(CafeSaveDto saveDto){
        return ResponseEntity.ok(cafeService.save(saveDto));
    }

    @PutMapping("/cafe/{cafeId}")
    public ResponseEntity updateCafe(@PathVariable Long cafeId, CafeUpdateDto updateDto){
        cafeService.update(cafeId, updateDto);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity findById(@PathVariable Long cafeId){
        return ResponseEntity.ok(cafeService.findById(cafeId));
    }

    @GetMapping("/cafe")
    public ResponseEntity findByAll(CafeSearchCond searchCond){
        return ResponseEntity.ok(cafeService.findByAll(searchCond));
    }
}
