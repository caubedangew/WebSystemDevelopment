/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.service.implement;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.pojo.Receipt;
import com.huunghiathienvu.repository.ReceiptRepository;
import com.huunghiathienvu.service.ParkinglotService;
import com.huunghiathienvu.service.ReceiptService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ThienVu
 */
@Service
public class ReceiptServiceImplement implements ReceiptService {
    @Autowired
    private ReceiptRepository receiptRepo;
    
    @Autowired
    private ParkinglotService parkinglotSer;
    
    @Override
    public List<Receipt> getAllReceipts(Map<String, String> params) {
        return this.receiptRepo.getAllReceipts(params);
    }

    @Override
    public List<Receipt> getCurrentUserReceipts(Map<String, String> params) {
        return this.receiptRepo.getCurrentUserReceipts(params);
    }

    @Override
    public void addOrUpdateReceipt(Receipt r) {
        Parkinglot pl = this.parkinglotSer.getParkinglotById(r.getParkingspaceId().getId());
        r.setTotalAmount(r.getTimeInterval() * pl.getPrice());
        this.receiptRepo.addOrUpdateReceipt(r);
    }

    @Override
    public void deleteReceipt(int receiptId) {
        this.receiptRepo.deleteReceipt(receiptId);
    }

    @Override
    public Receipt getReceiptByReceiptId(int receiptId) {
        return this.receiptRepo.getReceiptByReceiptId(receiptId);
    }
    
}
