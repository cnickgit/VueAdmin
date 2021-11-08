package com.markerhub.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.markerhub.common.dto.PcConstant;
import com.markerhub.common.lang.Result;
import com.markerhub.service.DsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class DsServiceImpl implements DsService {
    @Override
    public Result getDsData(String searchName, String code) {
        JSONObject jsonObject = null;
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxODkyNDAwMTA4NSIsImlkIjo0Nzk1MDUsImV4cCI6MTYzNjk0NTUxMCwiaWF0IjoxNjM2MzQwNzEwLCJqdGkiOiJiZjkyYjgxNC05ZmI2LTQ5YTYtYWRiOS0yNjc3YTQxMDE3MzkiLCJ1c2VybmFtZSI6IjE4OTI0MDAxMDg1In0.wkaC2Sg4boxyMPOPP_r_qtkTRW0-u1QwY9fJXe8_BQE";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("token", token);
        map.add("nick", searchName);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(PcConstant.DS_ADDRESS, request, String.class);
        jsonObject = JSONObject.parseObject(response.getBody());
        return Result.succ(jsonObject);
    }
}
