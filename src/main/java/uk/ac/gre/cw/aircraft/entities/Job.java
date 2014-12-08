package uk.ac.gre.cw.aircraft.entities;

import uk.ac.gre.cw.aircraft.utils.Utilities;

import java.util.Collection;
import java.util.Date;

public class Job implements PrettyJson {

    private int id;

    private String name;

    private String description;

    private int numberOfEngineer;

    private Qualification qualification;

    private Priority priority;

    private JobStatus status;

    private Collection<Engineer> engineers;

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

    public int getNumberOfEngineer() {
        return numberOfEngineer;
    }

    public void setNumberOfEngineer(int numberOfEngineer) {
        this.numberOfEngineer = numberOfEngineer;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Collection<Engineer> getEngineers() {
        return engineers;
    }

    public void setEngineers(Collection<Engineer> engineers) {
        this.engineers = engineers;
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
