package com.markerhub.service;

import com.markerhub.common.lang.Result;

public interface DsService {

    /**
     * 查询电商基本信息
     * @param searchName
     * @param code
     * @return
     */
    public Result getDsData(String searchName, String code);

    /**
     * 近期购买记录
     * @param searchName
     * @param code
     * @return
     */
    public Result getPurchaseRecordsList(String searchName, String code);
}
