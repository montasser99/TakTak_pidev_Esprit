/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ASUS
 */
public class Demande {
     private int id;
    private String nomC;
    private String moyen;
    private String dated;
    private String datea;
    private String permis;
    private String email;
    public Demande() {
    }
    
    

   

    public Demande(String nomC, String moyen, String dated, String datea, String permis, String email) {
        this.nomC = nomC;
        this.moyen = moyen;
        this.dated = dated;
        this.datea = datea;
        this.permis = permis;
        this.email = email;
    }

    public Demande(int id, String nomC, String moyen, String dated, String datea, String permis, String email) {
        this.id = id;
        this.nomC = nomC;
        this.moyen = moyen;
        this.dated = dated;
        this.datea = datea;
        this.permis = permis;
        this.email = email;
    }

    

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getMoyen() {
        return moyen;
    }

    public void setMoyen(String moyen) {
        this.moyen = moyen;
    }

    public String getDateD() {
        return dated;
    }

    public void setDateD(String dated) {
        this.dated = dated;
    }

    public String getDateA() {
        return datea;
    }

    public void setDateA(String datea) {
        this.datea = datea;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Demande{" + "id=" + id + ", nomC=" + nomC + ", moyen=" + moyen + ", dated=" + dated + ", datea=" + datea + ", permis=" + permis + ", email=" + email + '}';
    }

    
    
    
    
    
}
