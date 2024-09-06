/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.controllers;

import com.huunghiathienvu.transientpojo.ChargeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ThienVu
 */
@Controller
@PropertySource("classpath:stripe.properties")
public class CheckoutController {

    @Autowired
    private Environment env;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", "pk_test_51PsvdaGFodi9uGhsf39W4otm75qHRpv3Q6G7CH0ar14jFsP9GG0VB4X88reE3NCsGqer767Pgrw4dXfLyxSs5ZHe001mHA2ipH");
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}
