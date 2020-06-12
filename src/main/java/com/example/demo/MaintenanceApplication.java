package com.example.demo;

import com.example.demo.dao.FlatMaintenanceLookUpDAO;
import com.example.demo.dao.MaintenanceTxnDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		FlatMaintenanceLookUpDAO.class,
		MaintenanceTxnDAO.class
})
public class MaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintenanceApplication.class, args);
	}
}
