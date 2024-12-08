package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Demande;
import com.mycompany.services.ServiceDemande;
import java.io.IOException;
import java.io.InputStream;

public class AddForm extends Form {

    private Picker datea, dated;
    private String imageUrl;
    private Container imageContainer;
    private Label statusLabel;
    private Button permis;
    public AddForm(Form previous) {
        setTitle("ADD Ask");
        
        setLayout(BoxLayout.y());
        TextField nomc = new TextField("", "Ask name");
        TextField moyen = new TextField("", "Moyen de transport");
        datea = new Picker();
        dated = new Picker();
       
        TextField email = new TextField("", "Email");
        statusLabel = new Label("");
       permis = new Button("Permis");
        
        Button btnADD = new Button("Add");
        btnADD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nomc.getText().equals("") || moyen.getText().equals("") || datea.getText().equals("")
                        || dated.getText().equals("") || permis.getText().equals("") || email.getText().equals("")) {
                    Dialog.show("Error", "Fill the input", "ok", null);
                } else {
                    System.out.println(nomc.getText() + moyen.getText() + datea.getText()
                            + dated.getText() + permis.getText() + email.getText());

                    // Create a new Demande object
                    Demande demande = new Demande(nomc.getText(), moyen.getText(), datea.getText(),
                            dated.getText(), permis.getText(), email.getText());

                    // Call the addDemande() method from your ServiceDemande class
                    if (ServiceDemande.getInstance().addDemande(demande)) {
                        Dialog.show("success", "Ask added successfully", "ok", null);
                    } else {
                        Dialog.show("Error", "Request Error", "ok", null);
                    }
                }

            }
        });

       //  Button permis = new Button("Permis");
        permis.addActionListener(e -> chooseImage());

         imageContainer = new Container();
    imageContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

// Set the preferred size of the image container
int containerWidth = Display.getInstance().getDisplayWidth();
int containerHeight = Display.getInstance().getDisplayHeight() / 2;
imageContainer.setPreferredW(containerWidth);
imageContainer.setPreferredH(containerHeight);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });

        addAll(nomc, moyen, datea, dated, email, permis, imageContainer, statusLabel,btnADD);
    }

    private void chooseImage() {
        Display.getInstance().openGallery((e) -> {
            if (e != null && e.getSource() != null) {
                String fileUrl = (String) e.getSource();
                showImage(fileUrl);
            }
        }, Display.GALLERY_IMAGE);
    }

    
   private void showImage(String fileUrl) {
    try {
        // Read the image file as an input stream
        InputStream is = FileSystemStorage.getInstance().openInputStream(fileUrl);

        // Create an image from the input stream
        Image image = Image.createImage(is);

        // Scale the image to fit the container size
        int containerWidth = imageContainer.getWidth();
        int containerHeight = imageContainer.getHeight();
        Image scaledImage = image.scaled(containerWidth, containerHeight);

        // Clear the container and add the scaled image to it
        imageContainer.removeAll();
        imageContainer.add(scaledImage);

        // Revalidate the container to update the UI
        imageContainer.revalidate();

        // Save the image URL as the permis value
        permis.setText(fileUrl);
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    private void uploadImage() {
    if (imageUrl == null) {
        statusLabel.setText("No image selected");
        return;
    }

    try {
        // Create a MultipartRequest to perform the image upload
        MultipartRequest request = new MultipartRequest() {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                // Handle the server response after image upload
                String response = Util.readToString(input);
                statusLabel.setText(response);
            }
        };

        // Set the URL of the server-side upload script
        request.setUrl("http://your-server-url.com/upload.php");

        // Add the image file to the request
        request.addData("image", imageUrl, "image/jpeg");

        // Send the request to the server
        NetworkManager.getInstance().addToQueue(request);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

