package ru.itmo.kotikilab.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itmo.kotikilab.entities.Color;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class KotikWrap {
    private int id;
    private String name;
    private LocalDate birthday;
    private String breed;
    private int colorId;
    private int owner_id;
}
