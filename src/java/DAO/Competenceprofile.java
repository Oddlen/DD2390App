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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Competenceprofile.findByUsername", query = "SELECT c FROM Competenceprofile c WHERE c.username = :username"),
    @NamedQuery(name = "Competenceprofile.findByComment", query = "SELECT c FROM Competenceprofile c WHERE c.comment = :comment")})
public class Competenceprofile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Username")
    private String username;
    @Size(max = 45)
    @Column(name = "Comment")
    private String comment;
    @JoinColumn(name = "CompetenceName", referencedColumnName = "CompetenceName")
    @ManyToOne(optional = false)
    private Competence competenceName;
    @JoinColumn(name = "Username", referencedColumnName = "Username", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Competenceprofile() {
    }

    public Competenceprofile(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Competence getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(Competence competenceName) {
        this.competenceName = competenceName;
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
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competenceprofile)) {
            return false;
        }
        Competenceprofile other = (Competenceprofile) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Competenceprofile[ username=" + username + " ]";
    }
    
}
