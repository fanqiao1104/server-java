package com.sleep.server.task;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
//import com.sleep.server.dao.mapper.generator.ContentOrderMapper;
import com.sleep.server.dao.IExample;
import com.sleep.server.dao.entity.ContentOrderExample;
import com.sleep.server.dao.entity.MemberOrder;
import com.sleep.server.dao.entity.MemberOrderExample;
import com.sleep.server.dao.mapper.generator.ContentOrderMapper;
import com.sleep.server.dao.mapper.generator.MemberOrderMapper;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.sleep.server.dao.entity.ContentOrder;

/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 */

@Component
public class FetchOrderTask extends  IJobHandler {
    @Autowired
     private ContentOrderMapper contentOrderMapper;

    @Autowired
    private MemberOrderMapper memberOrderMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String server="http://openapi.haoxinqing.cn";
    private String saasInfoId="10017";
    private String key="2dfe8c04c80406b104b9ee6bd61da10e";
    @XxlJob("fetchOrderTask")
    public ReturnT<String> execute(String s) throws Exception{

        XxlJobLogger.log("XXL-JOB, Hello World.");

        try {
            LocalDate today=LocalDate.now();
            LocalDate lastmonth=today.minusMonths(1);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
            Calendar calendar= Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONTH,-1);
            Integer dayOfLastMonth=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            Map<String, String> params = new HashMap<String, String>();
            params.put("pn","1");
            params.put("ps","100");
            params.put("saasInfoId",saasInfoId);
            params.put("startPayTime",formatters.format(lastmonth)+"-01");
            params.put("endPayTime",formatters.format(lastmonth)+"-"+dayOfLastMonth);
            params.put("signtime",System.currentTimeMillis() + "");
            fetchContentOrderPageable(params);
        } catch (URISyntaxException | IOException e) {
            throw e;
        }

        try {
            LocalDate today=LocalDate.now();
            LocalDate lastmonth=today.minusMonths(1);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
            Calendar calendar= Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(calendar.MONTH,-1);
            Integer dayOfLastMonth=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

            Map<String, String> params = new HashMap<String, String>();
            params.put("pn","1");
            params.put("ps","100");
            params.put("saasInfoId",saasInfoId);
            params.put("startPayTime",formatters.format(lastmonth)+"-01");
            params.put("endPayTime",formatters.format(lastmonth)+"-"+dayOfLastMonth);
            params.put("signtime",System.currentTimeMillis() + "");
            fetchMemberOrderPageable(params);
        } catch (URISyntaxException | IOException e) {
            throw e;
        }


