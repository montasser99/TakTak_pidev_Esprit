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
import com.mycomany.entities.Moyenstransport;
import com.mycomany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public class ServiceMoyensTransport {
       //singleton 
    public static ServiceMoyensTransport instance = null ;
        public static boolean resultOk = true;
    //initilisation connection request 
    private ConnectionRequest req;
    
    //pour appliquer sigleton
        public static ServiceMoyensTransport getInstance() {
        if(instance == null )
            instance = new ServiceMoyensTransport();
        return instance ;
    }
    
       public ServiceMoyensTransport() {
        req = new ConnectionRequest();
        
    }
    
    
        //ajout 
    public boolean AjoutMoyensTransport(Moyenstransport moyens) {
        
        //hne n3adi url mta3i shih eli fel symfony o n3adilou mel table moyens données
        String url =Statics.BASE_URL+"/AjouterMoyensJson/new?type="+moyens.getType()
        +"&matricule="+moyens.getMatricule()+"&capacite="+moyens.getCapacite()+"&nummoy="+moyens.getNumMoy()+"";

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
    
    public ArrayList<Moyenstransport>affichageMoyensTransport() {
        
        ArrayList<Moyenstransport> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/moyenstransportjson";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapMoyensTransport = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapMoyensTransport.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Moyenstransport moy = new Moyenstransport();
                    
                    //dima id fi codename one float 5outhouha
                    int idmoy = (int) Float.parseFloat(obj.get("idmoy").toString());
                    
                    String type = obj.get("type").toString();
                    
                    String matricule = obj.get("matricule").toString();
                    
                    int capacite = (int) Float.parseFloat(obj.get("capacite").toString());
                    
                    String nummoy = obj.get("nummoy").toString();

                    moy.setIdMoy(idmoy);
                    moy.setType(type);
                    moy.setMatricule(matricule);
                    moy.setCapacite(capacite);
                    moy.setNumMoy(nummoy);
                    
//                    //Date
//                    String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2") ,obj.get("Date").toString().lastIndexOf("T"));
//                    
//     
//                    re.setDateR(DateConverter);
                    
                    //insert data into ArrayList result
                    result.add(moy);
                    
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
    public boolean modifierMoyensTransport(Moyenstransport moyensTransport) {
        String url = Statics.BASE_URL +"/ModifierMoyensJSON/"+(int)moyensTransport.getIdMoy()+"?type="+moyensTransport.getType()+"&matricule="+moyensTransport.getMatricule()+"&capacite="+moyensTransport.getCapacite()+"&nummoy="+moyensTransport.getNumMoy();
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
public void deleteMoyensTransport(int id, Runnable callback) {
    String url = Statics.BASE_URL + "/SupprimerMoyensJSON/" + id;
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
