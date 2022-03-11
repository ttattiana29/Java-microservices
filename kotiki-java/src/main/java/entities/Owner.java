package entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private LocalDate birthday;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kotik> kotiki;

    public Owner() {
    }

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

    public void setOwners(List<Kotik> autos) {
        this.kotiki = autos;
    }
}
