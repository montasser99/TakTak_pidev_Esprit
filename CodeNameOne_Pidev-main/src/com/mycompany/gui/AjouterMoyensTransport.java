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
import com.mycomany.entities.Moyenstransport;
import com.mycompany.services.ServiceMoyensTransport;

/**
 *
 * @author pc
 */
public class AjouterMoyensTransport extends Form {
Form current;
    public AjouterMoyensTransport(Form previous) {        
        setTitle("Ajouter un moyen de transport");
        setLayout(BoxLayout.y());
        //pour la creation d'un text field(win bech tekteb) + hunt (placeholder) 
        TextField Type = new TextField("","Type");
        TextField Matricule = new TextField("", "Matricule");
        TextField Capacite = new TextField("", "capacite");
        TextField Nummoy = new TextField("","Numero moyen de transport");
        Button btnAjouter = new Button("Ajouter");

        
        btnAjouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if(Type.getText()==""){
                Dialog.show("Error","Type non valide","ok",null);
                }
                else if (Matricule.getText()==""){
                Dialog.show("Error","Matricule non valide","ok",null);
                }
                else if (Capacite.getText()==""){
                Dialog.show("Error","Capacite non valide","ok",null);
                }
                else if (Nummoy.getText()==""){
                Dialog.show("Error","numero de moyen de transport non valide","ok",null);
                }
                else {
                Moyenstransport moyens = new Moyenstransport();
                moyens.setType(Type.getText());
                moyens.setMatricule(Matricule.getText());
                moyens.setCapacite((Integer.parseInt(Capacite.getText())));
                moyens.setNumMoy(Nummoy.getText());
                if(ServiceMoyensTransport.getInstance().AjoutMoyensTransport(moyens)){
                    Dialog.show("Success", "Moyens de transport ajouter avec succées !","Ok",null);
                }
                else
                {
                    Dialog.show("Error","Request probleme","Ok",null);
               
                }   
                }
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)->{
        new GestionTransport(current).show();                         
        });
        addAll(Type,Matricule,Capacite,Nummoy,btnAjouter); 
    }


            
    
  
}
