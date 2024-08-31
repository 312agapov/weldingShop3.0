package com.example.weldingShop.service;

import com.example.weldingShop.dto.WeldingDTO;
import com.example.weldingShop.entity.Welding;
import com.example.weldingShop.repository.WeldingRepository;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<Welding> findWeldsLimited(int page){
        final int limit = 2;
        Pageable pageable = PageRequest.of(page-1, limit);
        List<Welding> list = weldingRepository.findWeldsLimited(pageable);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Нет сварочных аппаратов");
        }
        return weldingRepository.findWeldsLimited(pageable);
    }

    public List<WeldingDTO> findWeldsByNameUS(String name){
        List<Welding> list = weldingRepository.findWeldsByName(name);
        if (list.isEmpty()) {
            throw new IllegalArgumentException("Нет сварочных аппаратов, в названии которых есть: " + name);
        }
        return list.stream()
                .map(welding -> new WeldingDTO(
                welding.getName(),
                welding.getPrice(),
                welding.getMaxPower(),
                welding.getType()
            )).collect(Collectors.toList());

    }

    public Welding editWeld(UUID id, Welding weld) {
        Welding editWeld = weldingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Welding с таким ID не найден!"));
        editWeld.setName(weld.getName());
        editWeld.setPrice(weld.getPrice());
        editWeld.setMaxPower(weld.getMaxPower());
        editWeld.setType(weld.getType());
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
