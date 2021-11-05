package com.sydney.vacbook.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ApiController {

    @RequestMapping("/api/getData")
    @ResponseBody
    public String getData() throws IOException {
        String callUrl = "https://nswdac-covid-19-postcode-heatmap.azurewebsites.net/datafiles/stats.json";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request  = new Request.Builder().url(callUrl).method("GET",null).build();
        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
        return response.body().string();
    }

    @RequestMapping("/api/getCaseLocation")
    @ResponseBody
    public String getCaseLocation() throws IOException {
        String callUrl = "https://data.nsw.gov.au/data/dataset/0a52e6c1-bc0b-48af-8b45-d791a6d8e289/resource/f3a28eed-8c2a-437b-8ac1-2dab3cf760f9/download/covid-case-locations-20210717-1753.json";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request  = new Request.Builder().url(callUrl).method("GET",null).build();
        Response response = client.newCall(request).execute();
//        System.out.println(response.body().string());
        return response.body().string();
    }
}
