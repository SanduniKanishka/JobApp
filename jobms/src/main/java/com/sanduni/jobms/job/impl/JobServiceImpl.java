package com.sanduni.jobms.job.impl;

import com.sanduni.jobms.job.dto.JobWithCompanyDTO;
import com.sanduni.jobms.job.external.Company;
import com.sanduni.jobms.job.Job;
import com.sanduni.jobms.job.JobRepository;
import com.sanduni.jobms.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

//    private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }
    @Override
    public List<JobWithCompanyDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDTO> jobWithCompanyDTOList = new ArrayList<>();

//        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
//        System.out.println("COMPANY:"+ company.getName());
//        System.out.println("COMPANY:"+ company.getDescription());
        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private JobWithCompanyDTO convertToDto(Job job){
        RestTemplate restTemplate = new RestTemplate();
        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setJob(job);
        Company company = restTemplate.getForObject("http://localhost:8081/companies/"+job.getCompanyId() , Company.class);
        jobWithCompanyDTO.setCompany(company);
        return jobWithCompanyDTO;
    }

    @Override
    public void createJob(Job job) {
//        job.setId(id++);
//        jobs.add(job);
//        return "Job added Successully";
        jobRepository.save(job);
    }

    @Override
    public Job getById(Long id) {
//        for (Job job: jobs){
//            if (job.getId().equals(id)){
//                return job;
//            }
//
//        }
//        return null;
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJob(Long jobId) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext()){
//            Job job = iterator.next();
//            if (job.getId().equals(jobId)){
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            jobRepository.deleteById(jobId);
            return true;
        }catch (Exception exception){
            return false;
        }

    }

    @Override
    public boolean updateJob(Long jobId, Job updateJob) {
        Optional<Job> optionalJob = jobRepository.findById(jobId);
//        for (Job job: jobs){
            if (optionalJob.isPresent()){
                Job job = optionalJob.get();
                job.setDescription(updateJob.getDescription());
                job.setLocation(updateJob.getLocation());
                job.setTitle(updateJob.getTitle());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setMinSalary(updateJob.getMinSalary());
                jobRepository.save(job);
                return true;
            }
//        }
        return false;
    }
}
