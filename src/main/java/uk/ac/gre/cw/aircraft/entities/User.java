package uk.ac.gre.cw.aircraft.entities;

import org.apache.commons.lang3.StringUtils;
import uk.ac.gre.cw.aircraft.utils.Utilities;

import java.util.Collection;
import java.util.Date;

public class User implements PrettyJson {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean gender;
    private String username;
    private String password;
    private Date createdDate;
    private Collection<Role> roles;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if (password == null) return "";
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean containRole(Role role) {
        if (roles == null) return false;
        return roles.contains(role);
    }

    private String strGender;

    private String strRoles;

    private String strCreatedDate;

    public void initAdditionalData() {
        setStrGender(gender ? "Male" : "Female");
        setStrRoles(roles == null ? "" : StringUtils.join(roles, ", "));
        strCreatedDate = Utilities.toPrettyDateString(createdDate);
    }

    /**
     *  For JSON Data transfer
     */
    public String getStrGender() {
        return strGender;
    }

    public void setStrGender(String strGender) {
        this.strGender = strGender;
    }

    public String getStrRoles() {
        return strRoles;
    }

    public void setStrRoles(String strRoles) {
        this.strRoles = strRoles;
    }

    public String getStrCreatedDate() {
        return strCreatedDate;
    }

    public void setStrCreatedDate(String strCreatedDate) {
        this.strCreatedDate = strCreatedDate;
    }
}
