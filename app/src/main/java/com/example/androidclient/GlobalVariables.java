package com.example.androidclient;

/**
 * Created by z on 20/12/2017.
 */

public final class GlobalVariables {
    private static String base_url = "http://172.20.10.2:8080";
//    private static String base_url = "http://test.legionnaire.ovh:8080";
//    private static String base_url = "http://192.168.137.1:8080";
    private static String app_name = "/webserveursport/";
//    private static String app_name = "/webserveursport/";
//    private static String app_name = "/Projet_Fin_Formation/";

    public static final String getBaseUrl(){
        return base_url;
    }

    public static final String getApp_name(){
        return app_name;
    }
}
