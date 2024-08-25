/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.service.ParkinglotService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ThienVu
 */
@Controller
@RequestMapping("/api/parkinglot")
@Transactional
public class ApiParkingLotController {
    @Autowired
    private ParkinglotService parkinglotSer;
    
    @DeleteMapping("/{parkinglotId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParkingLot(@PathVariable(value="parkinglotId") int parkinglotId) {
        this.parkinglotSer.deleteParkinglot(parkinglotId);
    }
    
    @GetMapping("")
    @CrossOrigin
    public ResponseEntity<List<Parkinglot>> getListParkinglot(@RequestParam(required = false) Map<String, String> params) {
        List<Parkinglot> parkinglots = this.parkinglotSer.getParkinglots(params);
        return new ResponseEntity<>(parkinglots, HttpStatus.OK);
    }
    
}
