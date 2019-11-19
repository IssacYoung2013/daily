package com.issac.bfxy.store.service.api;

import java.util.Date;

/**
 * @author: ywy
 * @date: 2019-11-17
 * @desc:
 */
public interface StoreServiceApi {
    int selectVersion(String supplierId, String goodsId);

    int updateStoreCountByVersion(int version, String supplierId, String goodsId, String updateBy, Date updateTime);

    int selectStoreCount(String supplierId, String goodsId);
}
