/*
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "jobs")
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Jobs implements Serializable {

    @Id
    @Column(name = "job_id")
    private String jobId;
    @Column(name = "job_title", nullable = false)
    private String jobTitle;
    @Column(name = "min_salary")
    private Double minSalary;
    @Column(name = "max_salary")
    private Double maxSalary;

    @Override
    public String toString() {
        return "Jobs{" +
                "jobId='" + jobId + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
*/
