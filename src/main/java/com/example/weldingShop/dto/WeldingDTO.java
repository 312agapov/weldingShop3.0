package com.example.weldingShop.dto;

import com.example.weldingShop.entity.WeldType;
import com.example.weldingShop.entity.Welding;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WeldingDTO {

    private String name;
    private double price;
    private int maxPower;

    private WeldType type;

    private final double USDT = 90;

    public WeldingDTO() {
    }

    public WeldingDTO(String name, double price, int maxPower, WeldType type) {
        this.name = name;
        this.price = Math.ceil(price/USDT);
        this.maxPower = maxPower;
        this.type = type;
    }
}
