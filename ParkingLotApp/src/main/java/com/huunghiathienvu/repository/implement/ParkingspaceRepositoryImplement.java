/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Parkingspace;
import com.huunghiathienvu.repository.ParkingspaceRepository;
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
public class ParkingspaceRepositoryImplement implements ParkingspaceRepository{
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Parkingspace> getParkingspacesInParkinglot(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Parkingspace> query = builder.createQuery(Parkingspace.class);
        Root root = query.from(Parkingspace.class);
        
        String parkinglotId = params.get("parkinglotId");
        if (parkinglotId != null)
            query.where(builder.equal(root.get("parkinglotId"), Integer.parseInt(parkinglotId)));
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }

    @Override
    public void addOrUpdateParkingspace(Parkingspace ps) {
        Session s = this.factory.getObject().getCurrentSession();
        s.merge(ps);
    }
    
}
