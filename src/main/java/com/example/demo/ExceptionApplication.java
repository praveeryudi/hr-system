package com.example.demo;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.JobsRepository;
import com.example.demo.dao.MaintenanceTxnDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		EmployeeRepository.class,
		JobsRepository.class,
		FlatMaintenanceLookUpDAO.class,
		MaintenanceTxnDAO.class
})
public class ExceptionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionApplication.class, args);
	}
}
