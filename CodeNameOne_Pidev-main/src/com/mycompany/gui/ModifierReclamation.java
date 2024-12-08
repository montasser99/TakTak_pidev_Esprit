/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author sbs
 */
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
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author pc
 */
public class ModifierReclamation extends Form{
Form current;
    public ModifierReclamation(Form previous,Reclamation r) {
          setTitle("Modifier Moyen de transport");
        setLayout(BoxLayout.y());
        TextField Nom = new TextField(r.getNom(), "Nom");
        TextField Prenom = new TextField(r.getPrenom(), "Prenom");
        TextField Description  = new TextField(r.getDescrec(), "Déscription");
        Button btnModifier = new Button("Modifier");
         
        btnModifier.addActionListener( new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent evt) {      
            if ((Nom.getText().length()==0)||(Nom.getText().length()==0)||(Prenom.getText().length()==0)||(Description.getText().length()==0)) {
                Dialog.show("Alert", "Les champs sont obligatoire !", new Command("OK"));
            } else {
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                try {
                    r.setIdr(r.getIdr());                        
                    r.setNom(r.getNom());
                    r.setPrenom(r.getPrenom());
                    r.setDescrec(r.getDescrec());

                    if(ServiceReclamation.getInstance().modifierReclamation(r)) {
                        Dialog.show("Success","Modifier avec succeés",new Command("OK"));
                        new GestionReclamation( current).show();                         

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
        addAll(Nom,Prenom,Description,btnModifier);
    
    
}
}
