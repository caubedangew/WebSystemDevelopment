/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.service.StatsService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThienVu
 */
@Controller
@RequestMapping("/stats")
public class StatsController {
    
    @Autowired
    private StatsService statsSer;
   
    @GetMapping("/revenue")
    public String getRevenue(Model model, @RequestParam(required = false) Map<String, String> params) {
        String period = params.get("period");
        if (period == null || period.isEmpty())
            period = "MONTH";
        String yearParam = params.get("year");
        
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(date);
        
        if (yearParam != null && !yearParam.isBlank())
            year = yearParam;
        int years = Integer.parseInt(year);
        model.addAttribute("year", years);
        model.addAttribute("period", period);
        model.addAttribute("revenues", this.statsSer.getRevenueByMonthYear(Integer.parseInt(year), period));
        return "revenueStats";
    }
    
    @GetMapping("/parking")
    public String getParkingStats(Model model, @RequestParam(required = false) Map<String, String> params) {
        String period = params.get("period");
        if (period == null || period.isEmpty())
            period = "MONTH";
        String yearParam = params.get("year");
        
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String year = df.format(date);
        
        if (yearParam != null && !yearParam.isBlank())
            year = yearParam;
        int years = Integer.parseInt(year);
        
        int periodValue = 1;
        String periodV = params.get("periodValue");
        if (periodV != null && !periodV.isBlank())
            periodValue = Integer.parseInt(periodV);
        model.addAttribute("year", years);
        model.addAttribute("period", period);
        model.addAttribute("periodValue", periodValue);
        model.addAttribute("revenues", this.statsSer.getRevenueOfEachParkinglotByMonthYear(years, period, periodValue));
        return "revenueStats";
    }
}
