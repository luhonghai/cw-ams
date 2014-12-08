package uk.ac.gre.cw.aircraft.entities;

public class Role {

    public static Role ADMINISTRATOR;

    public static Role ENGINEER;

    static {
        ADMINISTRATOR = new Role(1, "Administrator");
        ENGINEER = new Role(2, "Engineer");
    }

    public Role() {

    }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;

    private String name;

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

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Role) {
            Role tmp = (Role) obj;
            return tmp.getId() == this.getId();
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
