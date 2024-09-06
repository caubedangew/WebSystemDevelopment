/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.repository.ParkinglotRepository;
import com.huunghiathienvu.service.ParkinglotService;
import com.huunghiathienvu.service.ParkingspaceService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienVu
 */
@Service
public class ParkinglotServiceImplement implements ParkinglotService {
    @Autowired
    private ParkinglotRepository parkinglotRepo;
    
    @Autowired
    private ParkingspaceService parkingspaceSer;
    
    @Autowired 
    private Cloudinary cloudinary;

    @Override
    public Parkinglot getParkinglotById(int parkinglotId) {
        return this.parkinglotRepo.getParkinglotById(parkinglotId);
    }

    @Override
    public void addOrUpdateParkinglot(Parkinglot pl) {
        if (!pl.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(pl.getFile().getBytes(),
                            ObjectUtils.asMap("resource_type", "auto"));
                
                pl.setThumbnail(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ParkinglotServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }   
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
