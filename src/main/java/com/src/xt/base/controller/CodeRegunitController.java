/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.base.controller;

import java.util.List;
import java.util.Map;

import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.system.controller.CSBaseController;
import com.icinfo.cs.system.dto.SysUserDto;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.base.service.ICodeRegunitService;
import com.icinfo.framework.common.ajax.AjaxResult;
import com.icinfo.framework.core.web.BaseController;

/**
 * 描述:    cs_code_regunit 对应的Controller类.<br>
 *
 * @author framework generator
 * @date 2016年09月10日
 */
@Controller
@RequestMapping("/commom/server/regunit/")
public class CodeRegunitController extends CSBaseController {
	 @Autowired
	    private ICodeRegunitService codeRegunitService;
	/**
     *
     * 描述: to管辖单位树状选择页面
     * @auther ljx
     * @date 2016年10月27日
     * @return
     * @throws Exception
     */
	@RequestMapping("/regunitmutiselect")
	public ModelAndView toRegUnitTreeView(String isPermissionCheck)throws Exception{
	   ModelAndView view=new ModelAndView("/common/select/regunitmutiselect");
	   view.addObject("isPermissionCheck", isPermissionCheck);
	   return view;
   }
	/**
	 *
	 * 描述: 管辖单位单选
	 * @auther ljx
	 * @date 2016年11月18日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regunitsingselect")
	public ModelAndView toRegUnitSingleTreeView(String isPermissionCheck)throws Exception{
	   ModelAndView view=new ModelAndView("/common/select/regunitsingselect");
		view.addObject("isPermissionCheck", isPermissionCheck);
	   return view;
   }




	/**
	 *
	 * 描述: 只选择最后一级
	 * @auther ljx
	 * @date 2016年11月2日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regunitmutiselectnocheck")
	public ModelAndView toRegUnitTreeNoCheckView(String isPermissionCheck)throws Exception{
		  ModelAndView view=new ModelAndView("/common/select/regunitmutiselectnocheck");
		view.addObject("isPermissionCheck", isPermissionCheck);
		return view;
	}

	/**
     *
     * 描述: to管辖单位树状选择页面
     * @auther ljx
     * @date 2016年10月27日
     * @return
     * @throws Exception
     */
	@RequestMapping("/regunitmutiselectpermission")
	public ModelAndView toRegUnitTreeViewPermission(String isPermissionCheck)throws Exception{
	   ModelAndView view=new ModelAndView("/common/select/regunitmutiselect");
	   view.addObject("isPermissionCheck", isPermissionCheck);
	   return view;
   }

	/**
	 *
	 * 描述: 管辖单位json
	 * @auther ljx
	 * * modi by liuhq 增加权限限制isPermissionCheck
	 * @date 2016年10月19日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/treeRegUnitListJSON")
	@ResponseBody
	public AjaxResult treeRegUnitListJSON(String isPermissionCheck)throws Exception{
		SysUserDto sysUser = (SysUserDto) getSession().getAttribute(Constants.SESSION_SYS_USER);
		//应用权限 add by liuhq
		Map param = new HashedMap();
		if("true".equals(isPermissionCheck)){
			String whereValBySysuser=getWhereValBySysUser("");
			param.put("regUnit_permission_limit", whereValBySysuser);//权限限制字段
			List<Map<String,Object>> data=codeRegunitService.selectToTreeMap(param);
			if(StringUtil.isBlank(whereValBySysuser)){
				return AjaxResult.success("查询成功",data);
			}
			List<Map<String,Object>> dataAll=codeRegunitService.selectProv("false");
			dataAll.addAll(data);
			return AjaxResult.success("查询成功",dataAll);
		}
		List<Map<String,Object>> data=codeRegunitService.selectToTreeMap(param);
		return AjaxResult.success("查询成功",data);
	}
	/**
	 * 
	 * 描述: 选择最后一级
	 * @auther ljx
	 * modi by liuhq 增加权限限制isPermissionCheck
	 * @date 2016年11月2日 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/treeRegUnitListNoCheckJSON")
	@ResponseBody
	public AjaxResult treeRegUnitListNoCheckJSON(String isPermissionCheck)throws Exception{
		//应用权限 add by liuhq
		Map param = new HashedMap();
		if("true".equals(isPermissionCheck)){
			String whereValBySysuser=getWhereValBySysUser("");
			param.put("regUnit_permission_limit", whereValBySysuser);//权限限制字段
			List<Map<String,Object>> data=codeRegunitService.selectToTreeMap(param);
			if(StringUtil.isBlank(whereValBySysuser)){
				return AjaxResult.success("查询成功",data);
			}
			List<Map<String,Object>> dataAll=codeRegunitService.selectProv("true");
			dataAll.addAll(data);
			return AjaxResult.success("查询成功",dataAll);
		}
		List<Map<String,Object>> data=codeRegunitService.selectToTreeMap(param);
		return AjaxResult.success("查询成功",data);
		
	}

	/**
	 * 选择区域
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectArea")
	public ModelAndView toSelectArea(String isPermissionCheck)throws Exception{
		ModelAndView view=new ModelAndView("/common/select/select_area");
		return view;
	}

	/**
	 * 区域JSON
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectAreaJSON")
	@ResponseBody
	public AjaxResult selectAreaJSON()throws Exception{
		Map param = new HashedMap();
		List<Map<String,Object>> data=codeRegunitService.selectArea(param);
		return AjaxResult.success("查询成功",data);
	}

}