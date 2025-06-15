package com.sanduni.jobms.job.clients;

import com.sanduni.jobms.job.external.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE")
public interface ComapnyClient {

    @GetMapping("/companies/{id}")
    Company getCompany(@PathVariable Long id);
}
