package com.rest.client;

/*
 * @Project WeatherRestAPI
 * @author  pritipradhan
 */

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    public CloseableHttpResponse get(String url) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);

        return closeableHttpResponse;

    }
}
