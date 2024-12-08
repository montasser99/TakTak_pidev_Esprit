/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycomany.entities.Abonnement;
import com.mycomany.entities.Moyenstransport;
import com.mycompany.services.AbnService;
import com.mycompany.services.ServiceMoyensTransport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author pc
 */
public class AjouterAbonnement extends Form {
 Form current;   



/**
 *
 * @author pc
 */



        public AjouterAbonnement() {
    

    setTitle("Ajouter");
        setLayout(BoxLayout.y());
        //pour la creation d'un text field(win bech tekteb) + hunt (placeholder) 
        TextField idU = new TextField("","idUtilisateur");
        TextField moyenTransportA = new TextField("", "Moyenne de transport");
        
        Picker dateA = new Picker();
        dateA.setType(Display.PICKER_TYPE_DATE);
        dateA.setDate(new Date());
        
        
        Picker dateExpA = new Picker();
        dateExpA.setType(Display.PICKER_TYPE_DATE);
        dateExpA.setDate(new Date());
        
         TextField etudiantA = new TextField("","Etudiant Abonner");

         TextField redEtA = new TextField("","Reduction d'abonnement");


        Button btnAjouter = new Button("Ajouter");

        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
      
                Abonnement abonns = new Abonnement();
                abonns.setIdU(Integer.parseInt(idU.getText()));
                abonns.setMoyTrA(moyenTransportA.getText());
// Get the string date from the picker
String dateAStr = String.valueOf(dateA.getText());
// Parse the string date into a Date object
Date dateA;
                try {
                    dateA = new SimpleDateFormat("yyyy-MM-dd").parse(dateAStr);
                    abonns.setDateA(dateA);
                } catch (ParseException ex) {
                    System.out.println("erreur");
                }
// Set the dateA field in the Abonnement object

// Repeat the same for the dateExpA field
Date dateExp;
                 try {
                String dateExpAStr = String.valueOf(dateExpA.getText());
                 dateExp = new SimpleDateFormat("yyyy-MM-dd").parse(dateExpAStr);
                abonns.setDateExpA(dateExp);
                 } catch (ParseException ex) {
                     System.out.println("erreur");                                }
                abonns.setEtudiantA(Integer.parseInt(etudiantA.getText()));
                abonns.setRedEtA(Integer.parseInt(redEtA.getText()));


                if(AbnService.getInstance().addAbonnement(abonns)){
                    Dialog.show("Success", "Abonnement ajouter avec succées !","Ok",null);
                }
                else
                {
                    Dialog.show("Error","Request probleme","Ok",null);
               
                }   
                }
         
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)->{
        new GestionAbonnement(current).show();                         
        });
        addAll(idU, moyenTransportA, dateA, dateExpA, etudiantA, redEtA, btnAjouter);
    }
    
  
}


