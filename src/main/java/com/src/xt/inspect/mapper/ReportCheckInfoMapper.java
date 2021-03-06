/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 * 描述: TODO <br/>
 *
 * @author framework generator
 * @date 2017年01月17日
 * @version 2.0
 */
package com.icinfo.cs.inspect.mapper;

import com.icinfo.cs.inspect.dto.ReportCheckInfoDto;
import com.icinfo.cs.inspect.model.ReportCheckCode;
import com.icinfo.cs.inspect.model.ReportCheckInfo;
import com.icinfo.framework.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 描述:  cs_report_check_info 对应的Mapper接口.<br>
 *
 * @author framework generator
 * @date 2017年01月17日
 */
public interface ReportCheckInfoMapper extends Mapper<ReportCheckInfo> {
    List<ReportCheckInfoDto> inspectInputInfoQueryPage(Map<String, Object> params);

    int getTotalFromCheckInfo(Map params);

    int inspectInputInfoQueryPage_total(Map<String, Object> params);

    List<ReportCheckInfoDto> inspectYrInfoQueryPage(Map params);

    List<ReportCheckInfoDto> inspectCheckInfoQueryPage(Map<String, Object> params);
    List<ReportCheckInfoDto> inspectViewInfoQueryPage(Map<String, Object> params);

    Object countWaitCheckNum(Map<String, Object> params);

    Object countCheckNoNum(Map<String, Object> params);

    List<ReportCheckInfoDto> inspectInputInfoQueryPage_all(Map<String, Object> params);
    
    /**
     * 描述:新增补报核查记录
     * @author chenxin
     * @date 2017-02-03
     * @param request
     * @return
     */
    List<ReportCheckInfoDto> inspectInputInfoQueryPageNew(Map<String, Object> params);
}