package com.example.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class FriendWrap {
    public int id;
    public int kotikId;
    public int friendId;

    public FriendWrap() {}

    public FriendWrap(int id, int kotikId, int friendId) {
        this.id = id;
        this.kotikId = kotikId;
        this.friendId = friendId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKotikId() {
        return kotikId;
    }

    public void setKotikId(int kotikId) {
        this.kotikId = kotikId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    @Override
    public String toString() {
        return "FriendWrap{" +
                "id=" + id +
                ", kotikId=" + kotikId +
                ", friendId=" + friendId +
                '}';
    }
}
