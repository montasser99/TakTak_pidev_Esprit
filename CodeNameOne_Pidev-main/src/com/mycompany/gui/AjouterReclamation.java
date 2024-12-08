/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author sbs
 */
public class AjouterReclamation extends Form {
Form current;
    public AjouterReclamation(Form previous) {        
        setTitle("Ajouter une reclamation");
        setLayout(BoxLayout.y());
        //pour la creation d'un text field(win bech tekteb) + hunt (placeholder) 
       
          TextField Nom = new TextField("", "Nom");
        TextField Prenom = new TextField("", "Prenom");
        TextField Description  = new TextField("", "Déscription");
        Button btnAjouter = new Button("Ajouter");

        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(Nom.getText()==""){
                Dialog.show("Error","Nom non valide","ok",null);
                }
                else if (Prenom.getText()==""){
                Dialog.show("Error","Prenom non valide","ok",null);
                }
          
                else if (Description.getText()==""){
                Dialog.show("Error","numero de moyen de transport non valide","ok",null);
                }
                else {
                Reclamation reclam = new Reclamation();
                reclam.setNom(Nom.getText());
                reclam.setPrenom(Prenom.getText());
                reclam.setDescrec(Description.getText());
                if(ServiceReclamation.getInstance().AjoutReclamation(reclam)){
                    Dialog.show("Success", "Reclamation ajouté avec succées !","Ok",null);
                }
                else
                {
                    Dialog.show("Error","Request probleme","Ok",null);
               
                }   
                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)->{
        new GestionReclamation(current).show();                         
        });
        addAll(Nom,Prenom,Description,btnAjouter); 
    }


            
    
  
}
