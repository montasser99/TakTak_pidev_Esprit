package com.mycompany.gui;

import com.codename1.components.ToastBar;
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
import com.codename1.ui.TextField;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Moyenstransport;
import com.mycompany.services.ServiceMoyensTransport;
import java.util.ArrayList;
import java.util.List;

public class GestionTransport extends Form {

    Form current;
    private Container mainContainer;
    private Button btnAjouterTransport;
    private TextField searchField;
    private List<Moyenstransport> moyens= ServiceMoyensTransport.getInstance().affichageMoyensTransport();
    public GestionTransport(Form previous) {
        revalidate();
        current = this;
        setTitle("Gestion Moyen de transport");
        setLayout(new BorderLayout());

        
        
        // create main container for list of moyens de transport
        mainContainer = new Container(BoxLayout.y());
        mainContainer.setScrollableY(true); // make the main container scrollable
        addComponent(BorderLayout.CENTER, mainContainer);


        // add list of moyens de transport to main container
        refreshList();

searchField = new TextField("", "Search", 20, TextField.ANY);
searchField.addDataChangeListener((i1, i2) -> {
    String searchQuery = searchField.getText();
    if (searchQuery != null && !searchQuery.isEmpty()) {
        List<Moyenstransport> searchResults = new ArrayList<>();
        if (moyens != null) {
            for (Moyenstransport m : moyens) {
                if (m.getMatricule().toLowerCase().contains(searchQuery.toLowerCase()) && !searchResults.contains(m)) {
                    searchResults.add(m);
                }
            }
        }
        mainContainer.removeAll();
        if (!searchResults.isEmpty()) {
            mainContainer.add(createItem(searchResults.get(0)));
        }
    } else {
        refreshList();
    }
});

addComponent(BorderLayout.NORTH, searchField);

        // add button to add new moyen de transport
        btnAjouterTransport = new Button("Ajouter");
        btnAjouterTransport.addActionListener((evt) -> new AjouterMoyensTransport(current).show());
        Style btnStyle = UIManager.getInstance().getComponentStyle("Button");
        FontImage btnImage = FontImage.createMaterial(FontImage.MATERIAL_ADD, btnStyle);
        btnAjouterTransport.setIcon(btnImage);
        add(BorderLayout.SOUTH, FlowLayout.encloseCenter(btnAjouterTransport)); // center the button

        // add back button to toolbar
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
        new AcceuilForm().show();                         

        });
    }

    private void refreshList() {
        mainContainer.removeAll();

    ArrayList<Moyenstransport> moyens = ServiceMoyensTransport.getInstance().affichageMoyensTransport();
       


        for (Moyenstransport m : moyens) {
            Container item = new Container(BoxLayout.y());

        
            Style itemStyle = item.getAllStyles();
            itemStyle.setMarginTop(5);
            itemStyle.setMarginBottom(5);
            itemStyle.setBorder(Border.createLineBorder(1, 0xcccccc));
            itemStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
            itemStyle.setPadding(5, 5, 5, 5);

            Label typeLabel = new Label("Type:");
            typeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            typeLabel.getAllStyles().setFgColor(0x49c5ff);
            Label typeValue = new Label(m.getType());
            typeValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            typeValue.getAllStyles().setFgColor(0x000000);
            Container typeContainer = new Container(new BorderLayout());
            typeContainer.setUIID("TypeContainer");
            typeContainer.add(BorderLayout.WEST, typeLabel);
            typeContainer.add(BorderLayout.CENTER, typeValue);
            item.add(typeContainer);

            Label matriculeLabel = new Label("Matricule:");
            matriculeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            matriculeLabel.getAllStyles().setFgColor(0x49c5ff);
            Label matriculeValue = new Label(m.getMatricule());
            matriculeValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            matriculeValue.getAllStyles().setFgColor(0x666666);
            Container container = new Container(new BorderLayout());
            container.setUIID("MatriculeContainer");
            container.add(BorderLayout.WEST, matriculeLabel);
            container.add(BorderLayout.CENTER, matriculeValue);
            item.add(container);

            Label capaciteLabel = new Label("Capacité:");
            capaciteLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            capaciteLabel.getAllStyles().setFgColor(0x49c5ff);
            Label capaciteValue = new Label(Integer.toString(m.getCapacite()));
            capaciteValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            capaciteValue.getAllStyles().setFgColor(0x666666);
            Container capaciteContainer = new Container(new BorderLayout());
            capaciteContainer.setUIID("CapaciteContainer");
            capaciteContainer.add(BorderLayout.WEST, capaciteLabel);
            capaciteContainer.add(BorderLayout.CENTER, capaciteValue);
            item.add(capaciteContainer);

            Label numMoyLabel = new Label("Numéro de moyen:");
            numMoyLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            numMoyLabel.getAllStyles().setFgColor(0x49c5ff);
            Label numMoyValue = new Label((m.getNumMoy()));
            numMoyValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
            numMoyValue.getAllStyles().setFgColor(0x666666);
            Container numMoyContainer = new Container(new BorderLayout());
            numMoyContainer.setUIID("NumMoyContainer");
            numMoyContainer.add(BorderLayout.WEST, numMoyLabel);
            numMoyContainer.add(BorderLayout.CENTER, numMoyValue);
            item.add(numMoyContainer);

            FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Button", 4);
            Button btnModifier = new Button(icon);
            btnModifier.addActionListener((evt) -> new ModifierMoyensTransport(current, m).show());
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

                if (dig.show("Suppression", "Vous voulez supprimer ce moyen de transport ?", "Oui", "Annuler")) {
                    ServiceMoyensTransport.getInstance().deleteMoyensTransport(m.getIdMoy(), () -> {
                        new GestionTransport(current).show();
                        Dialog.show("Succes", "Moyen de transport a ete supprimer avec succes", "OK", null);
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
    
    
    
    private Component createItem(Moyenstransport m) {
    Container item = new Container(BoxLayout.y());

    Style itemStyle = item.getAllStyles();
    itemStyle.setMarginTop(5);
    itemStyle.setMarginBottom(5);
    itemStyle.setBorder(Border.createLineBorder(1, 0xcccccc));
    itemStyle.setPaddingUnit(Style.UNIT_TYPE_DIPS);
    itemStyle.setPadding(5, 5, 5, 5);

    Label typeLabel = new Label("Type:");
    typeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    typeLabel.getAllStyles().setFgColor(0x49c5ff);
    Label typeValue = new Label(m.getType());
    typeValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    typeValue.getAllStyles().setFgColor(0x000000);
    Container typeContainer = new Container(new BorderLayout());
    typeContainer.setUIID("TypeContainer");
    typeContainer.add(BorderLayout.WEST, typeLabel);
    typeContainer.add(BorderLayout.CENTER, typeValue);
    item.add(typeContainer);

    Label matriculeLabel = new Label("Matricule:");
    matriculeLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    matriculeLabel.getAllStyles().setFgColor(0x49c5ff);
    Label matriculeValue = new Label(m.getMatricule());
    matriculeValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    matriculeValue.getAllStyles().setFgColor(0x666666);
    Container container = new Container(new BorderLayout());
    container.setUIID("MatriculeContainer");
    container.add(BorderLayout.WEST, matriculeLabel);
    container.add(BorderLayout.CENTER, matriculeValue);
    item.add(container);

    Label capaciteLabel = new Label("Capacité:");
    capaciteLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    capaciteLabel.getAllStyles().setFgColor(0x49c5ff);
    Label capaciteValue = new Label(String.valueOf(m.getCapacite()));
    capaciteValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    capaciteValue.getAllStyles().setFgColor(0x666666);
    Container capaciteContainer = new Container(new BorderLayout());
    capaciteContainer.setUIID("CapaciteContainer");
    capaciteContainer.add(BorderLayout.WEST, capaciteLabel);
    capaciteContainer.add(BorderLayout.CENTER, capaciteValue);
    item.add(capaciteContainer);

        Label numMoyLabel = new Label("Numéro de moyen:");
        numMoyLabel.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        numMoyLabel.getAllStyles().setFgColor(0x49c5ff);
        Label numMoyValue = new Label((m.getNumMoy()));
        numMoyValue.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        numMoyValue.getAllStyles().setFgColor(0x666666);
        Container numMoyContainer = new Container(new BorderLayout());
        numMoyContainer.setUIID("NumMoyContainer");
        numMoyContainer.add(BorderLayout.WEST, numMoyLabel);
        numMoyContainer.add(BorderLayout.CENTER, numMoyValue);
        item.add(numMoyContainer);

            
         FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_EDIT, "Button", 4);
            Button btnModifier = new Button(icon);
            btnModifier.addActionListener((evt) -> new ModifierMoyensTransport(current, m).show());
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

                if (dig.show("Suppression", "Vous voulez supprimer ce moyen de transport ?", "Oui", "Annuler")) {
                    ServiceMoyensTransport.getInstance().deleteMoyensTransport(m.getIdMoy(), () -> {
                        new GestionTransport(current).show();
                        Dialog.show("Succes", "Moyen de transport a ete supprimer avec succes", "OK", null);
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
            return item;
}
    
}

