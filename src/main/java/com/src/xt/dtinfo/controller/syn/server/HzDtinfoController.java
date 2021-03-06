package com.icinfo.cs.dtinfo.controller.syn.server;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.dtinfo.model.HzDtinfoHis;
import com.icinfo.cs.dtinfo.service.IHzDtinfoHisService;
import com.icinfo.cs.system.controller.CSBaseController;


/**
 * 描述:    cs_pub_dtinfo 对应的Controller类.<br>
 *
 * @author framework generator
 * @date 2016年10月18日
 */
@Controller
@RequestMapping({"/hz/pubdtinfo"})
public class HzDtinfoController extends CSBaseController{
	
	@Autowired
	private IHzDtinfoHisService hzDtinfoHisService;
	
	
	/**
	 * 描述 :杭州，诸暨需求 双告知Excel导入页面
	 * @author yujingwei
	 * @date 2017年06月20日  
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView pubOpanoMalyListPage() throws Exception{
		ModelAndView view  = new ModelAndView("/syn/system/pubdtinfo/hzdtinfo_excel");
		return view;
	}
	
	 /**
     * 描述：excel批量导入
     * @author yujingwei
     * @date 2017年06月20日 
     * @throws Exception
     */
	@RequestMapping(value="/addbatch",method= RequestMethod.POST)
    @ResponseBody
    public void addbatch(@RequestParam("file") MultipartFile file,HttpServletResponse response,String districtType) throws Exception {
        InputStream in = file.getInputStream();
        Map<String, Object> resultMap = hzDtinfoHisService.doSavePubDtInfoExcelBatch(in,districtType);
        JSONObject json = new JSONObject();
        if(resultMap != null){
        	json.put("status", "success");
            json.put("errorInfo", resultMap.get("errorInfo"));
        }else{
        	json.put("status", "error");
        }
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(json);
        response.getWriter().flush();
        response.getWriter().close();//关闭流
    }
	
	
	/**
	 * 描述   杭州，诸暨双告知接口
	 * @author yujingwei
	 * @date 2017年06月20日 
	 * @param token，districtType，args
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = "/hzzjdtinfo.json")
	@ResponseBody
	public JSONObject hzzjPubdtinfo(String token,String districtType,String args) throws Exception {
		JSONObject jsonObj = hzDtinfoHisService.doWorkPubDtInfoForHzzj(token,districtType,args);
		if(StringUtil.isNotBlank(args)){
			JSONObject jsonObjDtInfo = JSONObject.fromObject(StringEscapeUtils.unescapeHtml4(args));
			if(jsonObjDtInfo != null){
				// 保存历史记录
				HzDtinfoHis InsertOrUpdateInfo = new HzDtinfoHis();
				InsertOrUpdateInfo = hzDtinfoHisService.doOptKeyOrValueHandle(InsertOrUpdateInfo,jsonObjDtInfo);
				if(jsonObj !=null && jsonObj.containsKey("returnCode")){
					String matchMsgInfo = jsonObj.get("msg").toString();
					String returnCode = jsonObj.get("returnCode").toString();
					// 返回信息
					InsertOrUpdateInfo.setMatchMsg(matchMsgInfo);
					InsertOrUpdateInfo.setIsMatch(returnCode);
				}
				// 地区编码
				InsertOrUpdateInfo.setDistrictCode(districtType);
				// 地区名称
				InsertOrUpdateInfo.setDistrictName(districtType);
				hzDtinfoHisService.insertHzzjDtInfo(InsertOrUpdateInfo);
			}
		}
		return jsonObj;
	}
	
}
