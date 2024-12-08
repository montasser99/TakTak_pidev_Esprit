/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Demande;
import com.mycompany.services.ServiceDemande;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ShowForm extends Form {

    public ShowForm(Form previous) {

        ArrayList<Demande> demandes;
        setTitle("List");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

        demandes = ServiceDemande.getInstance().getAllDemandes();
        for (Demande d : demandes) {
            Container item = new Container(new BorderLayout());
            Style demandeStyle = item.getAllStyles();
            demandeStyle.setMarginTop(5);
            demandeStyle.setMarginBottom(5);
            demandeStyle.setBorder(Border.createLineBorder(1, 0xcccccc));
            demandeStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
            demandeStyle.setPadding(5, 5, 5, 5);
            demandeStyle.setBgColor(0xf4f4f4);
            demandeStyle.setBgTransparency(255);

            Painter borderPainter = new Painter() {
                @Override
                public void paint(Graphics g, Rectangle rect) {
                    g.setColor(0xcccccc);
                    g.drawRect(rect.getX(), rect.getY(), rect.getWidth() - 1, rect.getHeight() - 1);
                }

            };

            demandeStyle.setBgPainter(borderPainter);
            Button acceptButton = new Button("Accept");
            Button refuseButton = new Button("Refuse");
            acceptButton.addActionListener((evt) -> {
               
                if (ServiceDemande.getInstance().acceptSMS()) {
                        Dialog.show("success", "SMS sent successfully", "ok", null);
                    } else {
                        Dialog.show("Error", "Request Error", "ok", null);
                    }
            });
            
            refuseButton.addActionListener((evt) -> {
                if (ServiceDemande.getInstance().refuseSMS()) {
                        Dialog.show("success", "SMS sent successfully", "ok", null);
                    } else {
                        Dialog.show("Error", "Request Error", "ok", null);
                    }
            });

// Apply button styles
            com.codename1.ui.plaf.Style acceptButtonStyle = acceptButton.getAllStyles();
            acceptButtonStyle.setBgColor(0x4CAF50); // Green color for Accept button
            acceptButtonStyle.setFgColor(0xFFFFFF); // White text color for Accept button

            com.codename1.ui.plaf.Style refuseButtonStyle = refuseButton.getAllStyles();
            refuseButtonStyle.setBgColor(0xFF0000); // Red color for Refuse button
            refuseButtonStyle.setFgColor(0xFFFFFF); // White text color for Refuse button

// Add buttons to item container
// ... rest of the code ...
            Label nomLabel = new Label("Nom Circuit:");
            nomLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            nomLabel.getAllStyles().setFgColor(0x49c5ff);
            nomLabel.setUIID("NomLabel");

            Label nomValue = new Label(d.getNomC());
            nomValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            nomValue.getAllStyles().setFgColor(0x000000);
            nomValue.setUIID("NomValue");

            Container nomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            nomContainer.add(nomLabel);
            nomContainer.add(nomValue);

            Label moyenLabel = new Label("Moyen de transport:");
            moyenLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            moyenLabel.getAllStyles().setFgColor(0x49c5ff);
            moyenLabel.setUIID("MoyenLabel");

            Label moyenValue = new Label(d.getMoyen());
            moyenValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            moyenValue.getAllStyles().setFgColor(0x666666);
            moyenValue.setUIID("MoyenValue");

            Container moyenContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            moyenContainer.add(moyenLabel);
            moyenContainer.add(moyenValue);

            Label dateDLabel = new Label("Date départ:");
            dateDLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            dateDLabel.getAllStyles().setFgColor(0x49c5ff);
            dateDLabel.setUIID("DateDLabel");

            Label dateDValue = new Label(d.getDateD());
            dateDValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            dateDValue.getAllStyles().setFgColor(0x666666);
            dateDValue.setUIID("DateDValue");

            Container dateDContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            dateDContainer.add(dateDLabel);
            dateDContainer.add(dateDValue);

            Label dateALabel = new Label("Date arrivée:");
            dateALabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            dateALabel.getAllStyles().setFgColor(0x49c5ff);
            dateALabel.setUIID("DateALabel");

            Label dateAValue = new Label((d.getDateA()));
            dateDValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            dateDValue.getAllStyles().setFgColor(0x666666);
            dateDValue.setUIID("dateAValue");

            Container dateAContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            dateAContainer.add(dateALabel);
            dateAContainer.add(dateAValue);

            Label emailLabel = new Label("Email Chauffeur:");
            emailLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            emailLabel.getAllStyles().setFgColor(0x49c5ff);
            emailLabel.setUIID("EmailLabel");

            Label emailValue = new Label((d.getEmail()));
            emailValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            emailValue.getAllStyles().setFgColor(0x666666);
            emailValue.setUIID("EmailValue");

            Container emailContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            emailContainer.add(emailLabel);
            emailContainer.add(emailValue);

            Label permisLabel = new Label("Permis Chauffeur:");
            permisLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            permisLabel.getAllStyles().setFgColor(0x49c5ff);
            permisLabel.setUIID("PermisLabel");

            Label permisValue = new Label((d.getPermis()));
            permisValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            permisValue.getAllStyles().setFgColor(0x666666);
            permisValue.setUIID("PermisValue");

            Container permisContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            permisContainer.add(permisLabel);
            permisContainer.add(permisValue);

            Button editButton = new Button("Edit");
            item.add(BorderLayout.SOUTH, editButton);

            Container infoContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            infoContainer.add(nomContainer);
            infoContainer.add(moyenContainer);
            infoContainer.add(dateDContainer);
            infoContainer.add(dateAContainer);
            infoContainer.add(emailContainer);
            infoContainer.add(permisContainer);

            item.add(BorderLayout.CENTER, infoContainer);
            Container buttonsContainer = new Container(new BorderLayout());
            buttonsContainer.add(BorderLayout.WEST, acceptButton);
            buttonsContainer.add(BorderLayout.EAST, refuseButton);
            item.add(BorderLayout.SOUTH, buttonsContainer);
//             item.add(nomContainer);
//            item.add(moyenContainer);
//            item.add(dateDContainer);
//             item.add(dateAContainer);
//            item.add(emailContainer);
//            item.add(permisContainer);

            add(item);
            
        }
    }

//            Container emailContainer = new Container(new BorderLayout());
//            emailContainer.setUIID("NumMoyContainer");
//            emailContainer.add(BorderLayout.WEST, dateALabel);
//            emailContainer.add(BorderLayout.CENTER, dateAValue);
//            item.add(emailContainer);
//            
//             Label permisLabel = new Label("Permis Chauffeur:");
//            permisLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//            permisLabel.getAllStyles().setFgColor(0x49c5ff);
//            Label permisValue = new Label((d.getPermis()));
//            permisValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//            permisValue.getAllStyles().setFgColor(0x666666);
////            Container permisContainer = new Container(new BorderLayout());
////            permisContainer.setUIID("NumMoyContainer");
////            permisContainer.add(BorderLayout.WEST, dateALabel);
////            permisContainer.add(BorderLayout.CENTER, dateAValue);
////            item.add(permisContainer);
//            addAll(nomValue,moyenValue,dateDValue,dateAValue,emailAValue,permisValue);
}
//            TextField nomTF = new TextField(d.getNomC());
//            nomTF.setEditable(false);
//            TextField moyenTF = new TextField(d.getMoyen());
//            moyenTF.setEditable(false);
//            TextField dateDtf = new TextField(d.getDateD());
//            dateDtf.setEditable(false);
//            TextField dateAtf = new TextField(d.getDateA());
//            dateAtf.setEditable(false);
//            TextField emailTF = new TextField(d.getEmail());
//            emailTF.setEditable(false);
//            TextField permisTF = new TextField(d.getPermis());
//            permisTF.setEditable(false);

//addAll(nomTF,moyenTF,dateDtf,dateAtf,emailTF,permisTF);
//            demandesList.add(nomTF);
//            demandesList.add(moyenTF);
//            demandesList.add(dateDtf);
//            demandesList.add(dateAtf);
//            demandesList.add(permisTF);

