package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.events.ActionListener;
import com.mycomany.utils.Statics;
import static com.mycomany.utils.Statics.BASE_URL;
import com.mycomany.entities.Abonnement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.ConnectionRequest;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import static com.mycompany.services.ServiceMoyensTransport.instance;
import static com.mycompany.services.ServiceMoyensTransport.resultOk;
import java.util.Date;

public class AbnService {

    // Singleton 
    public static AbnService instance = null;

        public static boolean resultOk = true;
    private ConnectionRequest req;

            public static AbnService getInstance() {
                       if(instance == null )
            instance = new AbnService();
        return instance ;
    }
             public AbnService() {
        req = new ConnectionRequest();
        
    }
    
    public boolean addAbonnement(Abonnement a) {
       
String url = Statics.BASE_URL + "/Abonnement/new" +"?idU="+ a.getIdU() + "&moyenTransportA=" + a.getMoyenTransportA()
            + "&dateA=" + a.getDateA()+ "&dateExpA=" + a.getDateExpA()+ "&redEtA="+a.getRedEtA()+"&etudiant="+a.getEtudiantA();

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


    public ArrayList<Abonnement> affichageAbonnements() {
        
        ArrayList<Abonnement> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/Abonnement/allJSON";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
                try {
                    Map<String, Object> mapAbonnements = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapAbonnements.get("root");

                    for(Map<String, Object> obj : listOfMaps) {
                       
                    Abonnement abn = new Abonnement();
                        
                    int id =(int) Float.parseFloat(obj.get("id").toString());
                    String moyenTransportA = obj.get("moyenTransportA").toString();
                    try 
                    {
                    Date dateA = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateA").toString());
                    
                    } catch (ParseException ex) 
                    {
                           System.out.println(ex);
                    }
                         try 
                    {           
                    Date dateExpA = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateExpA").toString());

                        } catch (ParseException ex) 
                    {
                           System.out.println(ex);
                    }
//                    int IdUser = (int) Float.parseFloat(obj.get("idU").toString());
                    int redEtA = (int) Float.parseFloat(obj.get("redEtA").toString());
                          
                    
                    abn.setId((int)id);
                    abn.setMoyTrA(moyenTransportA);
//                    abn.setIdU(IdUser);
                    abn.setRedEtA(redEtA);
                    abn.setDateA(new Date());
                    abn.setDateExpA(new Date());

                        
                    result.add(abn);
                }
                    
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }
    });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
        
    return result;
}

}




