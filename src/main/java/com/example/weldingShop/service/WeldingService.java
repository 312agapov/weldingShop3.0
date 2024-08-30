package com.example.weldingShop.service;

import com.example.weldingShop.entity.Welding;
import com.example.weldingShop.repository.WeldingRepository;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WeldingService {
    @Autowired
    WeldingRepository weldingRepository;

    public void addWeld(Welding weld){
        weldingRepository.save(weld);
    }

    public List<Welding> findWeldsByName(String name){
        List<Welding> list = weldingRepository.findWeldsByName(name);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Нет сварочных аппаратов, в названии которых есть: " + name);
        }
        return weldingRepository.findWeldsByName(name);
    }

    public Welding editWeld(UUID id, Welding weld) {
        Welding editWeld = weldingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Welding с таким ID не найден!"));
        editWeld.setName(weld.getName());
        editWeld.setPrice(weld.getPrice());
        editWeld.setMaxPower(weld.getMaxPower());
        return weldingRepository.save(editWeld);
    }

    public Welding deleteWeld(UUID id){
        Welding deleteWeld = weldingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Welding с таким ID не найден!"));
        weldingRepository.deleteById(id);
        return deleteWeld;
    }

    public List<Welding> sortWeldsByPrice(String sort){
        Sort sorted;
        if (sort.equals("priceASC")) {
            sorted = Sort.by(Sort.Order.asc("price"));
            return weldingRepository.findAll(sorted);
        } else if (sort.equals("priceDESC")){
            sorted = Sort.by(Sort.Order.desc("price"));
            return weldingRepository.findAll(sorted);
        } else {
            throw new IllegalArgumentException("Введено неверное название сортировки");
        }
    }
}
