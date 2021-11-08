package com.markerhub.service;

import com.markerhub.common.lang.Result;

public interface DsService {

    /**
     * 获取查询的电商数据
     * @return
     */
    public Result getDsData(String searchName, String code);
}
