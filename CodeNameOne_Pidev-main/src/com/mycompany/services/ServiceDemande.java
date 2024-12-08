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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Demande;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author ASUS
 */
public class ServiceDemande{
    
        public ConnectionRequest req; 
        private static ServiceDemande instance;
    private boolean resultOK;
    public  boolean result;
    ArrayList<Demande> demandes = new ArrayList<>();
  
        
        private ServiceDemande(){
            req =new ConnectionRequest();
        }

    public static ServiceDemande getInstance() {
        if (instance ==null)
            instance = new ServiceDemande();    
        return instance;
        }
    public boolean addDemande( Demande demande){
           
        // System.out.println("Calling addDemande() method with demande: " + demande.toString());
             String  url = Statics.BASE_URL+"/addDemandeJSON?nomc="  + demande.getNomC() + "&moyen=" + demande.getMoyen() + "&dated=" + demande.getDateD() + "&datea=" + demande.getDateA() + "&permis=" + demande.getPermis() + "&email=" + demande.getEmail();
                 req.setUrl(url);
                 req.setPost(false);
      
       req.addResponseListener(new ActionListener<NetworkEvent>() {
           
            @Override
           public void actionPerformed(NetworkEvent evt) {
              //  System.out.println("Response received for addDemande(): " + evt.getMetaData());
               resultOK = req.getResponseCode()==200;
               req.removeResponseListener(this);
           }
       });
       NetworkManager.getInstance().addToQueueAndWait(req);
           
       return resultOK;
        
    }
//   public ArrayList<Demande> getAllDemandes(){
//       
//       String  url = Statics.BASE_URL+"demandesJSON";
//                 req.setUrl(url);
//                 req.setPost(false);  
//       req.addResponseListener((evt) -> {
//           demandes = parseDemande(new String (req.getResponseData()));
//       });
//     
//             NetworkManager.getInstance().addToQueueAndWait(req);  
//           
//          
//        
//              return demandes; 
//                  
//               
//           }
//
//    private ArrayList<Demande> parseDemande(String jsonText) {
//         
//                 try { 
//                     JSONParser j = new JSONParser();
//                Map<String,Object> demandeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//                List<Map<String,Object>> list = (List<Map<String,Object>>) demandeListJson.get("root");
//                for(Map<String,Object> obj : list){
//                    Demande d = new Demande();
//                    
//                     int id = (int) Float.parseFloat(obj.get("id").toString());
//                    String nomc = obj.get("nomc").toString();
//                    String moyen = obj.get("moyen").toString();
//                    String dated = obj.get("dated").toString();
//                    String datea = obj.get("datea").toString();
//                    String email = obj.get("Emailc").toString();
//                    String permis = obj.get("permis").toString();
//             
//                    demandes.add(d);
//                }
//            } catch (Exception ex) {
//                 ex.printStackTrace();
//            }
//           
//    return demandes;
//        
//    }
     public ArrayList<Demande> getAllDemandes(){
          String url = Statics.BASE_URL + "/demandesJSON";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            demandes = parseDemande(new String (req.getResponseData()));
//            System.out.println(demandes);
            req.removeResponseListener(this);
        }

              private ArrayList<Demande> parseDemande(String jsonText) {
                 try { 
                     JSONParser j = new JSONParser();
                Map<String,Object> demandeListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                List<Map<String,Object>> list = (List<Map<String,Object>>) demandeListJson.get("root");
                for(Map<String,Object> obj : list){
                    Demande d = new Demande();
                    
                     int id = (int) Float.parseFloat(obj.get("id").toString());
                    String nomc = obj.get("nomc").toString();
                    String moyen = obj.get("moyen").toString();
                    String dated = obj.get("dated").toString();
                    String datea = obj.get("datea").toString();
                    String email = obj.get("Emailc").toString();
                    String permis = obj.get("permis").toString();
                   
                  
                    d.setId(id);
                    d.setNomC(nomc);
                    d.setMoyen(moyen);
                    d.setDateD(dated);
                    d.setDateA(datea);
                     d.setEmail(email);
                    d.setPermis(permis);
                    
                     demandes.add(d);
                       System.out.println(demandes);
                }
            } catch (Exception ex) {
                 ex.printStackTrace();
            }
                 return demandes;
              }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return demandes;
     }
     
     public boolean acceptSMS(){
         
         String url = Statics.BASE_URL + "/SMSjsonAccept";
         req.setUrl(url);
         req.setPost(false);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            result=req.getResponseCode()==200;
            req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
     }
     
      public boolean refuseSMS(){
         
         String url = Statics.BASE_URL + "/SMSjsonRefuse";
         req.setUrl(url);
         req.setPost(false);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            result=req.getResponseCode()==200;
            req.removeResponseListener(this);
            }
        });
         NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
     }

}

    
    

   


    
    
    
        
        

        
    
      
      
   
    


  

   