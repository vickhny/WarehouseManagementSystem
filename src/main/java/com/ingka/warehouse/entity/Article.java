package com.ingka.warehouse.entity;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Article {

    @Id
    private Long id;

    @NotNull
    private String name;

    private int stock;

    public boolean updateStock(int count) {
        if (stock >= count) {
            this.stock = stock - count;
            return true;
        }
        return false;
    }

    public void loadStock(int count) {
        this.stock += count;
    }
}
