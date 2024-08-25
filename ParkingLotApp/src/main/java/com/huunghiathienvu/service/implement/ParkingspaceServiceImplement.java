/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.huunghiathienvu.pojo.Parkingspace;
import com.huunghiathienvu.repository.ParkingspaceRepository;
import com.huunghiathienvu.service.ParkingspaceService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienVu
 */
@Service
public class ParkingspaceServiceImplement implements ParkingspaceService{
    @Autowired 
    private ParkingspaceRepository parkingspaceRepo;

    @Override
    public List<Parkingspace> getParkingspaces(Map<String, String> params) {
        return this.parkingspaceRepo.getParkingspaces(params);
    }

    @Override
    public void addOrUpdateParkingspace(Parkingspace ps) {
        this.parkingspaceRepo.addOrUpdateParkingspace(ps);
    }

    @Override
    public Parkingspace getParkingspaceById(int parkingspaceId) {
        return this.parkingspaceRepo.getParkingspaceById(parkingspaceId);
    }
    
}
