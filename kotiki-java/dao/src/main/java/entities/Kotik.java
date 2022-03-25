package entities;

import tools.KotikiException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "kotiki")
public class Kotik{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate birthday;
    private String breed;

    @Column(name = "color")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Owner owner;

    @OneToMany(mappedBy = "kotik", orphanRemoval = true)
    private final List<Friend> friends = new ArrayList<>();

    public Kotik() {
    }

    public Kotik(String name, LocalDate birthday, String breed,
                 Color color) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        //this.ownerId = ownerId;
    }

    public Friend addFriend(Kotik kotik) throws KotikiException {
        if(kotik == null) {
            throw new KotikiException("Sorry not found kotik");
        }
        if(kotik == this) {
            throw new KotikiException("Sorry you can't be frendship with yourself I must to say your owner about your schiza");
        }
        Friend newFriend = new Friend(this, kotik);
        friends.add(newFriend);
        return newFriend;
    }

    public void removeFriends(Friend friend) {
        friends.remove(friend);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Color getColorId() {
        return color;
    }

    public void setColorId(Color color) {
        this.color = color;
    }

    public Owner getOwnerId() {
        return owner;
    }

    public void setOwnerId(Owner owner) {
        this.owner = owner;
    }

}
