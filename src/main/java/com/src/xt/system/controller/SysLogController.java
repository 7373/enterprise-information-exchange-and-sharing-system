/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.system.dto.SysLogDto;
import com.icinfo.cs.system.service.ISysLogService;
import com.icinfo.framework.core.web.BaseController;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageResponse;

/**
 * 描述:    cs_sys_log 对应的Controller类.<br>
 *
 * @author framework generator
 * @date 2016年09月18日
 */
@Controller
@RequestMapping("/admin/system/syslog")
public class SysLogController extends BaseController {
	@Autowired
	private ISysLogService sysLogService;
	
	/**
	 * 
	 * 描述: 进入日志列表页面
	 * @auther chenxin
	 * @date 2016-09-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list",method= RequestMethod.GET)
	public ModelAndView list() throws Exception{
		ModelAndView view = new ModelAndView("/system/syslog/syslog_list");
		view.addObject("yearList",DateUtil.getYearToNowForReport());
		return view;
	}
	
	/**
     * 描述：获取列表Json数据
     * @author chenxin
     * @date 2016-09-11
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/list.json",method= RequestMethod.GET)
    @ResponseBody
    public PageResponse<SysLogDto> listJSON(PageRequest request) throws Exception {
		List<SysLogDto> data = sysLogService.queryPage(request);
        return new PageResponse<SysLogDto>(data);
    }
	
	/**
	 * 描述：进入申请修改审核详细页面
	 * @author chenxin
	 * @date 2016-09-11
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/show",method = RequestMethod.GET)
	public ModelAndView editModApplicationPage(@RequestParam(required = false) Integer sysLogId) throws Exception {
		if(sysLogId <= 0){
			return null;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", sysLogId);
		SysLogDto sysLogDto =  sysLogService.selectSysLogDtoById(map);
        ModelAndView view = new ModelAndView("/system/syslog/syslog_edit");
		view.addObject(sysLogDto);
		return view;
	}
	
}