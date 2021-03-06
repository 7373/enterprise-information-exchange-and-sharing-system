/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年08月28日
 * @version 2.0
 */
package com.icinfo.cs.yr.mapper;

import com.icinfo.cs.yr.model.YrSubcapital;
import com.icinfo.framework.mybatis.mapper.common.Mapper;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 描述:    cs_yr_subcapital 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2016年08月28日
 */
public interface YrSubcapitalMapper extends Mapper<YrSubcapital> {


    BigDecimal rj_sumMoeny(Map<String, Object> queryMap);

    BigDecimal sj_sumMoeny(Map<String, Object> queryMap);

    /**
     * 查询列表
     * @author:wangjin
     * @param params
     * @return
     */
    List<YrSubcapital> doGetYrSubcapitalList(Map<String, Object> params);


  /*  *//**
     * 列表查询
     * @author: wangjin
     * @param params
     * @return
     *//*
    List<YrSubcapital> search_queryPage(Map<String, Object> params);*/
}