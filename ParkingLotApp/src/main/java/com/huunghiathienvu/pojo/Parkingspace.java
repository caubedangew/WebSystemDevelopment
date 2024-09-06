/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.huunghiathienvu.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ThienVu
 */
@Entity
@Table(name = "parkingspace")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parkingspace.findAll", query = "SELECT p FROM Parkingspace p"),
    @NamedQuery(name = "Parkingspace.findById", query = "SELECT p FROM Parkingspace p WHERE p.id = :id"),
    @NamedQuery(name = "Parkingspace.findByStt", query = "SELECT p FROM Parkingspace p WHERE p.stt = :stt"),
    @NamedQuery(name = "Parkingspace.findByStatus", query = "SELECT p FROM Parkingspace p WHERE p.status = :status")})
public class Parkingspace implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "stt")
    private Integer stt;
    @Size(max = 4)
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkingspaceId")
    @JsonIgnore
    private Set<Receipt> receiptSet;
    @JoinColumn(name = "parkinglot_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Parkinglot parkinglotId;

    public Parkingspace() {
    }

    public Parkingspace(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Set<Receipt> getReceiptSet() {
        return receiptSet;
    }

    public void setReceiptSet(Set<Receipt> receiptSet) {
        this.receiptSet = receiptSet;
    }

    public Parkinglot getParkinglotId() {
        return parkinglotId;
    }

    public void setParkinglotId(Parkinglot parkinglotId) {
        this.parkinglotId = parkinglotId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parkingspace)) {
            return false;
        }
        Parkingspace other = (Parkingspace) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huunghiathienvu.pojo.Parkingspace[ id=" + id + " ]";
    }
    
}
