package ru.itmo.kotikilab.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itmo.kotikilab.entities.Kotik;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OwnerWrap {
    private int id;
    private String name;
    private LocalDate birthday;
    private String username;
    private String password;
    private String role;
    private List<Integer> kotikiId;
}
