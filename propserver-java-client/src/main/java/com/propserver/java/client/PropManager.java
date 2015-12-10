/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.propserver.java.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author PBhatt
 */
public final class PropManager {

    private static PropManager propManager = null;
    private String configUrl, project, env, release, fullURL;
    private JSONObject parsedJson;

    public static void main(String[] args) {

        PropManager prpManager = PropManager.getInstance("http://localhost:3000", "VisaCheckout", "DEV", "0.0");

    }

    // Made the constructor private. 
    private PropManager(String configUrl, String project, String env, String release) {

        this.configUrl = configUrl;
        this.project = project;
        this.env = env;
        this.release = release;

        this.fullURL = configUrl + "/projects/" + project + "/env/" + env + "/release/" + release + "?flatten=true";
    }

    /**
     * This function is responsible for returning the Single Instance of the
     * Prop Server.
     *
     * @param configUrl
     * @param project
     * @param release
     * @param env
     * @return
     */
    public final synchronized static PropManager getInstance(String configUrl, String project, String env, String release) {

        if (propManager == null) {
            propManager = new PropManager(configUrl, project, env, release);
            propManager.initialize();
        }
        return propManager;
    }

    /**
     * This is the function which will be responsible for returning a specific
     * Key of an Object.
     *
     * @param keyName
     * @return
     */
    public final String get(String keyName) {
        if (parsedJson instanceof JSONObject) {
            Object value = parsedJson.get(keyName);
            if (value != null) {
                return value.toString();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * This function will return all the keys as STRING. 
     * @return 
     */
    public final String getAllKeys() {

        if (parsedJson instanceof JSONObject) {
            return parsedJson.toString();
        } else {
            return "";
        }
    }

    /**
     * THis function refresh the Set of properties for the project afresh.
     *
     * @return
     */
    public final void refresh() {
        if(propManager != null)
            propManager.initialize();
    }

    /**
     * This is the main method which is responsible
     */
    private void initialize() {

        try {
            URL propServerUrl = new URL(this.fullURL);
            HttpURLConnection httpConn = (HttpURLConnection) propServerUrl.openConnection();
            httpConn.setRequestMethod("GET");
            int responseCode = httpConn.getResponseCode();
            if (responseCode == 200) {

                BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String inputLine;
                JSONParser parser = new JSONParser();
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                parsedJson = (JSONObject) parser.parse(response.toString());

            } else {
                System.err.println("The PropServer has responded with an Error Code. Please see the details. Error Code = " + responseCode);
            }
        } catch (MalformedURLException ex) {
            System.err.println("URL Specified is not correct. " + this.fullURL);
            ex.printStackTrace();
        } catch (IOException ioEx) {
            System.err.println("IO Exception has been occrred while connecting to the URL :  " + this.fullURL);
            ioEx.printStackTrace();
        } catch (ParseException parseEx) {
            System.err.println("Parse Exception has been occrred while parsing Data from the URL  :  " + this.fullURL);
            parseEx.printStackTrace();
        }

    }

}
