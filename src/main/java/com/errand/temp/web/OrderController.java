package com.errand.temp.web;

import com.errand.temp.repository.querydsl.OrderSaveDto;
import com.errand.temp.repository.querydsl.OrderSearchCond;
import com.errand.temp.repository.querydsl.OrderUpdateDto;
import com.errand.temp.service.CafeOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final CafeOrderService orderService;


    @PostMapping("/order")
    public ResponseEntity saveParty(OrderSaveDto saveDto){
        return ResponseEntity.ok(orderService.save(saveDto));
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity updateCafe(@PathVariable Long partyId, OrderUpdateDto updateDto){
        orderService.update(partyId, updateDto);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity findById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.findById(orderId));
    }

    @GetMapping("/order")
    public ResponseEntity findByAll(OrderSearchCond searchCond){
        return ResponseEntity.ok(orderService.findByAll(searchCond));
    }

}
