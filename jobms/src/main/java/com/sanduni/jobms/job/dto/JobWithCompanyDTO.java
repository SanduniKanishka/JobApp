package com.sanduni.jobms.job.dto;

import com.sanduni.jobms.job.Job;
import com.sanduni.jobms.job.external.Company;

public class JobWithCompanyDTO {

    private Job job;

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    private Company company;
}
