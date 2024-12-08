/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

import java.util.Date;

public class Reclamation {
    private int idr;
    private String nom;
    private String prenom;
    private Date dater;
    private String descrec;

    public Reclamation() {
    }

    public Reclamation(int idr, String nom, String prenom, Date dater, String descrec) {
        this.idr = idr;
        this.nom = nom;
        this.prenom = prenom;
        this.dater = dater;
        this.descrec = descrec;
    }

    public Reclamation(String nom, String prenom, Date dater, String descrec) {
        this.nom = nom;
        this.prenom = prenom;
        this.dater = dater;
        this.descrec = descrec;
    }

    public Reclamation(String nom, String prenom, String descrec) {
        this.nom = nom;
        this.prenom = prenom;
        this.descrec = descrec;
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

    public int getIdr() {
        return idr;
    }

    public void setIdr(int idr) {
        this.idr = idr;
    }

    public Date getDater() {
        return dater;
    }

    public void setDater(Date dater) {
        this.dater = dater;
    }

    public String getDescrec() {
        return descrec;
    }

    public void setDescrec(String descrec) {
        this.descrec = descrec;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idr=" + idr + ", nom=" + nom + ", prenom=" + prenom + ", dater=" + dater + ", descrec=" + descrec + '}';
    }
}
