/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Receipt;
import com.huunghiathienvu.repository.ReceiptRepository;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ThienVu
 */
@Repository
@Transactional
public class ReceiptRepositoryImplement implements ReceiptRepository {
    private static final int PAGE_SIZE = 4;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Receipt> getAllReceipts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Query q = s.createNamedQuery("Receipt.findAll");
        
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()){
                int p = Integer.parseInt(page);
                int start = (p - 1) * PAGE_SIZE;
                
                q.setFirstResult(start);
                q.setMaxResults(PAGE_SIZE);
            }
        }
        
        return q.getResultList();
    }

    @Override
    public List<Receipt> getCurrentUserReceipts(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Receipt> query = builder.createQuery(Receipt.class);
        Root root = query.from(Receipt.class);
        
        if (params != null) {
            String userId = params.get("userId");
            if (userId != null)
                query.where(builder.equal(root.get("userId"), Integer.parseInt(userId)));
        }
        
        Query q = s.createQuery(query);
        
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()){
                int p = Integer.parseInt(page);
                int start = (p - 1) * PAGE_SIZE;
                
                q.setFirstResult(start);
                q.setMaxResults(PAGE_SIZE);
            }
        }
        
        return q.getResultList();
    }

    @Override
    public void addOrUpdateReceipt(Receipt r) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (r.getId() != null)
            s.merge(r);
        else
            s.persist(r);
    }

    @Override
    public void deleteReceipt(int receiptId) {
        Session s = this.factory.getObject().getCurrentSession();
        Receipt r = getReceiptByReceiptId(receiptId);
        if (r != null)
            s.delete(r);
    }

    @Override
    public Receipt getReceiptByReceiptId(int receiptId) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Receipt.class, receiptId);
    }
    
}
