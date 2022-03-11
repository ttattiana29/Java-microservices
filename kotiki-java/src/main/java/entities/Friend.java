package entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "kotiki_friends")
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "kotik_id")
    private Integer kotik_id;
    @Column(name = "friend_id")
    private Integer friend_id;

    public Friend() {
    }

    public Friend(Integer kotik_id, Integer friend_id) {
        this.kotik_id = kotik_id;
        this.friend_id = friend_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getKotik_id() {
        return kotik_id;
    }

    public void setKotik_id(Integer kotik_id) {
        this.kotik_id = kotik_id;
    }

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }
}