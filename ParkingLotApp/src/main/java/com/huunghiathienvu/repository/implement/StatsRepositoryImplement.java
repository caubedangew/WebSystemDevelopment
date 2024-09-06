/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.pojo.Parkingspace;
import com.huunghiathienvu.pojo.Receipt;
import com.huunghiathienvu.repository.StatsRepository;
import java.util.List;
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
public class StatsRepositoryImplement implements StatsRepository {
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getRevenueByMonthYear(int year, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root rootPL = query.from(Parkinglot.class);
        Root rootPS = query.from(Parkingspace.class);
        Root rootR = query.from(Receipt.class);
        
        query.where(builder.equal(rootPL.get("id"), rootPS.get("parkinglotId")),
                builder.equal(rootPS.get("id"), rootR.get("parkingspaceId")),
                builder.equal(builder.function("YEAR", Integer.class, rootR.get("createdDate")), year));
        
        query.multiselect(builder.function(period, Integer.class, rootR.get("createdDate")),
                builder.sum(rootR.get("totalAmount")));
        
        query.groupBy(builder.function(period, Integer.class, rootR.get("createdDate")));
        
        query.orderBy(builder.desc(builder.sum(rootR.get("totalAmount"))));
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public List<Object[]> getRevenueOfEachParkinglotByMonthYear(int year, String period, int periodValue) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root rootPL = query.from(Parkinglot.class);
        Root rootPS = query.from(Parkingspace.class);
        Root rootR = query.from(Receipt.class);
        
        query.where(builder.equal(rootPL.get("id"), rootPS.get("parkinglotId")),
                builder.equal(rootPS.get("id"), rootR.get("parkingspaceId")),
                builder.equal(builder.function("YEAR", Integer.class, rootR.get("createdDate")), year),
                builder.equal(builder.function(period, Integer.class, rootR.get("createdDate")), periodValue));
        
        query.multiselect(rootPL.get("id"),
                builder.sum(rootR.get("totalAmount")),
                rootPL.get("address"));
        
        query.groupBy(rootPL.get("id"), rootPL.get("address"));
        
        query.orderBy(builder.desc(builder.sum(rootR.get("totalAmount"))));
        
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }
    
}
