package com.example.companyService.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @GetMapping()
    public ResponseEntity<List<Company>> getCompanies(){
        return ResponseEntity.ok(companyService.getCompanies());
    }
    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("companyId") int companyId){
        return ResponseEntity.ok(companyService.getCompanyById(companyId));
    }
    @PostMapping()
    public ResponseEntity<Company> addCompany(@RequestBody Company company){
        return new ResponseEntity(companyService.addCompany(company), HttpStatus.CREATED);
    }
    @PutMapping("/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable("companyId") int companyId,@RequestBody Company company){
        return  new ResponseEntity<>(companyService.updateCompany(companyId,company),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{companyId}")
    public ResponseEntity<List<Company>> deleteCompany(@PathVariable("companyId")int companyId){
        return new ResponseEntity<>(companyService.deleteCompany(companyId),HttpStatus.MOVED_PERMANENTLY);
    }

}
