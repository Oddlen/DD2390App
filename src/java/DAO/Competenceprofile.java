/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Haarhus
 */
@Entity
@Table(name = "competenceprofile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competenceprofile.findAll", query = "SELECT c FROM Competenceprofile c"),
    @NamedQuery(name = "Competenceprofile.findByUsername", query = "SELECT c FROM Competenceprofile c WHERE c.competenceprofilePK.username = :username"),
    @NamedQuery(name = "Competenceprofile.findByComment", query = "SELECT c FROM Competenceprofile c WHERE c.comment = :comment"),
    @NamedQuery(name = "Competenceprofile.findByCompetenceName", query = "SELECT c FROM Competenceprofile c WHERE c.competenceprofilePK.competenceName = :competenceName")})
public class Competenceprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CompetenceprofilePK competenceprofilePK;
    @Size(max = 255)
    @Column(name = "Comment")
    private String comment;
    @JoinColumn(name = "CompetenceName", referencedColumnName = "CompetenceName", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competence competence;
    @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public Competenceprofile() {
    }

    public Competenceprofile(CompetenceprofilePK competenceprofilePK) {
        this.competenceprofilePK = competenceprofilePK;
    }

    public Competenceprofile(String username, String competenceName) {
        this.competenceprofilePK = new CompetenceprofilePK(username, competenceName);
    }

    public CompetenceprofilePK getCompetenceprofilePK() {
        return competenceprofilePK;
    }

    public void setCompetenceprofilePK(CompetenceprofilePK competenceprofilePK) {
        this.competenceprofilePK = competenceprofilePK;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Competence getCompetence() {
        return competence;
    }

    public void setCompetence(Competence competence) {
        this.competence = competence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceprofilePK != null ? competenceprofilePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competenceprofile)) {
            return false;
        }
        Competenceprofile other = (Competenceprofile) object;
        if ((this.competenceprofilePK == null && other.competenceprofilePK != null) || (this.competenceprofilePK != null && !this.competenceprofilePK.equals(other.competenceprofilePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Competenceprofile[ competenceprofilePK=" + competenceprofilePK + " ]";
    }
    
}
