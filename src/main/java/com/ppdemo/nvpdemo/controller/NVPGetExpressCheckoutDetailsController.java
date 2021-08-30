package com.ppdemo.nvpdemo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ppdemo.nvpdemo.common.ConstantVariable;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nvp")
public class NVPGetExpressCheckoutDetailsController {

    @PostMapping("GetExpressCheckoutDetails")
    public String getExpressCheckoutDetails(@RequestParam String token){

        String responseBody ="";
        try {    
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(ConstantVariable.NVP_SANDBOX_URL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("USER", ConstantVariable.USER));
            params.add(new BasicNameValuePair("PWD", ConstantVariable.PWD));
            params.add(new BasicNameValuePair("SIGNATURE", ConstantVariable.SIGNATURE));
            params.add(new BasicNameValuePair("VERSION", ConstantVariable.VERSION));
            params.add(new BasicNameValuePair("METHOD", "GetExpressCheckoutDetails"));
            params.add(new BasicNameValuePair("TOKEN", token));
            
            httpPost.setEntity(new UrlEncodedFormEntity(params));

    
            CloseableHttpResponse response = client.execute(httpPost);
            // HttpEntity entity = response.getEntity();
            responseBody = java.net.URLDecoder.decode(EntityUtils.toString(response.getEntity(),"UTF-8"));
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseBody;

    }
    
}
