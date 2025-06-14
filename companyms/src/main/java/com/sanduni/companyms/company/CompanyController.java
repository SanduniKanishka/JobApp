package com.sanduni.companyms.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    private ComapnyService comapnyService;

    public CompanyController(ComapnyService comapnyService){
        this.comapnyService = comapnyService;
    }

    @GetMapping("/companies")
    public ResponseEntity<List<Company>> findAll(){
        return new ResponseEntity<>(comapnyService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/companies")
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        comapnyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/companies/{companyId}")
    public ResponseEntity<Company> getById(@PathVariable Long companyId){
        Company company = comapnyService.getById(companyId);
        if (company != null){
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/companies/{companyId}")
    public ResponseEntity<String> updateCompany(@PathVariable Long companyId,
                                                @RequestBody Company company){
        comapnyService.updateCompany(companyId, company);
        return new ResponseEntity<>("Company Update Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId){
        boolean isDeleted = comapnyService.deleteById(companyId);
        if (isDeleted){
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company is not found", HttpStatus.NOT_FOUND);
        }

    }
}
