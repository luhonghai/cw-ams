package uk.ac.gre.cw.aircraft.jdo.entities;
import javax.jdo.annotations.*;
@PersistenceCapable(table = "Customer")
public class Customer {

    @PrimaryKey
    @Persistent( valueStrategy = IdGeneratorStrategy.IDENTITY , column = "Customer_ID")
    private Long id;

    @Persistent( column = "name")
    private String name;

    @Persistent( column = "email")
    private String email;

    @Persistent( column = "password")
    private String password;

    @Persistent( column = "phone")
    private String phone;

    @Persistent( column = "address")
    private String address;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
