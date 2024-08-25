/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.formatters;

import com.huunghiathienvu.pojo.Parkingspace;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author ThienVu
 */
public class ParkingspaceFormatter implements Formatter<Parkingspace>{

    @Override
    public String print(Parkingspace ps, Locale locale) {
        return String.valueOf(ps.getId());
    }

    @Override
    public Parkingspace parse(String parkingspaceId, Locale locale) throws ParseException {
        Parkingspace ps = new Parkingspace();
        ps.setId(Integer.parseInt(parkingspaceId));
        
        return ps;
    }
    
}
