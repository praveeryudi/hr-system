package com.example.demo.dao;

import com.example.demo.entity.OCFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OCFeeDAO extends JpaRepository<OCFee, Long> {

}
