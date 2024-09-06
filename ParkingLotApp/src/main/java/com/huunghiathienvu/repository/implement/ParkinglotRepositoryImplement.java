/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.repository.ParkinglotRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
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
public class ParkinglotRepositoryImplement implements ParkinglotRepository{
    private static final int PAGE_SIZE = 3;
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Parkinglot getParkinglotById(int parkinglotId) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Parkinglot.class, parkinglotId);
    }
    
    @Override
    public void addOrUpdateParkinglot(Parkinglot pl) {
        Session s = this.factory.getObject().getCurrentSession();
        if (pl.getId() != null)
            s.merge(pl);
        else
            s.persist(pl);
    }
    
    @Override
    public void deleteParkinglot(int parkinglotId) {
        Session s = this.factory.getObject().getCurrentSession();
        Parkinglot pl = getParkinglotById(parkinglotId);
        if (pl != null)
            s.delete(pl);
    }

    @Override
    public List<Parkinglot> getParkinglots(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery(Parkinglot.class);
        Root root = query.from(Parkinglot.class);
        
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            String address = params.get("address");
            if (address != null)
                predicates.add(builder.like(root.get("address"), String.format("%%%s%%", address)));
            
            query.where(predicates.toArray(Predicate[]::new));
            
            String price = params.get("price");
            if (price != null) {
                if ("asc".equals(price))
                    query.orderBy(builder.asc(root.get("price")));
                else
                    query.orderBy(builder.desc(root.get("price")));
            }
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
}
