package uk.ac.gre.cw.aircraft.jdo.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Created by Long Nguyen on 12/4/14.
 */
@PersistenceCapable(table = "Catalogue")
public class Catalogue {
    @PrimaryKey
    @Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY , column = "Catalogue_ID")
    private Long id;

    @Persistent( column = "title")
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
