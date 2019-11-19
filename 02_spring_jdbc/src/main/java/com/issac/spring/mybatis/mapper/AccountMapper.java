package com.issac.spring.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author: ywy
 * @date: 2019-10-28
 * @desc:
 */
public interface AccountMapper {
    void update(@Param("name") String name,@Param("money") Double money);
    double queryMoney(@Param("name") String name);
}
