/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.repository.ParkinglotRepository;
import com.huunghiathienvu.service.ParkinglotService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienVu
 */
@Service
public class ParkinglotServiceImplement implements ParkinglotService {
    @Autowired
    private ParkinglotRepository parkinglotRepo;

    @Override
    public Parkinglot getParkinglotById(int parkinglotId) {
        return this.parkinglotRepo.getParkinglotById(parkinglotId);
    }

    @Override
    public void addOrUpdateParkinglot(Parkinglot pl) {
        this.parkinglotRepo.addOrUpdateParkinglot(pl);
    }

    @Override
    public void deleteParkinglot(int parkinglotId) {
        this.parkinglotRepo.deleteParkinglot(parkinglotId);
    }

    @Override
    public List<Parkinglot> getParkinglots(Map<String, String> params) {
        return this.parkinglotRepo.getParkinglots(params);
    }
    
    
}
