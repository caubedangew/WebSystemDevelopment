/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.service.ParkinglotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ThienVu
 */
@Controller
@RequestMapping("/parkinglot")
@Transactional
public class ParkingLotController {
    @Autowired
    private ParkinglotService parkinglotSer;
    
    @GetMapping("")
    public String get(Model model) {
        model.addAttribute("parkinglot", parkinglotSer.getParkinglots());
        return "parkinglot";
    }
    
    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("parkinglot", new Parkinglot());
        return "parkinglotAddOrUpdate";
    }
}
