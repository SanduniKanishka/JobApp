package com.sanduni.jobms.job.impl;

import com.sanduni.jobms.job.clients.ComapnyClient;
import com.sanduni.jobms.job.clients.ReviewClient;
import com.sanduni.jobms.job.dto.JobDTO;
import com.sanduni.jobms.job.external.Company;
import com.sanduni.jobms.job.Job;
import com.sanduni.jobms.job.JobRepository;
import com.sanduni.jobms.job.JobService;
import com.sanduni.jobms.job.external.Review;
import com.sanduni.jobms.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    RestTemplate restTemplate;

    private ComapnyClient companyClient;
    private ReviewClient reviewCLient;

    int attempt =0;

    public JobServiceImpl(JobRepository jobRepository, ComapnyClient companyClient, ReviewClient reviewCLient){
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewCLient = reviewCLient;
    }
    @Override
//    @CircuitBreaker(name = "companyBreaker",
//                    fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker",
//                    fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {
        System.out.println("Attempt: "+ ++attempt);
        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOList = new ArrayList<>();

//        Company company = restTemplate.getForObject("http://localhost:8081/companies/1", Company.class);
//        System.out.println("COMPANY:"+ company.getName());
//        System.out.println("COMPANY:"+ company.getDescription());
        return jobs.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<String> companyBreakerFallback(Exception ex){
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;
    }

    private JobDTO convertToDto(Job job){

        //Using RestTemplate
//        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId() , Company.class);
//        ResponseEntity<List<Review>> reviewResponse= restTemplate.exchange("http://REVIEW-SERVICE:8083/reviews?company_id=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });

        //Using Feign
        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewCLient.getReviews(job.getCompanyId());
        JobDTO jobDTO = JobMapper.mapToJobWithCompanyDto(job, company, reviews);
        return jobDTO;
    }

    @Override
    public void createJob(Job job) {
//        job.setId(id++);
//        jobs.add(job);
//        return "Job added Successully";
        jobRepository.save(job);
    }

    @Override
    public JobDTO getById(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if (job == null) {
            return null; // Or throw a custom exception
        }
        return convertToDto(job);
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
