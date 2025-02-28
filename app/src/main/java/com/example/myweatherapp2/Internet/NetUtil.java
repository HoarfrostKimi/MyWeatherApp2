package com.example.myweatherapp2.Internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    public static String doGet(String urlStr){
        String result="";
        HttpURLConnection connection=null;
        InputStreamReader inputStreamReader=null;
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(urlStr);
            connection=(HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            //从连接中读取数据
            InputStream inputStream=connection.getInputStream();
            inputStreamReader=new InputStreamReader(inputStream);
            bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();

            String line="";

            while ((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);

            }
            result=stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }
            if (inputStreamReader!=null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }

        return result;
    }
}
