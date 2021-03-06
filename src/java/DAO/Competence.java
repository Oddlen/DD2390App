/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haarhus
 */
@Entity
@Table(name = "competence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c"),
    @NamedQuery(name = "Competence.findByCompetenceName", query = "SELECT c FROM Competence c WHERE c.competenceName = :competenceName"),
    @NamedQuery(name = "Competence.findByDescription", query = "SELECT c FROM Competence c WHERE c.description = :description")})
public class Competence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CompetenceName")
    private String competenceName;
    @Size(max = 255)
    @Column(name = "Description")
    private String description;

    public Competence() {
    }

    public Competence(String competenceName) {
        this.competenceName = competenceName;
    }
    
    public Competence(String competenceName,String description) {
        this.competenceName = competenceName;
         this.description = description;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceName != null ? competenceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.competenceName == null && other.competenceName != null) || (this.competenceName != null && !this.competenceName.equals(other.competenceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Competence[ competenceName=" + competenceName + " ]";
    }
    
}
