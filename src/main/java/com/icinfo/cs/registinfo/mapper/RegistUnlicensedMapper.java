/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2016年09月09日
 * @version 2.0
 */
package com.icinfo.cs.registinfo.mapper;

import com.icinfo.cs.registinfo.dto.RegistInfoDto;
import com.icinfo.cs.registinfo.model.RegistUnlicensed;
import com.icinfo.framework.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 描述:    cs_regist_unlicensed 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2016年09月09日
 */
public interface RegistUnlicensedMapper extends Mapper<RegistUnlicensed> {

    int batchInsertBySuperNo(RegistUnlicensed registUnlicensed);

    List<RegistUnlicensed> selectSuperItem(RegistInfoDto registInfoDto);
    RegistUnlicensed selectSuperItemOne(String priPID);
}