package ru.itmo.kotikilab.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FriendWrap {
    public int id;
    public int kotikId;
    public int friendId;
}
