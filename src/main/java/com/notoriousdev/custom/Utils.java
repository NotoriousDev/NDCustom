package com.notoriousdev.custom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils
{

    private final NDCustom plugin;

    public Utils(NDCustom plugin)
    {
        this.plugin = plugin;
    }

    public String postPasswordHash(String password) throws MalformedURLException, IOException
    {
        String result = "";

        URL url = new URL(plugin.getConfig().getString(""));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setUseCaches(false);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes("pass=" + password);
        out.flush();
        out.close();

        BufferedReader in = null;
        try {
            String line;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return result;
    }
}
