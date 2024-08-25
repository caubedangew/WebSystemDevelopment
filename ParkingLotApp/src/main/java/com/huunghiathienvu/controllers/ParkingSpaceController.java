/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Parkingspace;
import com.huunghiathienvu.service.ParkinglotService;
import com.huunghiathienvu.transientpojo.ParkingspaceStatus;
import com.huunghiathienvu.service.ParkingspaceService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ThienVu
 */
@Controller
@Transactional
@RequestMapping("/parkingspace")
public class ParkingSpaceController {
    @Autowired
    private ParkinglotService parkinglotSer;
    
    @Autowired
    private ParkingspaceService parkingspaceSer;
    
    @GetMapping("")
    public String getParkingspacesByParkinglot(Model model, @RequestParam Map<String, String> params ) {
        model.addAttribute("parkingspace", this.parkingspaceSer.getParkingspaces(params));
        return "parkingspace";
    }
    
    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("parkingspace", new Parkingspace());
        model.addAttribute("status", ParkingspaceStatus.values());
        
        return "parkingspaceAddOrUpdate";
    }
    
    @GetMapping("/{parkingspaceId}")
    public String getUpdatePage(Model model, @PathVariable(value="parkingspaceId") int parkingspaceId) {
        model.addAttribute("parkinglot", this.parkinglotSer.getParkinglots(null));
        model.addAttribute("parkingspace", this.parkingspaceSer.getParkingspaceById(parkingspaceId));
        model.addAttribute("status", ParkingspaceStatus.values());
        return "parkingspaceAddOrUpdate";
    }
    
    @PostMapping("")
    public String postAddOrUpdate(Model model, 
            @ModelAttribute(value="parkingspace") @Valid Parkingspace ps,
            BindingResult rs) {
        if (rs.hasErrors())
            return "parkingspaceAddOrUpdate";
        try {
            parkingspaceSer.addOrUpdateParkingspace(ps);
            return "redirect:/parkingspace";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }
        return "parkingspaceAddOrUpdate";
    }
}
