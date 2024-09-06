/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository;

import com.huunghiathienvu.pojo.Parkinglot;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienVu
 */
public interface ParkinglotRepository {
    List<Parkinglot> getParkinglots(Map<String, String> params);
    Parkinglot getParkinglotById(int parkinglotId);
    void addOrUpdateParkinglot(Parkinglot pl);
    void deleteParkinglot(int parkinglotId);
}
