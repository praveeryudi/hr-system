package com.example.demo.dao;

import com.example.demo.entity.MonthYear;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.demo.util.MaintenanceUtil.*;

@Repository
@Slf4j
public class MonthYearRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MonthYearRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MonthYear> getMonthsList(String timePeriod) {
        Connection conn = null;
        PreparedStatement pStmt = null;
        ResultSet resultSet = null;
        String sql = null;
        switch(timePeriod) {
            case "3":
                sql = get3MonthsQuery();
                break;

            case "6":
                sql = get6MonthsQuery();
                break;

            case "9":
                sql = get12MonthsQuery();
                break;
        }

        List<MonthYear> monthYearList = new ArrayList<>();
        try {
            conn = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection();
            pStmt = conn.prepareStatement(sql);
            resultSet = pStmt.executeQuery();
            MonthYear monthYear;
            while(resultSet.next()) {
                String month = resultSet.getString("Month");
                month = month.toUpperCase().substring(0, 3);
                String year = resultSet.getString("Year");
                monthYear = new MonthYear(month, year);
                monthYearList.add(monthYear);
            }
        } catch(Exception e) {
            log.error("Exception in query execution {}{}", e.getMessage(), e);
        } finally {
            try {
                if(null != resultSet)
                    resultSet.close();
                if(null != pStmt)
                    pStmt.close();
                if(null != conn)
                    conn.close();
            } catch(Exception e) {
                log.error("Exception in closing connection resources {}{}", e.getMessage(), e);
            }
        }
        return monthYearList;
    }
}
