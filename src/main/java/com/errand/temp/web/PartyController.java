package com.errand.temp.web;

import com.errand.temp.repository.jdbc.CafeSearchCond;
import com.errand.temp.repository.jpa.PartySaveDto;
import com.errand.temp.repository.jpa.PartySearchCond;
import com.errand.temp.repository.jpa.PartyUpdateDto;
import com.errand.temp.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PartyController {

    private final PartyService partyService;


    @PostMapping("/party")
    public ResponseEntity saveParty(PartySaveDto saveDto){
        return ResponseEntity.ok(partyService.save(saveDto));
    }

    @PutMapping("/party/{partyId}")
    public ResponseEntity updateCafe(@PathVariable Long partyId, PartyUpdateDto updateDto){
        partyService.update(partyId, updateDto);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/party/{partyId}")
    public ResponseEntity findById(@PathVariable Long partyId){
        return ResponseEntity.ok(partyService.findById(partyId));
    }

    @GetMapping("/party")
    public ResponseEntity findByAll(PartySearchCond searchCond){
        return ResponseEntity.ok(partyService.findByAll(searchCond));
    }

}
