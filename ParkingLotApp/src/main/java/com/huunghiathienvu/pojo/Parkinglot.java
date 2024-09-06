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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ThienVu
 */
@Entity
@Table(name = "parkinglot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parkinglot.findAll", query = "SELECT p FROM Parkinglot p"),
    @NamedQuery(name = "Parkinglot.findById", query = "SELECT p FROM Parkinglot p WHERE p.id = :id"),
    @NamedQuery(name = "Parkinglot.findByAddress", query = "SELECT p FROM Parkinglot p WHERE p.address = :address"),
    @NamedQuery(name = "Parkinglot.findByQuantity", query = "SELECT p FROM Parkinglot p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Parkinglot.findByPrice", query = "SELECT p FROM Parkinglot p WHERE p.price = :price"),
    @NamedQuery(name = "Parkinglot.findByThumbnail", query = "SELECT p FROM Parkinglot p WHERE p.thumbnail = :thumbnail")})
public class Parkinglot implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "quantity")
    private Integer quantity;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Size(max = 255)
    @Column(name = "thumbnail")
    private String thumbnail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkinglotId")
    @JsonIgnore
    private Set<Comment> commentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parkinglotId")
    @JsonIgnore
    private Set<Parkingspace> parkingspaceSet;
    
    @Transient
    private MultipartFile file;

    public Parkinglot() {
    }

    public Parkinglot(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @XmlTransient
    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @XmlTransient
    public Set<Parkingspace> getParkingspaceSet() {
        return parkingspaceSet;
    }

    public void setParkingspaceSet(Set<Parkingspace> parkingspaceSet) {
        this.parkingspaceSet = parkingspaceSet;
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
        if (!(object instanceof Parkinglot)) {
            return false;
        }
        Parkinglot other = (Parkinglot) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.huunghiathienvu.pojo.Parkinglot[ id=" + id + " ]";
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
