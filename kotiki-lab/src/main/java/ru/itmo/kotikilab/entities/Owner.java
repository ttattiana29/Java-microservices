package ru.itmo.kotikilab.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.kotikilab.wrapper.OwnerWrap;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "owners")
public class Owner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private LocalDate birthday;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kotik> kotiki;

    public Owner(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        kotiki = new ArrayList<>();
    }

    public void addKotik(Kotik kotik) {
        kotik.setOwnerId(this);
        kotiki.add(kotik);
    }

    public void removeKotik(Kotik kotik) {
        kotiki.remove(kotik);
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

    public LocalDate getBirthDay() {
        return birthday;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthday = birthDay;
    }

    public List<Kotik> getKotiki() {
        return kotiki;
    }

    public List<Integer> getKotikiId(){
        List<Integer> kotikiId = new ArrayList<>();
        for (Kotik item: kotiki) {
            kotikiId.add(item.getId());
        }
        return kotikiId;
    }

    public OwnerWrap getOwnerWrap(){
        return new OwnerWrap(id, name, birthday, this.getKotikiId());
    }
}
