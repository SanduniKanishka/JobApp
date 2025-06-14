package com.sanduni.jobms.job.mapper;

import com.sanduni.jobms.job.Job;
import com.sanduni.jobms.job.dto.JobDTO;
import com.sanduni.jobms.job.external.Company;
import com.sanduni.jobms.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobWithCompanyDto(Job job, Company company, List<Review> reviews){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setCompany(company);
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
