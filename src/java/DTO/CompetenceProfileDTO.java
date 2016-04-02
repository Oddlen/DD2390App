/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Aeive
 */
public class CompetenceProfileDTO {
    
    private String comment;
    private String competence;
    private String userName;
    private String competenceDescription;
    
    public CompetenceProfileDTO(String userName, String competence,String competenceDescription, String comment){
        this.comment = comment;
        this.competence = competence;
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompetenceDescription() {
        return competenceDescription;
    }

    public void setCompetenceDescription(String competenceDescription) {
        this.competenceDescription = competenceDescription;
    }
    
    
    
}
