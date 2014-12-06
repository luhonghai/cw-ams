package uk.ac.gre.cw.aircraft.jdo.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Created by Long Nguyen on 12/4/14.
 */
@PersistenceCapable (table = "Product")
public class Product {
    @PrimaryKey
    @Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY , column = "Product_ID")
    private Long id;

    @Persistent(column = "Name")
    private String name;

    @Persistent (column = "Specifications")
    private String specifications;

    @Persistent (column = "Unit_Price")
    private float unitPrice;

    @Persistent
    private String imageUrl;

    @Persistent
    private boolean featured;

    @Persistent
    private boolean isNew;

    @Persistent
    private boolean isSale;

    @Persistent
    private boolean recommended;

    @Persistent (column = "Catalogue_ID")
    private Long catalogueId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getCatalogueId() {
        return catalogueId;
    }

    public void setCatalogueId(Long catalogueId) {
        this.catalogueId = catalogueId;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean isSale) {
        this.isSale = isSale;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
