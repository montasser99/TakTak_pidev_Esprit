/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

//import java.time.LocalDate;

/**
 *
 * @author 21626
 */
import java.util.Date;
import java.util.List;


public class Abonnement {

    private int id;
    private int idU;
    private String moyenTransportA;
    private Date dateA;
    private Date dateExpA;
    //private int idtypeA;
    private int etudiantA;
    private int redEtA;
   // private int redEvA;

    public Abonnement() {}

    public Abonnement(int id, int idU, String moyTrA, Date dateA,Date dateExpA,int etudiantA,int redEtA) {
        this.id = id;
        this.idU = idU;
        this.moyenTransportA = moyenTransportA;
        this.dateA = dateA;
        this.dateExpA = dateExpA;
       // this.idtypeA = idtypeA;
        this.etudiantA = etudiantA;
        this.redEtA = redEtA;
       // this.redEvA = redEvA;
    }


    public int getId() {
        return id;
    }

    public void setId(int idA) {
        this.id = id;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getMoyenTransportA() {
        return moyenTransportA;
    }

    public void setMoyTrA(String moyenTransportA) {
        this.moyenTransportA = moyenTransportA;
    }

    public Date getDateA() {
        return dateA;
    }

    public void setDateA(Date dateA) {
        this.dateA = dateA;
    }

    public Date getDateExpA() {
        return dateExpA;
    }

    public void setDateExpA(Date dateExpA) {
        this.dateExpA = dateExpA;
    }



    public int getEtudiantA() {
        return etudiantA;
    }

    public void setEtudiantA(int etudiantA) {
        this.etudiantA = etudiantA;
    }

   public int getRedEtA() {
        return redEtA;
    }

    public void setRedEtA(int redEtA) {
        this.redEtA = redEtA;
    }

 /*   public int getRedEvA() {
        return redEvA;
    }

    public void setRedEvA(int redEvA) {
        this.redEvA = redEvA;
    }
*/
    @Override
    public String toString() {
        return "Abonnement{" +
                "id=" + id +
                ", idU=" + idU +
                ", moyTrA='" + moyenTransportA + '\'' +
                ", dateA=" + dateA +
                ", dateExpA=" + dateExpA +
              //  ", idtypeA=" + idtypeA +
                ", etudiantA=" + etudiantA +
                ", redEtA=" + redEtA +
               // ", redEvA=" + redEvA +
                '}';
    }
    

    
}

   