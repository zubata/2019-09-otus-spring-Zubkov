package ru.otus.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "vines")
@NamedEntityGraph(name = "VineAndProducer", attributeNodes = @NamedAttributeNode(value = "producer"))
public class Vine {

    @Id
    private long id;
    private int year;
    private String type;
    private String color;
    private float capacity;
    private String country;
    private boolean isAvailable;
    private double cost;

    @ManyToOne(targetEntity = Producer.class,cascade = {CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    private String name;
    private String url;

    @OneToMany(targetEntity = History.class,cascade = CascadeType.ALL, mappedBy = "vine")
    @JsonIgnore
    private List<History> historyPrice;

    public Vine() {
        historyPrice = new ArrayList<>();
    }

    @JsonSetter("wine_color")
    public void setColor(Map<String, String> wineColor) {
        this.color = wineColor.get("label");
    }

    public void setColor(String wineColor) {
        this.color = wineColor;
    }

    @JsonSetter("year")
    public void setYear(String json) {
        if (json.equals("")) this.year = 0;
        else {
            if (json.contains("&nbsp")) this.year = Integer.parseInt(json.substring(0, json.indexOf("&nbsp")));
            else this.year = Integer.parseInt(json.substring(0, 4));
        }
    }

    @JsonSetter("capacity")
    public void setCapacity(String json) {
        if (json.equals("")) this.capacity = 0;
        else {
            if (json.contains("&nbsp")) this.capacity = Float.parseFloat(json.substring(0, json.indexOf("&nbsp")));
            else this.capacity = Float.parseFloat(json.substring(0, 4));
        }
    }

    @JsonSetter("notAvailable")
    public void setIsAvailable(boolean notAvailable) { this.isAvailable = !notAvailable; }

    public boolean getIsAvailable() { return this.isAvailable; }

    public void addPriceToList(History price) { historyPrice.add(price); }

    @Override
    public String toString() {
        return "Vine{" +
                "id=" + id +
                ", year=" + year +
                ", type='" + type + '\'' +
                ", color='" + color + '\'' +
                ", capacity=" + capacity +
                ", country='" + country + '\'' +
                ", price=" + cost +
                ", producer=" + producer +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
