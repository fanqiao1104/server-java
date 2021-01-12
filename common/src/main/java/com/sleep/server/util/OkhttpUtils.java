package com.sleep.server.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OkhttpUtils {

    // 高德key
    private static final String GAODE_KEY = "e7dc70865ad69ac9bd3c15fae2ff0332";

    /**
     * get 请求
     * @param url
     * @param paramsMap
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, Object> paramsMap) throws Exception {
        StringBuilder params = null;
        if (!paramsMap.isEmpty()){
            params = new StringBuilder();
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()){
                params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        if (Objects.nonNull(params)){
            url = url + "?" + params.toString().substring(0, params.toString().length() - 1);
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return  response.body().string();
    }


    /**
     * post请求
     * @param url
     * @param paramsMap
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, Object> paramsMap) throws Exception {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        if (!paramsMap.isEmpty()){
            for (Map.Entry<String, Object> entry : paramsMap.entrySet()){
                bodyBuilder.add(entry.getKey(), new ObjectMapper().writeValueAsString(entry.getValue()));
            }
        }
        FormBody body = bodyBuilder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 获取指定地址的经纬度
     * @param address
     * @return
     */
    public static String getLongLatByAddress(String address) throws Exception {
        String url = "https://restapi.amap.com/v3/geocode/geo";
        Map<String, Object> paramsMap = new HashMap<>(2);
        paramsMap.put("key", GAODE_KEY);
        paramsMap.put("address", address);
        String response = get(url, paramsMap);
        System.err.println("response:"+response);
        // 解析
        String longlat = "";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        List<JsonNode> geocodes = jsonNode.findValues("geocodes");
        if (CollectionUtils.isNotEmpty(geocodes) && geocodes.size() > 0){
            longlat = geocodes.get(0).findValue("location").asText();
        }
        return longlat;
    }

    public static void main(String[] args) throws Exception {

    }
}
