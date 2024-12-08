/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *    
 * @author ASUS
 */
public class HomeForm extends Form{
    
   // Form hi = this;  

    public HomeForm() {
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnADD = new Button("ADD Ask");
        Button btnSHOW = new Button("Show Asks");
        
        btnADD.addActionListener((evt) -> {
            AddForm addForm = new AddForm(this);
             addForm.show();
        });
        btnSHOW.addActionListener((evt) -> {
            ShowForm showForm = new ShowForm(this);
            showForm.show();
        });
        
//        add(btnADD);
//        add(btnSHOW);
        addAll(btnADD,btnSHOW);
    }
    
    
}
