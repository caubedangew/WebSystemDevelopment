/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository;

import com.huunghiathienvu.pojo.Parkinglot;
import java.util.List;

/**
 *
 * @author ThienVu
 */
public interface ParkinglotRepository {
    List<Parkinglot> getParkinglots();
    Parkinglot getParkinglotById(int parkinglotId);
    void addOrUpdateParkinglot(Parkinglot pl);
    void deleteParkinglot(int parkinglotId);
}