        return SUCCESS;
    }

    private void fetchMemberOrderPageable(Map<String, String> params)throws Exception{

        String signature = getSign(key,params);
        params.put("signature",signature);

        try {
            URIBuilder uriBuilder = new URIBuilder(server + "/api/order/memberOrderList");
            uriBuilder.setParameter("pn",params.get("pn"));
            uriBuilder.setParameter("ps",params.get("ps"));
            uriBuilder.setParameter("saasInfoId", params.get("saasInfoId"));
            uriBuilder.setParameter("startPayTime", params.get("startPayTime"));
            uriBuilder.setParameter("endPayTime", params.get("endPayTime"));
            uriBuilder.setParameter("signtime", params.get("signtime"));
            uriBuilder.setParameter("signature", params.get("signature"));

            URI url = uriBuilder.build();
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpPost get = new HttpPost(url);
            HttpResponse response = client.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            XxlJobLogger.log(res);
            if(!res.startsWith("{")){
                throw new RuntimeException(res);
            }
            JSONObject jsonObject = new JSONObject(res);
            Integer code = jsonObject.getInt("code");
            if(code != null && code == 1000) {
                JSONObject result = jsonObject.getJSONObject("result");
                JSONArray list = result.getJSONArray("list");
                Integer pn = result.getInt("pn");
                Integer ps = result.getInt("ps");
                Integer size = list.size();

                for(int i = 0; i < size; i++){

                    JSONObject itemObject = list.getJSONObject(i);
                    MemberOrder item = new MemberOrder();
                    String orderNo = itemObject.getStr("orderNo");
                    MemberOrderExample example = new MemberOrderExample();
                    example.createCriteria().andOrderNoEqualTo(orderNo);
                    List<ContentOrder> memberOrders=  contentOrderMapper.selectByExample(example);

                    if(memberOrders.size()>0){
                        XxlJobLogger.log("member order "+orderNo+" is exist passed");
                        continue;
                    }
                    item.setOrderNo(orderNo);
                    item.setOrderMoney(itemObject.getBigDecimal("orderMoney"));
                    item.setActualMoney(itemObject.getBigDecimal("actualMoney"));
                    item.setUserId(itemObject.getStr("userId"));
                    item.setUserType(itemObject.getInt("userType"));
                    item.setPayTime(itemObject.getDate("payTime"));
                    item.setDiscountAmount(itemObject.getBigDecimal("discountAmount"));
                    item.setExpireTime(itemObject.getDate("expireTime"));
                    item.setMemberName(itemObject.getStr("memberName"));
                    XxlJobLogger.log(item.getOrderNo()+" "+item.getMemberName()+" "+item.getPayTime()+" "+item.getUserType()+" "+item.getOrderMoney()+" "+item.getActualMoney());
                    memberOrderMapper.insert(item);
                }
                if(size>=ps){
                    pn=pn+1;
                    params.put("pn",pn.toString());
                    fetchContentOrderPageable(params);
                }
            }
        }catch(URISyntaxException | IOException e){
            throw e;
        }

    }
    private void fetchContentOrderPageable(Map<String, String> params) throws Exception{

        String signature = getSign(key,params);
        params.put("signature",signature);

        try {
                URIBuilder uriBuilder = new URIBuilder(server + "/api/order/contentOrderList");
                uriBuilder.setParameter("pn",params.get("pn"));
                uriBuilder.setParameter("ps",params.get("ps"));
                uriBuilder.setParameter("saasInfoId", params.get("saasInfoId"));
                uriBuilder.setParameter("startPayTime", params.get("startPayTime"));
                uriBuilder.setParameter("endPayTime", params.get("endPayTime"));
                uriBuilder.setParameter("signtime", params.get("signtime"));
                uriBuilder.setParameter("signature", params.get("signature"));

                URI url = uriBuilder.build();
                CloseableHttpClient client = HttpClientBuilder.create().build();
                HttpPost get = new HttpPost(url);
                HttpResponse response = client.execute(get);
                String res = EntityUtils.toString(response.getEntity());
                XxlJobLogger.log(res);
                if(!res.startsWith("{")){
                    throw new RuntimeException(res);
                }
                JSONObject jsonObject = new JSONObject(res);
                Integer code = jsonObject.getInt("code");
                if(code != null && code == 1000) {
                    JSONObject result = jsonObject.getJSONObject("result");
                    JSONArray list = result.getJSONArray("list");
                    Integer pn = result.getInt("pn");
                    Integer ps = result.getInt("ps");
                    Integer size = list.size();

                    for(int i = 0; i < size; i++){

                        JSONObject itemObject = list.getJSONObject(i);
                        ContentOrder item = new ContentOrder();
                        String orderNo = itemObject.getStr("orderNo");
                        ContentOrderExample example = new ContentOrderExample();
                        example.createCriteria().andOrderNoEqualTo(orderNo);
                        List<ContentOrder> contentOrders=  contentOrderMapper.selectByExample(example);

                        if(contentOrders.size()>0){
                            XxlJobLogger.log("order "+orderNo+" is exist passed");
                            continue;
                        }
                        item.setThirdId(itemObject.getStr("thirdId"));
                        item.setOrderNo(orderNo);
                        item.setOrderMoney(itemObject.getBigDecimal("orderMoney"));
                        item.setActualMoney(itemObject.getBigDecimal("actualMoney"));
                        item.setUserId(itemObject.getStr("userId"));
                        item.setUserType(itemObject.getInt("userType"));
                        item.setContentTitle(itemObject.getStr("contentTitle"));
                        item.setCreateTime(itemObject.getDate("createTime"));
                        item.setPayTime(itemObject.getDate("payTime"));
                        item.setDiscountAmount(itemObject.getBigDecimal("discountAmount"));
                        item.setExpireTime(itemObject.getDate("expireTime"));
                        item.setSupplierProportion(itemObject.getInt("supplierProportion"));
                        XxlJobLogger.log(item.getOrderNo()+" "+item.getUserId()+" "+item.getPayTime()+" "+item.getUserType()+" "+item.getOrderMoney()+" "+item.getActualMoney());
                        contentOrderMapper.insert(item);
                    }
                    if(size>=ps){
                        pn=pn+1;
                        params.put("pn",pn.toString());
                        fetchContentOrderPageable(params);
                    }
                }
            }catch(URISyntaxException | IOException e){
                    throw e;
            }
        }

    /**
     * 获取签名值
     *
     * @param secretKey
     * @param params
     *请求参数
     * @return
     */
    public static String getSign(String secretKey, Map<String, String> params) {

        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        for (String key : keys) {
            if (!"signature".equals(key)) {
                String value = params.get(key);
                query.append(key);
                query.append(value);
//                for (String string : value) {
//                    query.append(string);
//                }
            }

        }
        XxlJobLogger.log("ThirdPartyInterceptor 加密前：" + secretKey + " query=" + query.toString());
        return MD5(secretKey + query.toString()).toUpperCase();
    }

    public static String MD5(String str) {

        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(str.getBytes("UTF-8"));
            BigInteger bigInt = new BigInteger(1, m.digest());
            String hashtext = bigInt.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;

        } catch (Exception e) {
            throw new RuntimeException("MD5 init failed.", e);
        }
    }

}
