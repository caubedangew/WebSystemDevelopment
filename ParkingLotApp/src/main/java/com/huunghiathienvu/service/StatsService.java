/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.huunghiathienvu.service;

import java.util.List;

/**
 *
 * @author ThienVu
 */
public interface StatsService {
    List<Object[]> getRevenueByMonthYear(int year, String period);
    List<Object[]> getRevenueOfEachParkinglotByMonthYear(int year, String period, int periodValue);
}
