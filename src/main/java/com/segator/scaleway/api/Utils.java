/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.segator.scaleway.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.segator.scaleway.api.constants.ScalewayConstants;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

/**
 *
 * @author isaac_000
 */
public class Utils {
 public static HttpRequestBase buildRequest(String type, String typeUrl, String method, String accessToken) {
        String requestPath = new StringBuilder(typeUrl).append("/").append(method).toString();
        return buildRequest(type, requestPath, accessToken);
 }
    
    
    public static HttpRequestBase buildRequest(String type, String requestPath, String accessToken) {
        HttpRequestBase request = null;
        switch (type) {
            case "POST":
                request = new HttpPost(requestPath);
                break;
            case "GET":
                request = new HttpGet(requestPath);
                break;
            case "DELETE":
                request = new HttpDelete(requestPath);
                break;
        }
        request.setHeader(ScalewayConstants.HEADER_AUTH_TOKEN, accessToken);
        request.setHeader(HttpHeaders.CONTENT_TYPE, ScalewayConstants.JSON_APPLICATION);
        return request;
    }

    public static ObjectMapper initializeObjectMapperJson() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        mapper.enable(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS);
        return mapper;
    }

    public static <T> T parseJson(HttpEntity responseEntity, Class<T> type) throws IOException {

        String encoding = responseEntity.getContentEncoding() != null ? responseEntity.getContentEncoding().getValue() : "UTF-8";
        String jsonString = IOUtils.toString(responseEntity.getContent(), encoding);
        try {
            return initializeObjectMapperJson().readValue(jsonString, type);
        } catch (Exception ex) {
            System.out.println(jsonString);
            throw ex;
        }
    }

    public static String formatJson(Object entity) throws JsonProcessingException {
        return initializeObjectMapperJson().writeValueAsString(entity);
    }
    
    
}
