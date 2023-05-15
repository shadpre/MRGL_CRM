package BLL.Managers;

import BE.DBEnteties.WiFi;
import DAL.DB.WiFiDAO_DB;

public class WiFiManager {
    WiFi wifi;


    public void init(){
    try
    {
        var test = WiFiDAO_DB.getWiFis(1);
    }
    catch(Exception ex){
        System.out.println(ex.getMessage());
    }
}}
