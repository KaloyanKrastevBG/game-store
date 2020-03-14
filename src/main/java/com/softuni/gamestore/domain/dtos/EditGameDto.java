package com.softuni.gamestore.domain.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EditGameDto {

    private long id;
    private BigDecimal price;
    private double size;

    public EditGameDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EditGameDto(long id, BigDecimal price, double size) {
        this.id = id;
        this.price = price;
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

