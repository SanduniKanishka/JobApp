package com.sanduni.jobms.job;


import com.sanduni.jobms.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {

    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    Job     getById(Long id);

    boolean deleteJob(Long jobId);

    boolean updateJob(Long jobId, Job updateJob);
}
