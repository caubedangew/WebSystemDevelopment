/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.pojo.Receipt;
import com.huunghiathienvu.service.ParkinglotService;
import com.huunghiathienvu.service.ParkingspaceService;
import com.huunghiathienvu.service.ReceiptService;
import com.huunghiathienvu.service.UserService;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ThienVu
 */
@Controller
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptSer;
    @Autowired
    private UserService userSer;
    @Autowired
    private ParkingspaceService parkingspaceSer;
    @Autowired
    private ParkinglotService parkinglotSer;

    @GetMapping("")
    public String getReceipts(Model model) {
        model.addAttribute("receipt", this.receiptSer.getAllReceipts(null));
        return "receipt";
    }

    @GetMapping("/add")
    public String getAddReceipt(Model model) {
        model.addAttribute("receipt", new Receipt());
        model.addAttribute("parkingspace", this.parkingspaceSer.getParkingspaces(null));
        model.addAttribute("user", this.userSer.getAllUsers());

        return "receiptAddOrUpdate";
    }

    @GetMapping("/{receiptId}")
    public String getDetailReceipt(Model model, @PathVariable(value = "receiptId") int receiptId) {
        model.addAttribute("receipt", this.receiptSer.getReceiptByReceiptId(receiptId));
        model.addAttribute("parkingspace", this.parkingspaceSer.getParkingspaces(null));
        model.addAttribute("user", this.userSer.getAllUsers());
        return "receiptAddOrUpdate";
    }

    @PostMapping("")
    public String postAddOrUpdate(Model model,
            @ModelAttribute(value = "receipt") Receipt r,
            BindingResult rs) {
        if (rs.hasErrors()) {
            return "receiptAddOrUpdate";
        }
        try {
            Parkinglot pl = this.parkinglotSer.getParkinglotById(r.getParkingspaceId().getParkinglotId().getId());
//            r.setTotalAmount(pl.getPrice() * r.getTimeInterval());

            this.receiptSer.addOrUpdateReceipt(r);
            return "redirect:/receipt";
        } catch (Exception ex) {
            model.addAttribute("errMsg", ex.getMessage());
        }
        return "receiptAddOrUpdate";
    }
}
