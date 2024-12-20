/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.service.ParkinglotService;
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
@RequestMapping("/parkinglot")
@Transactional
public class ParkingLotController {
    @Autowired
    private ParkinglotService parkinglotSer;
    
    @Autowired
    private ParkingspaceService parkingspaceSer;
    
    @GetMapping("")
    public String getListPage(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("parkinglot", this.parkinglotSer.getParkinglots(params));
        return "parkinglot";
    }
    
    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("parkinglot", new Parkinglot());
        return "parkinglotAddOrUpdate";
    }
    
    @GetMapping("/{parkinglotId}")
    public String getUpdatePage(Model model, @PathVariable(value = "parkinglotId") int id) {
        model.addAttribute("parkinglot", this.parkinglotSer.getParkinglotById(id));
        return "parkinglotAddOrUpdate";
    }
    
    @PostMapping("")
    public String createOrUpdate(Model model, 
            @ModelAttribute(value="parkinglot") Parkinglot pl
            ) {
//        if (rs.hasErrors())
//            return "parkinglotAddOrUpdate";
        try {
            this.parkinglotSer.addOrUpdateParkinglot(pl);
            return "redirect:/parkinglot";
        }
        catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }
        
        return "parkinglotAddOrUpdate";
    }
}
