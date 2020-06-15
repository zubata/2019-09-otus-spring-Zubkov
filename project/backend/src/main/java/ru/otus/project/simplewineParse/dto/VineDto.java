package ru.otus.project.simplewineParse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VineDto {

    @Id
    private String id;
    private String year;
    private String type;
    private String color;
    private float capacity;
    private String country;
    private double unitSalePrice;
    private String manufacturer;
    private String name;
    private String url;

    public VineDto(String id, String year, String color, double unitSalePrice, String manufacturer, String name, String url) {
        this.id = id;
        this.year = year;
        this.color = color;
        this.unitSalePrice = unitSalePrice;
        this.manufacturer = manufacturer;
        this.name = name;
        this.url = url;
    }
}
