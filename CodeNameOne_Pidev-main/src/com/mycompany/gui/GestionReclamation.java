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
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;
import java.util.ArrayList;

public class GestionReclamation extends BaseForm {

    Form current;
    private Container mainContainer;
    private Button btnAjouterReclam;

    public GestionReclamation(Form previous) {
        revalidate();
        current = this;
        setTitle("Gestion de Reclamations");
        setLayout(new BorderLayout());

        // create main container for list of reclam de transport
        mainContainer = new Container(BoxLayout.y());
        mainContainer.setScrollableY(true); // make the main container scrollable
        addComponent(BorderLayout.CENTER, mainContainer);


        // add list of reclam  to main container
        refreshList();

        // add button to add new moyen de transport
        btnAjouterReclam = new Button("Ajouter");
        btnAjouterReclam.addActionListener((evt) -> new AjouterReclamation(current).show());
        Style btnStyle = UIManager.getInstance().getComponentStyle("Button");
        FontImage btnImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, btnStyle);
        btnAjouterReclam.setIcon(btnImage);
        addComponent(BorderLayout.SOUTH, FlowLayout.encloseCenter(btnAjouterReclam)); // center the button

        // add back button to toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        new AcceuilReclam().show();                         

        });
    }

    private void refreshList() {
        mainContainer.removeAll();

        ArrayList<Reclamation> reclam = ServiceReclamation.getInstance().affichageReclamation();
        for (Reclamation r : reclam) {
            Container item = new Container(BoxLayout.y());
            Style itemStyle = item.getAllStyles();
            itemStyle.setMarginTop(5);
            itemStyle.setMarginBottom(5);
            itemStyle.setBorder(Border.createLineBorder(1, 0xcccccc));
            itemStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
            itemStyle.setPadding(5, 5, 5, 5);

            Label NomLabel = new Label("Nom: ");
            NomLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            NomLabel.getAllStyles().setFgColor(0x49c5ff);
            Label NomValue = new Label(r.getNom());
            NomValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            NomValue.getAllStyles().setFgColor(0x000000);
           Container NomContainer = new Container(new BorderLayout());
            NomContainer.setUIID("Nom Container");
            NomContainer.add(BorderLayout.WEST, NomLabel);
            NomContainer.add(BorderLayout.CENTER, NomValue);
            item.add(NomContainer);

            Label PrenomLabel = new Label("Prenom: ");
            PrenomLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            PrenomLabel.getAllStyles().setFgColor(0x49c5ff);
            Label PrenomValue = new Label(r.getPrenom());
            PrenomValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            PrenomValue.getAllStyles().setFgColor(0x666666);
            Container PrenomContainer = new Container(new BorderLayout());
            PrenomContainer.setUIID("Prenom Container");
            PrenomContainer.add(BorderLayout.WEST, PrenomLabel);
            PrenomContainer.add(BorderLayout.CENTER, PrenomValue);
            item.add(PrenomContainer);

           

            Label DescLabel = new Label("Description: ");
            DescLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            DescLabel.getAllStyles().setFgColor(0x49c5ff);
            Label DescValue = new Label(r.getDescrec());
            DescValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            DescValue.getAllStyles().setFgColor(0x666666);
            Container DescContainer = new Container(new BorderLayout());
            DescContainer.setUIID("DescriptionContainer");
            DescContainer.add(BorderLayout.WEST, DescLabel);
            DescContainer.add(BorderLayout.CENTER, DescValue);
            item.add(DescContainer);


            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Button", 4);
            Button btnModifier = new Button(icon);
            btnModifier.addActionListener((evt) -> new ModifierReclamation(current, r).show());
            btnModifier.setUIID("GreenButton");

            Painter greenPainter = new Painter() {
                @Override
                public void paint(Graphics g, Rectangle rect) {
                    g.setColor(0x007F00); // green color
                    g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                }
            };

            btnModifier.getUnselectedStyle().setBgPainter(greenPainter);
            btnModifier.getPressedStyle().setBgPainter(greenPainter);

            FontImage icon2 = FontImage.createMaterial(FontImage.MATERIAL_DELETE, "Button", 4);
            Button btnSupprimer = new Button(icon2);
            btnSupprimer.addActionListener((evt) -> {
                Dialog dig = new Dialog("Suppression");

                if (dig.show("Suppression", "Vous voulez supprimer cette reclamation ?", "Oui", "Annuler")) {
                    ServiceReclamation.getInstance().deleteReclamation(r.getIdr(), () -> {
                        new GestionReclamation(current).show();
                        Dialog.show("Succes", "Réclamation a ete supprimer avec succes", "OK", null);
                    });
                    dig.dispose();
                }
            });
            btnSupprimer.setUIID("RedButton");

            Painter redPainter = new Painter() {
                @Override
                public void paint(Graphics g, Rectangle rect) {
                    g.setColor(0xff0000); // red color
                    g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                }
            };

            btnSupprimer.getUnselectedStyle().setBgPainter(redPainter);
            btnSupprimer.getPressedStyle().setBgPainter(redPainter);

            // Create a container with BoxLayout and add the buttons to it
            Container btnContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            btnContainer.setUIID("Container");
            btnContainer.getAllStyles().setAlignment(Component.CENTER);
            btnContainer.add(btnModifier);
            btnContainer.add(btnSupprimer);

            // Add the container to the item container
            item.add(btnContainer);

            mainContainer.add(item); // add the item container to the main container
        }
    }
}

