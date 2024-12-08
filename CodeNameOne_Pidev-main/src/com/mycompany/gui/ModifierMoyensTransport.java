/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
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
public class ModifierMoyensTransport extends Form{
Form current;
    public ModifierMoyensTransport(Form previous,Moyenstransport m) {
          setTitle("Modifier Moyen de transport");
        setLayout(BoxLayout.y());
        TextField Type = new TextField(m.getType(), "Type");
        TextField Matricule = new TextField(m.getMatricule(), "Matricule");
        TextField Capacite = new TextField(String.valueOf(m.getCapacite()),"capacite");
        TextField Nummoy  = new TextField(m.getNumMoy(), "Numero de transport");
        Button btnModifier = new Button("Modifier");
         
        btnModifier.addActionListener( new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {      
            if ((Type.getText().length()==0)||(Matricule.getText().length()==0)||(Capacite.getText().length()==0)||(Nummoy.getText().length()==0)) {
                Dialog.show("Alert", "Les champs sont obligatoire !", new Command("OK"));
            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                try {
                    m.setIdMoy(m.getIdMoy());                        
                    m.setType(Type.getText());
                    m.setMatricule(Matricule.getText());
                    m.setCapacite((int) Float.parseFloat(Capacite.getText()));
                    m.setNumMoy(Nummoy.getText());

                    if(ServiceMoyensTransport.getInstance().modifierMoyensTransport(m)) {
                        Dialog.show("Success","Modifier avec succeés",new Command("OK"));
                        new GestionTransport(current).show();                         

                    } else {
                        Dialog.show("ERROR", "erreur de server", new Command("OK"));
                        previous.showBack();
                    }
                } catch (Exception e) {
                    Dialog.show("ERROR", e.getMessage() , new Command("OK"));
                    previous.showBack();    

                }
            }
            }
        });
        
             getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt)->{
            previous.showBack();
        });   
        addAll(Type,Matricule,Capacite,Nummoy,btnModifier);
    
    
}
}
