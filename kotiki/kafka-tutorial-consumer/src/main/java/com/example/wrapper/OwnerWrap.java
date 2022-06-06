package com.example.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.example.entities.Kotik;

import java.time.LocalDate;
import java.util.List;

@Data
//@AllArgsConstructor
public class OwnerWrap {
    private int id;
    private String name;
    private LocalDate birthday;
    private String username;
    private String password;
    private String role;
    private List<Integer> kotikiId;


    @Override
    public String toString() {
        return "OwnerWrap{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", kotikiId=" + kotikiId +
                '}';
    }

    public OwnerWrap(int id, String name, LocalDate birthday, String username, String password, String role, List<Integer> kotikiId) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.role = role;
        this.kotikiId = kotikiId;
    }

    public OwnerWrap() {}

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

    public List<Integer> getKotikiId() {
        return kotikiId;
    }

    public void setKotikiId(List<Integer> kotikiId) {
        this.kotikiId = kotikiId;
    }
}
