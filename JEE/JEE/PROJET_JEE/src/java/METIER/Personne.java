/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package METIER;

/**
 *
 * @author hp
 */
public class Personne {
    
    private Long id_pers;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String tel;
    private String password;
    private String role;
    private Long id_fct;
    private Long id_grade;
    private Long id_etat;

    public Long getId_pers() {
        return id_pers;
    }

    public void setId_pers(Long id_pers) {
        this.id_pers = id_pers;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    

    public Long getId_fct() {
        return id_fct;
    }

    public void setId_fct(Long id_fct) {
        this.id_fct = id_fct;
    }

    public Long getId_grade() {
        return id_grade;
    }

    public void setId_grade(Long id_grade) {
        this.id_grade = id_grade;
    }

    public Long getId_etat() {
        return id_etat;
    }

    public void setId_etat(Long id_etat) {
        this.id_etat = id_etat;
    }

    

    
}
