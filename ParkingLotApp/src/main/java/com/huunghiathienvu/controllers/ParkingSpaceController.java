/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.service.ParkingspaceService;
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
@RequestMapping("/parkingspace")
public class ParkingSpaceController {
    @Autowired
    private ParkingspaceService parkingspaceSer;
    
    @GetMapping("")
    public String getParkingspacesByParkinglot(Model model, @RequestParam Map<String, String> params ) {
        model.addAttribute("parkingspace", this.parkingspaceSer.getParkingspacesInParkinglot(params));
        return "parkingspace";
    }
}
