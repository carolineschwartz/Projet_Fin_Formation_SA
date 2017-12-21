package com.example.androidclient;

/**
 * Created by z on 20/12/2017.
 */

public final class GlobalVariables {
    private static String base_url = "http://192.168.137.1:8080";
    private static String app_name = "/Projet_Fin_Formation/api/";

    public static final String getBaseUrl(){
        return base_url;
    }

    public static final String getApp_name(){
        return app_name;
    }
}
