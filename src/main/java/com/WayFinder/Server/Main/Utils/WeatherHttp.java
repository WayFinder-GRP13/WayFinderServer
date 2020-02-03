//package com.WayFinder.Server.Main.Utils.;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//
//
//public class WeatherHttp {
//
//
//    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
//
//    // since APK 28, cleartext isn't supported hence have to add permission to manifest else this function will return null
//
//
//    public String getWeatherData(String city) {
//
//        InputStream is;
//        String URL_main = BASE_URL + city +"&APPID=bc375e921d8d29a6a7679d3791091a08";
//        HttpURLConnection con = null ;
//
//        try {
//
//            con = (HttpURLConnection) ( new URL(URL_main )).openConnection();
//            con.setRequestMethod("GET");
//            con.setDoInput(true);
//            con.setDoOutput(true);
//            con.connect();
//
//            // Let's read the response
//            StringBuffer buffer = new StringBuffer();
//
//            is = con.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//            String line = null;
//            while (  (line = br.readLine()) != null )
//                buffer.append(line + "\r\n");
//
//            is.close();
//            con.disconnect();
//
//            return buffer.toString();
//        }
//        catch(Exception e) {
//            String err = (e.getMessage()==null)?"SD Card failed":e.getMessage();
//
//        }
//        finally {
//            try { is.close(); } catch(Throwable t) {}
//            try { con.disconnect(); } catch(Throwable t) {}
//        }
//
//        return null;
//
//
//    }
//
//
//}