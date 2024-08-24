/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Receipt;
import com.huunghiathienvu.repository.ReceiptRepository;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ThienVu
 */
@Repository
public class ReceiptRepositoryImplement implements ReceiptRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Receipt> getAllReceipts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Query q = s.createQuery("");
        return q.getResultList();
    }

    @Override
    public List<Receipt> getCurrentUserReceipts(int userId) {
        Session s = this.factory.getObject().getCurrentSession();
    }

    @Override
    public void addOrUpdateReceipt(Receipt r) {
        Session s = this.factory.getObject().getCurrentSession();
    }

    @Override
    public void deleteReceipt(int receiptId) {
        Session s = this.factory.getObject().getCurrentSession();
    }

    @Override
    public Receipt getReceiptByReceiptId(int receiptId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
