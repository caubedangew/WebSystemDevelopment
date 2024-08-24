/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.formatters;

import com.huunghiathienvu.pojo.Parkinglot;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ThienVu
 */
public class ParkinglotFormatter implements Formatter<Parkinglot> {

    @Override
    public String print(Parkinglot parkinglot, Locale locale) {
        return String.valueOf(parkinglot.getId());
    }

    @Override
    public Parkinglot parse(String parkinglotId, Locale locale) throws ParseException {
        Parkinglot pl = new Parkinglot();
        pl.setId(Integer.parseInt(parkinglotId));
        
        return pl;
    }
    
}
