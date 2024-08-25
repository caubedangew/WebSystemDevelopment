/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository;

import com.huunghiathienvu.pojo.Receipt;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ThienVu
 */
public interface ReceiptRepository {
    List<Receipt> getAllReceipts(Map<String, String> params);
    List<Receipt> getCurrentUserReceipts(Map<String, String> params);
    void addOrUpdateReceipt(Receipt r);
    void deleteReceipt(int receiptId);
    Receipt getReceiptByReceiptId(int receiptId);
}
