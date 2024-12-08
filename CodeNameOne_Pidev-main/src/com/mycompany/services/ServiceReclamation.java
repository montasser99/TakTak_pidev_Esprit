/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.Reclamation;
import com.mycomany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sbs
 */
public class ServiceReclamation {
     public static ServiceReclamation instance = null ;
        public static boolean resultOk = true;
    //initilisation connection request 
    private ConnectionRequest req;
    
    //pour appliquer sigleton
        public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    
       public ServiceReclamation() {
        req = new ConnectionRequest();
        
    }
     //ajout 
    public boolean AjoutReclamation(Reclamation reclam) {
        
        //hne n3adi url mta3i shih eli fel symfony o n3adilou mel table reclam données
        String url =Statics.BASE_URL+"/reclamationjson/newjson?nom="+reclam.getNom()
        +"&prenom="+reclam.getPrenom()+"&desc="+reclam.getDescrec()+"";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            resultOk=req.getResponseCode()==200;
            req.removeResponseListener(this);
            }
        });
        //execution ta3 request sinon yet3ada chy dima nal9awha
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
        
        
    
    }
    //affichage
    
    public ArrayList<Reclamation>affichageReclamation() {
        
        ArrayList<Reclamation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reclamationjson/Recjson";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapReclamation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamation.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Reclamation rec = new Reclamation();
                    
                    //dima id fi codename one float 5outhouha
                    int idr = (int) Float.parseFloat(obj.get("idr").toString());
                    
                    String nom = obj.get("nom").toString();
                    
                    String prenom = obj.get("prenom").toString();
                    String descrec = obj.get("descrec").toString();
                                        

                    rec.setIdr(idr);
                    rec.setNom(nom);
                    rec.setPrenom(prenom);
                    rec.setDescrec(descrec);
             
                    result.add(rec);
                    
                }
            
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }
        });
        //execution de la requette sinon mayet3ada hata chey 
      NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
      
    }
     //Update 
    public boolean modifierReclamation(Reclamation reclam) {
        System.out.println(reclam);
        String url = Statics.BASE_URL +"/reclamationjson/editjson/"+(int)reclam.getIdr()+"?nom="+reclam.getNom()+"&prenom="+reclam.getPrenom()+"&dater="+reclam.getDater()+"&desc="+reclam.getDescrec();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOk;
        
    }
    //Delete 
public void deleteReclamation(int id, Runnable callback) {
    String url = Statics.BASE_URL + "/reclamationjson/removejson/" + id;
    req.setUrl(url);
    req.setPost(true);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            boolean resultOk = req.getResponseCode() == 200;
            req.removeResponseListener(this);
            if (callback != null) {
                callback.run();
            }
        }
    });
    NetworkManager.getInstance().addToQueue(req);
}
    
    
    
}
