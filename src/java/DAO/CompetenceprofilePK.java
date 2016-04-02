/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Haarhus
 */
@Embeddable
public class CompetenceprofilePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "CompetenceName")
    private String competenceName;

    public CompetenceprofilePK() {
    }

    public CompetenceprofilePK(String username, String competenceName) {
        this.username = username;
        this.competenceName = competenceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompetenceName() {
        return competenceName;
    }

    public void setCompetenceName(String competenceName) {
        this.competenceName = competenceName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (competenceName != null ? competenceName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceprofilePK)) {
            return false;
        }
        CompetenceprofilePK other = (CompetenceprofilePK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.competenceName == null && other.competenceName != null) || (this.competenceName != null && !this.competenceName.equals(other.competenceName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.CompetenceprofilePK[ username=" + username + ", competenceName=" + competenceName + " ]";
    }
    
}
