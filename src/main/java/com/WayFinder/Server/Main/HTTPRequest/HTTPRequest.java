package com.WayFinder.Server.Main.HTTPRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest {


    public String sendHTTPRequest(String request){
        //https://stackoverflow.com/questions/2938502/sending-post-data-in-android
        StringBuilder jsonStringBuilder = new StringBuilder();
        // Making HTTP request
        try {
            URL url = new URL(request);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuffer buffer = new StringBuffer();
                String line;

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    jsonStringBuilder.append(line);
                    jsonStringBuilder.append("\n");
                }
                //System.out.println(jsonStringBuilder.toString());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //JSONFile=jsonStringBuilder.toString();
        return jsonStringBuilder.toString();
    }
}
