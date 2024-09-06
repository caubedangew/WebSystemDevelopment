/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Receipt;
import com.huunghiathienvu.service.ParkingspaceService;
import com.huunghiathienvu.service.ReceiptService;
import com.huunghiathienvu.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ThienVu
 */
@RestController
@RequestMapping("/api/receipt")
public class ApiReceiptController {

    @Autowired
    private ReceiptService receiptSer;
    
    @Autowired
    private ParkingspaceService parkingspaceSer;
    
    @Autowired
    private UserService userSer;

    @DeleteMapping("/{receiptId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReceipt(@PathVariable(value = "receiptId") int receiptId) {
        this.receiptSer.deleteReceipt(receiptId);
    }

    @PostMapping("/add")
    public ResponseEntity<String> postReceipt(@RequestBody Map<String, String> params) {
        String parkingspaceId = params.get("parkingspaceId");
        String timeInterval = params.get("timeInterval");
        if (parkingspaceId != null && timeInterval != null) {
            Receipt r = new Receipt();

            r.setParkingspaceId(this.parkingspaceSer.getParkingspaceById(Integer.parseInt(parkingspaceId)));
            r.setTimeInterval(Double.parseDouble(timeInterval));
            r.setUserId(this.userSer.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));

            this.receiptSer.addOrUpdateReceipt(r);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Got some error", HttpStatus.BAD_REQUEST);
    }

}
