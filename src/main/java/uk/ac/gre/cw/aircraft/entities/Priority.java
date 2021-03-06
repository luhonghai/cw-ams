package uk.ac.gre.cw.aircraft.entities;

public class Priority {

    private int id = 4;
    private String name;
    private float level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLevel() {
        return level;
    }

    public void setLevel(float level) {
        this.level = level;
    }

    public String getColor() {
        switch (id) {
            case 1: return "#FF0000";
            case 2: return "#FF8C00";
            case 3: return "#87CEFA";
            case 4: return "#98FB98";
        }
        return "";
    }

    public String getIcon() {
        switch (id) {
            case 1: return "fa-angle-double-up";
            case 2: return "fa-angle-up";
            case 3: return "fa-angle-down";
            case 4: return "fa-angle-double-down";
        }
        return "";
    }
}
