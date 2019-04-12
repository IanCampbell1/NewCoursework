// Mobile Platform Development Coursework 2018-2019
// Ian Campbell 200507045

package com.mpd.coursework.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


// Helper class for working with a remote server, in this case returning
// text from a URL on a web server

public class Http {


    public static String downloadUrl(String address) throws IOException {

        InputStream is = null;
        try {

            URL url = new URL(address); //wraps string in url object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //opens connection
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect(); //calls connect

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) { //standard http response code
                throw new IOException("Got response code " + responseCode);

            }
            is = conn.getInputStream();
            return readStream(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    // Reads InputStream and converts it to a String. Works for any input stream.
    private static String readStream(InputStream stream) throws IOException {

        byte[] buffer = new byte[1024];
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        BufferedOutputStream out = null;
        try {
            int length = 0;
            out = new BufferedOutputStream(byteArray);
            while ((length = stream.read(buffer)) > 0) { out.write(buffer, 0, length); //Loop through incoming data saving to buffer
            }
            out.flush();
            return byteArray.toString(); // Returns byteArray as a string
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (out != null) {
                out.close(); //closed
            }
        }
    }

}
