package com.pjmike.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author pjmike
 * @create 2018-10-03 23:13
 */
public class HttpClientTest1 {
    public static void main(String[] args) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpGet httpGet = new HttpGet("http://www.baidu.com");
//        try {
//            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//            HttpEntity entity = httpResponse.getEntity();
//            System.out.println("content "+entity.getContent());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        HttpClient httpClient = HttpClientBuilder.create().disableRedirectHandling().build();
        HttpResponse response = httpClient.execute(new HttpGet("http://www.baidu.com"));
        System.out.println("reponse: "+response.getStatusLine().getStatusCode());
    }
}
