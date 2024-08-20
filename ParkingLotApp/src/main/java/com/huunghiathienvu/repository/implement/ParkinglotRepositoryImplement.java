/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Parkinglot;
import com.huunghiathienvu.repository.ParkinglotRepository;
import java.util.List;
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
public class ParkinglotRepositoryImplement implements ParkinglotRepository{
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
    public List<Parkinglot> getParkinglots() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Parkinglot.findAll");
        return q.getResultList();
    }
}
