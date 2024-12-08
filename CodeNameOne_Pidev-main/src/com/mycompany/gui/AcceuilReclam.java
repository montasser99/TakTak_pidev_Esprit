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
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author pc
 */
public class AcceuilReclam extends Form{
Form current;

    public AcceuilReclam() {
    
        current=this;
        setTitle("Page d'acceuil");
        setLayout(BoxLayout.y());
        Button btnReclam = new Button("Gestion de Réclamation");
        btnReclam.addActionListener((evt)-> new GestionReclamation(current).show());
        add(btnReclam);
//         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
//        new BaseForm().show();                      
//
//        });
//        
    
    }
    
    
    
}
