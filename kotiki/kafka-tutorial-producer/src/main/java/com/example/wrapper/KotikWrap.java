package com.example.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;

@Data
public class KotikWrap {
    private int id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private int colorId;
    private int owner_id;

    public KotikWrap() {}

    public KotikWrap(int id, String name, LocalDate birthday, String breed, int colorId, int owner_id) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.colorId = colorId;
        this.owner_id = owner_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public String toString() {
        return "KotikWrap{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", breed='" + breed + '\'' +
                ", colorId=" + colorId +
                ", owner_id=" + owner_id +
                '}';
    }
}
