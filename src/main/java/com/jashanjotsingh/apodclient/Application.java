/**
 *
 * References
 *  - https://www.thediycoder.com/nasa-api-play-around/
 */
package com.jashanjotsingh.apodclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author jashanjotsingh
 */
public class Application {
    // this is a throw away key! (may get blocked after requests > 500/day)
    // you can get your own key by registering at https://api.nasa.gov/
    static String API_KEY = "cWgeOiI7tyGL70EQ10QXYoq7V8LG1lvzcecZWicT";
    public void run() {
        try {
            // connection setup
            URL url = new URL("https://api.nasa.gov/planetary/apod?api_key=" + API_KEY);
            System.out.println("Establishing connection...");
            HttpURLConnection httpConnnection = (HttpURLConnection) url.openConnection();

            // handle request
            httpConnnection.setRequestMethod("GET");
            httpConnnection.setRequestProperty("Content-Type", "application/json");
            httpConnnection.setRequestProperty("Content-Length", "0");
            System.out.println("Connecting to... " + url.toString());
            httpConnnection.connect();

            // handle response
            System.err.println("HTTP Response Code " + httpConnnection.getResponseCode() + " : " + httpConnnection.getResponseMessage());
            BufferedReader responsebr = new BufferedReader(new InputStreamReader((httpConnnection.getInputStream())));
            // handle json with gson library
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            System.out.println(g.toJson(responsebr.readLine()));
            // disconnect
            System.out.println("Disconnecting from... " + url.toString());
            httpConnnection.disconnect();

        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }    
}