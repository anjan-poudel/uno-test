package com.uno.homeloans.repository;

import com.uno.homeloans.web.model.CalculationResult;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;



@EnableScan
public interface CalculationResultRepository extends
        CrudRepository<CalculationResult, String> {
}
