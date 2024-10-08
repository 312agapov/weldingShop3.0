package com.example.weldingShop.controller;

import com.example.weldingShop.dto.WeldingDTO;
import com.example.weldingShop.entity.Welding;
import com.example.weldingShop.service.WeldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/weld")
public class WeldingController {
    @Autowired
    WeldingService weldingService;

    @PostMapping(path = "/add")
    public void addWeld(@RequestBody Welding weld){
        weldingService.addWeld(weld);
    }

    @PutMapping(path = "/edit/{id}")
    public Welding addWeld(@PathVariable ("id") UUID id, @RequestBody Welding weld) {
        return weldingService.editWeld(id, weld);
    }

    @DeleteMapping(path = "/{id}")
    public Welding addWeld(@PathVariable ("id") UUID id) {
        return weldingService.deleteWeld(id);
    }

    @GetMapping(path = "/RU")
    public List<Welding> findWeldsByName(@RequestParam String name){
        return weldingService.findWeldsByName(name);
    }

    @GetMapping(path = "/pages/{page}")
    public List<Welding> findWeldsByNameLim10(@PathVariable ("page") int page){
        return weldingService.findWeldsLimited(page);
    }

    @GetMapping(path = "/US")
    public List<WeldingDTO> findWeldsByNameUS(@RequestParam String name){
        return weldingService.findWeldsByNameUS(name);
    }

    @GetMapping(path = "/{sort}")
    public List<Welding> showWeldsSortedAsc(@PathVariable ("sort") String sort){
        return weldingService.sortWeldsByPrice(sort);
    }
}
