package uk.ac.gre.cw.aircraft.entities;

import uk.ac.gre.cw.aircraft.utils.Utilities;

import java.util.Date;

public class Qualification implements PrettyJson {

    private int id;
    private String name;
    private String description;
    private Date createdDate;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    private String strCreatedDate;

    @Override
    public void initAdditionalData() {
        strCreatedDate = Utilities.toPrettyDateString(createdDate);
    }

    public String getStrCreatedDate() {
        return strCreatedDate;
    }

    public void setStrCreatedDate(String strCreatedDate) {
        this.strCreatedDate = strCreatedDate;
    }
}
