/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Haarhus
 */
@Entity
@Table(name = "position")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Position.findAll", query = "SELECT p FROM Position p"),
    @NamedQuery(name = "Position.findById", query = "SELECT p FROM Position p WHERE p.id = :id"),
    @NamedQuery(name = "Position.findByPosition", query = "SELECT p FROM Position p WHERE p.position = :position"),
    @NamedQuery(name = "Position.findByDescription", query = "SELECT p FROM Position p WHERE p.description = :description"),
    @NamedQuery(name = "Position.findUnApliedBy", query = "SELECT p FROM Position p WHERE p.id not in (\"SELECT a:id FROM Application a WHERE a.username = :username\")"),
    @NamedQuery(name = "Position.findApliedBy", query = "SELECT p FROM Position p WHERE p.id in (\"SELECT a:id FROM Application a WHERE a.username = :username\")"),
    @NamedQuery(name = "Position.findByCompany", query = "SELECT p FROM Position p WHERE p.company = :company"),})

public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Position")
    private String position;
    @Size(max = 240)
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "positionID")
    private Collection<Application> applicationCollection;
    @JoinColumn(name = "Company", referencedColumnName = "Name")
    @ManyToOne(optional = false)
    private Company company;

    public Position() {
    }

    public Position(Integer id) {
        this.id = id;
    }

    public Position(Integer id, String position) {
        this.id = id;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
        if (!(object instanceof Position)) {
            return false;
        }
        Position other = (Position) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Position[ id=" + id + " ]";
    }
    
}
