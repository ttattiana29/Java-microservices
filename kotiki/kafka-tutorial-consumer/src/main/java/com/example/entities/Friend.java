package com.example.entities;


import com.example.wrapper.FriendWrap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "kotiki_friends")
public class Friend{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kotik")
    private Kotik kotik;
    @OneToOne
    @JoinColumn(name = "friend")
    private Kotik friend;

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

    public FriendWrap getFriendWrap(){
        return new FriendWrap(id, kotik.getId(), friend.getId());
    }
}