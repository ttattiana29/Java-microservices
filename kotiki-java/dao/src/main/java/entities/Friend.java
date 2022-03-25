package entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "kotiki_friends")
public class Friend{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "kotik")
    private Kotik kotik;
    @OneToOne
    @JoinColumn(name = "friend")
    private Kotik friend;

    public Friend() {
    }

    public Friend(Kotik kotik, Kotik friend) {
        this.kotik = kotik;
        this.friend = friend;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Kotik getKotik() {
        return kotik;
    }

    public void setKotik(Kotik kotik) {
        this.kotik = kotik;
    }

    public Kotik getFriend() {
        return friend;
    }

    public void setFriend(Kotik friend) {
        this.friend = friend;
    }
}