/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Parkingspace;
import com.huunghiathienvu.service.ParkingspaceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThienVu
 */
@RestController
@RequestMapping("/api/parkingspace")
public class ApiParkingspaceController {
    @Autowired
    private ParkingspaceService parkingspaceSer;
    
    @GetMapping("")
    public ResponseEntity<List<Parkingspace>> getParkingspaces (Map<String, String> params) {
        List<Parkingspace> parkingspaces = this.parkingspaceSer.getParkingspacesInParkinglot(params);
        
        return new ResponseEntity<>(parkingspaces, HttpStatus.OK);
    }
}
