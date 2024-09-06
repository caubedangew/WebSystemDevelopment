/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.huunghiathienvu.repository.StatsRepository;
import com.huunghiathienvu.service.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienVu
 */

@Service
public class StatsServiceImplement implements StatsService {
    
    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<Object[]> getRevenueByMonthYear(int year, String period) {
        return this.statsRepo.getRevenueByMonthYear(year, period);
    }

    @Override
    public List<Object[]> getRevenueOfEachParkinglotByMonthYear(int year, String period, int periodValue) {
        return this.statsRepo.getRevenueOfEachParkinglotByMonthYear(year, period, periodValue);
    }
    
}
