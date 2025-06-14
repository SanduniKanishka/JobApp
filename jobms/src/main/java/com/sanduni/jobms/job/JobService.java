package com.sanduni.jobms.job;


import com.sanduni.jobms.job.dto.JobDTO;

import java.util.List;

public interface JobService {

    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getById(Long id);

    boolean deleteJob(Long jobId);

    boolean updateJob(Long jobId, Job updateJob);
}
