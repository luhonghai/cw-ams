package uk.ac.gre.cw.aircraft.entities;

import java.util.Collection;

public class Engineer extends User {

    private boolean isAvailable;

    private Collection<Qualification> qualifications;

    private Collection<Job> jobs;

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Collection<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Collection<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public Collection<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Collection<Job> jobs) {
        this.jobs = jobs;
    }
}
