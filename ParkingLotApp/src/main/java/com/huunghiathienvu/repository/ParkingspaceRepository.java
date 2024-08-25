/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.huunghiathienvu.repository;

import com.huunghiathienvu.pojo.Parkingspace;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienVu
 */
public interface ParkingspaceRepository {
    List<Parkingspace> getParkingspaces(Map<String, String> params);
    void addOrUpdateParkingspace(Parkingspace ps);
    Parkingspace getParkingspaceById(int parkingspaceId);
}
