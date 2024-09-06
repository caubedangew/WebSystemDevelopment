/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.repository.implement;

import com.huunghiathienvu.pojo.Comment;
import com.huunghiathienvu.repository.CommentRepository;
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
public class CommentRepositoryImplement implements CommentRepository {
    private static final int PAGE_SIZE = 5;
    
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Comment> getAllComments(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Comment> query = builder.createQuery(Comment.class);
        Root root = query.from(Comment.class);
        
        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();
            
            String parkinglotId = params.get("parkinglotId");
            if (parkinglotId != null && !parkinglotId.isBlank())
                predicates.add(builder.equal(root.get("parkinglotId"), Integer.parseInt(parkinglotId)));
            
            query.where(predicates.toArray(Predicate[]::new));
        }
        
        query.orderBy(builder.desc(root.get("id")));
        
        Query q = s.createQuery(query);
        
        if (params != null) {
            String page = params.get("page");
            if (page != null && !page.isEmpty()) {
                int p = Integer.parseInt(page);
                int start = (p - 1) * PAGE_SIZE;
                
                q.setFirstResult(start);
                q.setMaxResults(PAGE_SIZE);
            }
        }
        
        return q.getResultList();
    }

    @Override
    public void addOrUpdateComment(Comment cmt) {
        Session s = this.factory.getObject().getCurrentSession();
        
        if (cmt.getId() != null)
            s.merge(cmt);
        else
            s.persist(cmt);
    }

    @Override
    public void deleteComment(int commentId) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Comment cmt = getCommentById(commentId);
        if (cmt != null)
            s.delete(cmt);
    }

    @Override
    public Comment getCommentById(int commentId) {
        Session s = this.factory.getObject().getCurrentSession();
        
        return s.get(Comment.class, commentId);
    }
    
}
