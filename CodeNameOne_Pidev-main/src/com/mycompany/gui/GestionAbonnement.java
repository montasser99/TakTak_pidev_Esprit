/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Painter;
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycomany.entities.Abonnement;
import com.mycompany.services.AbnService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author pc
 */
public class GestionAbonnement extends Form {

    Form current;
    private Container mainContainer;
    private Button btnAjouterAbn;
    private TextField searchField;

    public GestionAbonnement(Form previous) {
        revalidate();
        current = this;
        setTitle("Gestion Abonnement");
        setLayout(new BorderLayout());

        // create main container for list of moyens de transport
        mainContainer = new Container(BoxLayout.y());
        mainContainer.setScrollableY(true); // make the main container scrollable
        addComponent(BorderLayout.CENTER, mainContainer);

        // add list of moyens de transport to main container
        refreshList();

        // add button to add new moyen de transport
        btnAjouterAbn = new Button("Ajouter");
        btnAjouterAbn.addActionListener((evt) -> new AjouterAbonnement().show());
        add(BorderLayout.SOUTH, FlowLayout.encloseCenter(btnAjouterAbn)); // center the button

        // add back button to toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new AcceuilForm().show();

        });
    }

    private void refreshList() {
        mainContainer.removeAll();

        ArrayList<Abonnement> Abns = AbnService.getInstance().affichageAbonnements();

        for (Abonnement A : Abns) {
     Container item = new Container(BoxLayout.y());

        Style itemStyle = item.getAllStyles();
        itemStyle.setMarginTop(5);
        itemStyle.setMarginBottom(5);
        itemStyle.setBorder(Border.createLineBorder(1, 0xcccccc));
        itemStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
        itemStyle.setPadding(5, 5, 5, 5);

        Label typeLabel = new Label("Type de transport:");
        typeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        typeLabel.getAllStyles().setFgColor(0x49c5ff);
        Label typeValue = new Label(A.getMoyenTransportA());
        typeValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        typeValue.getAllStyles().setFgColor(0x000000);
        Container typeContainer = new Container(new BorderLayout());
        typeContainer.setUIID("TypeContainer");
        typeContainer.add(BorderLayout.WEST, typeLabel);
        typeContainer.add(BorderLayout.CENTER, typeValue);
        item.add(typeContainer);

        Label matriculeLabel = new Label("date d'abonnement:");
        matriculeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        matriculeLabel.getAllStyles().setFgColor(0x49c5ff);
  
SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date dateA = A.getDateA();
if (dateA != null) {
    String dateAString = formatter.format(dateA);
    Label matriculeValue = new Label(dateAString);       
    matriculeValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        matriculeValue.getAllStyles().setFgColor(0x666666);
        Container container = new Container(new BorderLayout());
        container.setUIID("DateAContainer");
        container.add(BorderLayout.WEST, matriculeLabel);
        container.add(BorderLayout.CENTER, matriculeValue);
        item.add(container);

    // add the label to the container and item as before
} else {
    // handle the case where the date is null (e.g. set a default label value)
}
        

        Label capaciteLabel = new Label("date expiration d'abonnement :");
        capaciteLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        capaciteLabel.getAllStyles().setFgColor(0x49c5ff);
            Date DateExpA = A.getDateExpA();
if (DateExpA != null) {
    String dateEXPString = formatter.format(DateExpA);
    Label capaciteValue = new Label(dateEXPString);  

        capaciteValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        capaciteValue.getAllStyles().setFgColor(0x666666);
        Container capaciteContainer = new Container(new BorderLayout());
        capaciteContainer.setUIID("DateExpContainer");
        capaciteContainer.add(BorderLayout.WEST, capaciteLabel);
        capaciteContainer.add(BorderLayout.CENTER, capaciteValue);
        item.add(capaciteContainer);

//        Label numMoyLabel = new Label("Etudiant ID :");
//        numMoyLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//        numMoyLabel.getAllStyles().setFgColor(0x49c5ff);
//        Label numMoyValue = new Label((String.valueOf(A.getIdU())));
//        numMoyValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//        numMoyValue.getAllStyles().setFgColor(0x666666);
//        Container numMoyContainer = new Container(new BorderLayout());
//        numMoyContainer.setUIID("EtudiantIdContainer");
//        numMoyContainer.add(BorderLayout.WEST, numMoyLabel);
//        numMoyContainer.add(BorderLayout.CENTER, numMoyValue);
//        item.add(numMoyContainer);

        
//        Label RedLabel = new Label("Reduction d'abonner :");
//        RedLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//        RedLabel.getAllStyles().setFgColor(0x49c5ff);
//        Label RedAValue = new Label((String.valueOf(A.getRedEtA())));
//        RedAValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
//        RedAValue.getAllStyles().setFgColor(0x666666);
//        Container RedAContainer = new Container(new BorderLayout());
//        RedAContainer.setUIID("ReductionContainer");
//        RedAContainer.add(BorderLayout.WEST, RedAContainer);
//        RedAContainer.add(BorderLayout.CENTER, RedAContainer);
//        item.add(RedAContainer);


} else {
    // handle the case where the date is null (e.g. set a default label value)
}
            mainContainer.add(item); // add the item container to the main container
        }
    }

   

}
