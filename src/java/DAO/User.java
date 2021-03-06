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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "user")
@XmlRootElement
public class User implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<Competenceprofile> competenceprofileCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "Password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Application> applicationCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Competenceprofile competenceprofile;
    @JoinColumn(name = "Type", referencedColumnName = "Type")
    @ManyToOne(optional = false)
    private Accounttype type;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Application> getApplicationCollection() {
        return applicationCollection;
    }

    public void setApplicationCollection(Collection<Application> applicationCollection) {
        this.applicationCollection = applicationCollection;
    }

    public Competenceprofile getCompetenceprofile() {
        return competenceprofile;
    }

    public void setCompetenceprofile(Competenceprofile competenceprofile) {
        this.competenceprofile = competenceprofile;
    }

    public Accounttype getType() {
        return type;
    }

    public void setType(Accounttype type) {
        this.type = type;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @XmlTransient
    public Collection<Competenceprofile> getCompetenceprofileCollection() {
        return competenceprofileCollection;
    }

    public void setCompetenceprofileCollection(Collection<Competenceprofile> competenceprofileCollection) {
        this.competenceprofileCollection = competenceprofileCollection;
    }
}
