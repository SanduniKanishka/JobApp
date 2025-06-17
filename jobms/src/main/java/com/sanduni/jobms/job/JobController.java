package com.sanduni.jobms.job;

import com.sanduni.jobms.job.dto.JobDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private JobService jobService;

    public  JobController(JobService jobService){
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll(){
        return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
//        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added Successully", HttpStatus.CREATED);
    }

    @GetMapping("/{job_id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long job_id){
        JobDTO job = jobService.getById(job_id);
        if (job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{job_id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long job_id){
        boolean deleted = jobService.deleteJob(job_id);
        if (deleted){
            return new ResponseEntity<>("Job is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long jobId,
                                            @RequestBody Job updateJob){
        boolean updated =   jobService.updateJob(jobId, updateJob);

        if (updated){
            return new ResponseEntity<>("Job is Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
