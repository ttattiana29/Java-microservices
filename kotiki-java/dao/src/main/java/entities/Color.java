package entities;

import java.util.HashMap;
import java.util.Map;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    RED("red"),
    GREY("grey"),
    GREEN("green"),
    YELLOW("yellow");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static final Map<String, Color> LOOKUP_MAP = new HashMap<>();

    static {
        for (Color env : values()) {
            LOOKUP_MAP.put(env.getName(), env);
        }
    }

    public static Color getTypeByName(String url) {
        return LOOKUP_MAP.get(url);
    }
}
