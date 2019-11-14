package com.aysaml.common.feign.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aysaml.common.feign.constant.Constants;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Feign signature interceptor , to add signature.
 *
 * @author wangning
 * @date 2019-10-29
 */
public class SignatureRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            String appKey = (String) attributes.getAttribute(Constants.FEIGN_APPKEY, 0);
            String appSecret = (String) attributes.getAttribute(Constants.FEIGN_APPSECRET, 0);
            if (null != appKey && null != appSecret) {
                // Signature or token authentication logic.
            }
        }
    }

    /**
     * Put the sign and parameter to body.
     *
     * @param requestTemplate
     * @param mapToSign
     */
    private void putParameterMapToBody(
            RequestTemplate requestTemplate, Map<String, Object> mapToSign) {
        String body = JSON.toJSONString(mapToSign, SerializerFeature.WriteMapNullValue);
        requestTemplate.body(body);
    }

    /**
     * To get parameters from requestTemplate.
     *
     * @param requestTemplate
     * @return
     */
    private Map<String, Object> getParametersMap(RequestTemplate requestTemplate) {
        Map<String, Object> map = new HashMap<>(16);
        for (Map.Entry<String, Collection<String>> entry : requestTemplate.queries().entrySet()) {
            map.put(entry.getKey(), ((Collection) entry.getValue()).iterator().next());
        }
        String body;
        if (null != requestTemplate.body()) {
            try {
                body = new String(requestTemplate.body(), StandardCharsets.UTF_8);
                JSONObject jsonBody = JSONObject.parseObject(body);
                jsonBody.keySet().forEach((key) -> map.put(key, jsonBody.getString(key)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
