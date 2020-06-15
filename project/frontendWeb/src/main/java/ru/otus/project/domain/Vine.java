package ru.otus.project.domain;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Vine {

    private long id;
    private int year;
    private String type;
    private String color;
    private float capacity;
    private String country;
    private boolean isAvailable;
    private double cost;
    private Producer producer;
    private String name;
    private String url;
    private List<History> historyPrice;

    public Vine() {
        historyPrice = new ArrayList<>();
    }

    public void setIsAvailable(boolean isAvailable) { this.isAvailable = isAvailable; }
    public boolean getIsAvailable() {return isAvailable;}

    @JsonSetter("wine_color")
    public void setColor(String wineColor) {
        this.color = wineColor;
    }

    public void addPriceToList(History price) {
        historyPrice.add(price);
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", название ='" + name + '\'' +
                ", производитель = " + producer.getName() +
                ", тип ='" + type + '\'' +
                ", цвет ='" + color + '\'' +
                ", год = " + year +
                ", объём = " + capacity +
                ", страна ='" + country + '\'' +
                ", В наличии = " + (isAvailable? "да" : "нет") +
                ", цена = " + cost +
                ", url= " + url;
    }
}
