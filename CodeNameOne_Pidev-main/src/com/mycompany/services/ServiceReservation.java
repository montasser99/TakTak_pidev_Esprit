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
import com.mycomany.entities.Moyenstransport;
import com.mycomany.entities.Reservation;
import com.mycomany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc
 */
public class ServiceReservation {
    
           //singleton 
    public static ServiceReservation instance = null ;
        public static boolean resultOk = true;
    //initilisation connection request 
    private ConnectionRequest req;
    
    //pour appliquer sigleton
        public static ServiceReservation getInstance() {
        if(instance == null )
            instance = new ServiceReservation();
        return instance ;
    }
    
       public ServiceReservation() {
        req = new ConnectionRequest();
        
    }
    
    
    
    
           //affichage
    
    public ArrayList<Reservation>affichageReservation() {
        
        ArrayList<Reservation> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/reservation/json";
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>mapReservation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReservation.get("root");
                
                for(Map<String, Object> obj : listOfMaps) {
                    
                    Reservation reserv = new Reservation();
                    
                    //dima id fi codename one float 5outhouha
                    int idnum = (int) Float.parseFloat(obj.get("idnum").toString());
                    
                    String dater = obj.get("dater").toString();
                    
                    String heuredep = obj.get("heuredep").toString();
                    
                    String heurearr = obj.get("heurearr").toString();
                    
                    String type = obj.get("type").toString();

                                       
                    int cin = (int) Float.parseFloat(obj.get("cin").toString());
                    
                    int prix = (int) Float.parseFloat(obj.get("prix").toString());
                    
                    String numerot = obj.get("numerot").toString();

                                        String datereservation = obj.get("datereservation").toString();


                    reserv.setIdNum(idnum);
                    reserv.setType(type);
                    reserv.setHeureDep(heuredep);
                    reserv.setHeureArr(heurearr);
                    reserv.setCin(cin);
                    reserv.setPrix(prix);
                    reserv.setNUM(numerot);
                    reserv.setDateR(datereservation);

                    
//                    //Date
//                    String DateConverter =  obj.get("Date").toString().substring(obj.get("Date").toString().indexOf("2") ,obj.get("Date").toString().lastIndexOf("T"));
//                    
//     
//                    re.setDateR(DateConverter);
                    
                    //insert data into ArrayList result
                    result.add(reserv);
                    
                }
            
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }
        });
        //execution de la requette sinon mayet3ada hata chey 
      NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
      
    }
}
