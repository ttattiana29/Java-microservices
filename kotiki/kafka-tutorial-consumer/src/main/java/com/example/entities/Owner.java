package com.example.entities;


import com.example.wrapper.OwnerWrap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owners")
public class Owner{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private LocalDate birthday;
    private String username;
    private String password;
    private String role;
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kotik> kotiki;

    public Owner(String name, LocalDate birthday, String username, String password, String role) {
        this.name = name;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.role = role;
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
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setKotiki(List<Kotik> kotiki) {
        this.kotiki = kotiki;
    }


    public OwnerWrap getOwnerWrap(){
        return new OwnerWrap(id, name, birthday, username, password, role, this.getKotikiId());
    }
}
