/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author pc
 */
public class AcceuilForm extends Form{
Form current;

    public AcceuilForm() {
    
        current=this;
        setTitle("Page d'acceuil");
        setLayout(BoxLayout.y());
        Button btnTransport = new Button("Gestion moyenne de transport");
        Button btnReservztion = new Button("Affiche Reservation");
        btnTransport.addActionListener((evt)-> new GestionTransport(current).show());
        btnReservztion.addActionListener((evt)->new AffichageReservation(current).show()); 
        addAll(btnTransport,btnReservztion);
        
        // add back button to toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        new BaseForm().show();                      

        });
    }
    
    
    
}
