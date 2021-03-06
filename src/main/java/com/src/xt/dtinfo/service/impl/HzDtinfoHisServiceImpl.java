/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.dtinfo.service.impl;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.icinfo.cs.base.model.CodeLicense;
import com.icinfo.cs.base.service.ICodeLicenseService;
import com.icinfo.cs.common.utils.DateUtil;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.dtinfo.mapper.HzDtinfoHisMapper;
import com.icinfo.cs.dtinfo.mapper.PubDtInfoMapper;
import com.icinfo.cs.dtinfo.model.HzDtinfoHis;
import com.icinfo.cs.dtinfo.model.PubDtInfo;
import com.icinfo.cs.dtinfo.service.IHzDtinfoHisService;
import com.icinfo.cs.ext.model.MidBaseInfo;
import com.icinfo.cs.ext.service.IMidBaseInfoService;
import com.icinfo.cs.system.model.SysDepart;
import com.icinfo.cs.system.service.ISysDepartService;
import com.icinfo.cs.upload.utils.ExcelUtil;
import com.icinfo.framework.core.service.support.MyBatisServiceSupport;

/**
 * 描述:    cs_pub_hzdtinfo_his 对应的Service接口实现类.<br>
 *
 * @author framework generator
 * @date 2017年06月20日
 */
@Service
public class HzDtinfoHisServiceImpl extends MyBatisServiceSupport implements IHzDtinfoHisService {
	
	@Autowired
	PubDtInfoMapper pubDtInfoMapper;
	
	@Autowired
	ICodeLicenseService codeLicenseService;
	
	@Autowired
	ISysDepartService sysDepartService;
	
	@Autowired
	private IMidBaseInfoService midBaseInfoService;  
	
	@Autowired
    HzDtinfoHisMapper hzDtinfoHisMapper;
	
	private static final String key = "3b679f7cf55011e5bb52000188T839ae8";
	private static final String districtType01 = "01"; // 杭州
	private static final String districtType02 = "02"; // 诸暨
	
	
	
	/**
     * 描述：excel批量导入
     * @author yujingwei
     * @date 2017年06月20日 
     * @throws Exception
     */
	@SuppressWarnings("unchecked")
	public Map<String, Object> doSavePubDtInfoExcelBatch(InputStream inputStream ,String districtType) throws Exception{
		if(inputStream == null){
			return null;
		}
		StringBuilder strBuff =  new StringBuilder();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> returnMap = excelTranslateToPubDtInfo(inputStream);
		if(returnMap != null && returnMap.get("pubDtInfoList") !=null){
			List<PubDtInfo> excelForPubDtInfos = (List<PubDtInfo>) returnMap.get("pubDtInfoList");
			if(excelForPubDtInfos !=null && excelForPubDtInfos.size() > 0){
				for(PubDtInfo pubDtInfo : excelForPubDtInfos){
					String jsonStr = JSON.toJSONString(pubDtInfo);
					if(jsonStr !=null){
						// 杭州，诸暨双告知接口
						JSONObject jsonObject = this.doWorkPubDtInfoForHzzj(key,districtType,jsonStr);
						// 保存历史记录
						JSONObject jsonObjDtInfo = JSONObject.fromObject(StringEscapeUtils.unescapeHtml4(jsonStr));
						HzDtinfoHis InsertOrUpdateInfo = new HzDtinfoHis();
						InsertOrUpdateInfo = doOptKeyOrValueHandle(InsertOrUpdateInfo,jsonObjDtInfo);
						if(jsonObject !=null && jsonObject.containsKey("returnCode")){
							String matchMsgInfo = jsonObject.get("msg").toString();
							String retutnCode = jsonObject.get("returnCode").toString();
							// 返回信息
							InsertOrUpdateInfo.setMatchMsg(matchMsgInfo);
							InsertOrUpdateInfo.setIsMatch(retutnCode);
							if(retutnCode.equalsIgnoreCase("0")){
								if(StringUtil.isBlank(pubDtInfo.getPriPID())){
									strBuff.append("企业名称为"+pubDtInfo.getEntName()+':'+matchMsgInfo + '，');
								}else{
									strBuff.append("主体代码为"+pubDtInfo.getPriPID()+':'+matchMsgInfo + '，');
								}
							}
						}
						InsertOrUpdateInfo.setDistrictCode(districtType);
						InsertOrUpdateInfo.setDistrictName(districtType);
						this.insertHzzjDtInfo(InsertOrUpdateInfo);
					}
				}
			}
		}
		String errInfo = strBuff.toString(); 
		String rebackInfo = (String) returnMap.get("errorInfo");
		if(errInfo.length() > 0 && StringUtil.isNotEmpty(errInfo)){
			errInfo = errInfo.substring(0, errInfo.length()-1);
			if(rebackInfo !=null && !rebackInfo.equals("")){
				jsonMap.put("errorInfo", rebackInfo+"，"+errInfo);
			}else{
				jsonMap.put("errorInfo", errInfo);
			}
		}else{
			jsonMap.put("errorInfo",rebackInfo);
		}
		return jsonMap;
	}

	private Map<String, Object> excelTranslateToPubDtInfo(InputStream inputStream) {
		try {
			//解析excel
			Workbook workbook = WorkbookFactory.create(inputStream);
			Sheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows < 2) {
				return null;
			}
			StringBuilder strBuff =  new StringBuilder();
			List<PubDtInfo> pubDtInfoList = new ArrayList<PubDtInfo>();
			// 遍历处理每行
			for (int i = 1; i < rows; i++) {
				PubDtInfo pubDtInfo = new PubDtInfo();
				Row row = sheet.getRow(i);
				if(row == null){
					continue;
				}
				pubDtInfo.setPriPID(ExcelUtil.getCellContent(row.getCell(0)));
				pubDtInfo.setExaCode(ExcelUtil.getCellContent(row.getCell(10)));
				pubDtInfo.setCheckDep(ExcelUtil.getCellContent(row.getCell(12)));
				pubDtInfo.setUniSCID(ExcelUtil.getCellContent(row.getCell(1)));
				pubDtInfo.setEntName(ExcelUtil.getCellContent(row.getCell(2)));
				pubDtInfo.setRegNO(ExcelUtil.getCellContent(row.getCell(3)));
				pubDtInfo.setExaName(ExcelUtil.getCellContent(row.getCell(11)));
				pubDtInfo.setCheckDepName(ExcelUtil.getCellContent(row.getCell(13)));
				pubDtInfo.setCheckPushDate(checkWorkTime(ExcelUtil.getCellContent(row.getCell(15))));
				pubDtInfo.setClaimCode(ExcelUtil.getCellContent(row.getCell(16)));
				pubDtInfo.setClaimName(ExcelUtil.getCellContent(row.getCell(17)));
				pubDtInfo.setClaimDate(checkWorkTime(ExcelUtil.getCellContent(row.getCell(18))));
				pubDtInfo.setDutyDeptCode(ExcelUtil.getCellContent(row.getCell(19)));
				pubDtInfo.setDutyDeptName(ExcelUtil.getCellContent(row.getCell(20)));
				String checkRegDesc = ExcelUtil.getCellContent(row.getCell(14));
				if("新设".equalsIgnoreCase(checkRegDesc)){
					checkRegDesc = "1";
				}else if("变更".equalsIgnoreCase(checkRegDesc)){
					checkRegDesc = "2";
				}else{
					checkRegDesc = null;
				}
				pubDtInfo.setCheckRegType(checkRegDesc);
				pubDtInfoList.add(pubDtInfo);
			}
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("pubDtInfoList", pubDtInfoList);
		    String errorInfo = strBuff.toString();
		    if(errorInfo.length() > 0 && StringUtil.isNotEmpty(errorInfo)){
		    	returnMap.put("errorInfo",  errorInfo.substring(0, errorInfo.length()-1));
		    }else{
		    	returnMap.put("errorInfo","");
		    }
			return returnMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
    /**
	 * 描述   杭州，诸暨双告知接口
	 * @author yujingwei
	 * @date 2017年06月20日 
	 * @param token，districtType，args
	 * @return AjaxResult
	 * @throws Exception
	 */
	public JSONObject doWorkPubDtInfoForHzzj(String token, String districtType,String args) throws Exception{
		JSONObject jsonObj = new JSONObject();
		try {
			// 开始前的参数校验
			if(!key.equals(token)){
				returnMsgInfo("false","token会话口令验证不通过","0",jsonObj);
				return jsonObj;  
			}
			if(!districtType.equals(districtType01) && !districtType.equals(districtType02)){
				returnMsgInfo("false","地区验证不通过","0",jsonObj);
				return jsonObj;  
			}
			if(StringUtil.isBlank(args)){
				returnMsgInfo("false","参数不能为空","0",jsonObj);
   				return jsonObj;  
   			}
			
			int updateSuccessTotal = 0;
			int insertSuccessTotal = 0;
			JSONObject jsonObjDtInfo = JSONObject.fromObject(StringEscapeUtils.unescapeHtml4(args));
			if(jsonObjDtInfo != null){
				PubDtInfo InsertOrUpdateInfo = new PubDtInfo();
				// 主体代码
				String priPID = getContent(jsonObjDtInfo.get("priPID"));
				InsertOrUpdateInfo.setPriPID(priPID);
				// 审批事项编码
				String exaCode = getContent(jsonObjDtInfo.get("exaCode"));
				InsertOrUpdateInfo.setExaCode(exaCode);
				// 审批事项名称
				String exaName = getContent(jsonObjDtInfo.get("exaName"));
				InsertOrUpdateInfo.setExaName(exaName);
				// 审批部门编码
				String checkDep = getContent(jsonObjDtInfo.get("checkDep"));
				InsertOrUpdateInfo.setCheckDep(checkDep);
				InsertOrUpdateInfo.setDeptCode(checkDep);
				// 审批部门名称
				String checkDepName = getContent(jsonObjDtInfo.get("checkDepName"));
				InsertOrUpdateInfo.setCheckDepName(checkDepName);
				InsertOrUpdateInfo.setDeptName(checkDepName);
				// 推送时间
				Long checkPushDate = (Long) jsonObjDtInfo.get("checkPushDate");
				InsertOrUpdateInfo.setCheckPushDate(changeJsonFastTime(checkPushDate));
				// 认领人账号
				String claimCode = getContent(jsonObjDtInfo.get("claimCode"));
				InsertOrUpdateInfo.setClaimCode(claimCode);
				// 认领人姓名
				String claimName = getContent(jsonObjDtInfo.get("claimName"));
				InsertOrUpdateInfo.setClaimName(claimName);
				// 认领时间
				Long claimDate = (Long) jsonObjDtInfo.get("claimDate");
				InsertOrUpdateInfo.setClaimDate(changeJsonFastTime(claimDate));
				if(InsertOrUpdateInfo.getClaimDate() == null){
					InsertOrUpdateInfo.setClaimDate(InsertOrUpdateInfo.getCheckPushDate());
				}
				// 职能部门编码
				String dutyDeptCode = getContent(jsonObjDtInfo.get("dutyDeptCode"));
				InsertOrUpdateInfo.setDutyDeptCode(dutyDeptCode);
				// 职能部门
				String dutyDeptName = getContent(jsonObjDtInfo.get("dutyDeptName"));
				InsertOrUpdateInfo.setDutyDeptName(dutyDeptName);
				// 企业登记事项
				String checkRegType = getContent(jsonObjDtInfo.get("checkRegType"));
				InsertOrUpdateInfo.setCheckRegType(checkRegType);
				// 审批机关类型
				InsertOrUpdateInfo.setCheckDeptType("1");
				// 是否终止
				InsertOrUpdateInfo.setCheckStopState("0");
				// 认领状态
				InsertOrUpdateInfo.setClaimState("1");
				// 接收类型
				InsertOrUpdateInfo.setReceiveState("0");
				// 生成时间
				InsertOrUpdateInfo.setCreateTime(InsertOrUpdateInfo.getClaimDate());
				// 地区名称
				if(StringUtil.isNotBlank(checkDep)){
					String areaCode = checkDep.substring(0, 6);
					String areaName = "";
					if("330000".equals(areaCode)){
						areaName = "省政府";
					}else{
						List<SysDepart> sysDepartList=sysDepartService.doGetSysDepartByAdcode(areaCode);
						if(sysDepartList != null && sysDepartList.size() > 0){
							areaName = sysDepartList.get(0).getOrgName();
						}
					}
					InsertOrUpdateInfo.setAreaName(areaName);
				}
				
				// 参数校验
				jsonObj = doCheckArgsParam(priPID,exaCode,checkDep,dutyDeptCode,jsonObj);
				if(jsonObj!=null && jsonObj.containsKey("result")){
					return jsonObj;
				}
				// 判断是否存在
				List<PubDtInfo> pubDtInfos = doJudgeParamIsExsit(priPID,exaCode,checkDep);
				if(CollectionUtils.isNotEmpty(pubDtInfos)){
					if(pubDtInfos.size() == 1){
						// 已认领状态无须更新
						if(!pubDtInfos.get(0).getClaimState().equals("1")){
							 if(pubDtInfos.get(0).getCheckPushType().equals("0") 
						            || pubDtInfos.get(0).getCheckPushType().equals("3")){
								 // 分流状态
								 InsertOrUpdateInfo.setCheckPushType("1");
							 }
							 InsertOrUpdateInfo.setId(pubDtInfos.get(0).getId());
							 updateSuccessTotal =pubDtInfoMapper.updateByPrimaryKeySelective(InsertOrUpdateInfo);
						}else{
							returnMsgInfo("success","该企业已被认领","2",jsonObj);
							return jsonObj;
						}
					}else{
						for(PubDtInfo pubDtInfo : pubDtInfos){
							// 非审批状态不更新
							if(!pubDtInfo.getCheckDeptType().equals("1")){
								continue;
							}else{
								// 已认领状态无须更新
								if(!pubDtInfo.getClaimState().equals("1")){
									if(pubDtInfo.getCheckPushType().equals("0") 
								                 || pubDtInfo.getCheckPushType().equals("3")){
										InsertOrUpdateInfo.setCheckPushType("1");
									}
									InsertOrUpdateInfo.setId(pubDtInfo.getId());
									updateSuccessTotal = pubDtInfoMapper.updateByPrimaryKeySelective(InsertOrUpdateInfo);
								}else{
									returnMsgInfo("success","该企业已被认领","2",jsonObj);
									return jsonObj;
								}
							}
						}
					}
				}else{
					InsertOrUpdateInfo.setCheckPushType("1");
					MidBaseInfo baseInfo = midBaseInfoService.selectInfoByPriPID(priPID);
					if(baseInfo !=null){
						InsertOrUpdateInfo.setUniSCID(baseInfo.getUniCode());
						InsertOrUpdateInfo.setEntName(baseInfo.getEntName());
						InsertOrUpdateInfo.setRegCap(baseInfo.getRegCap());
						InsertOrUpdateInfo.setRegNO(baseInfo.getRegNO());
						InsertOrUpdateInfo.setRegOrg(baseInfo.getRegOrg());
						InsertOrUpdateInfo.setLeRep(baseInfo.getLeRep());
						InsertOrUpdateInfo.setTel(baseInfo.getTel());
						InsertOrUpdateInfo.setEstDate(baseInfo.getEstDate());
						InsertOrUpdateInfo.setDom(baseInfo.getDom());
						InsertOrUpdateInfo.setOpFrom(baseInfo.getOpFrom());
						InsertOrUpdateInfo.setOpTo(baseInfo.getOpTo());
						InsertOrUpdateInfo.setOpScope(baseInfo.getOpScope());
						InsertOrUpdateInfo.setApprDate(baseInfo.getApprDate());
						InsertOrUpdateInfo.setLocalAdm(baseInfo.getLocalAdm());
						InsertOrUpdateInfo.setDomDistrict(baseInfo.getDomDistrict());
						InsertOrUpdateInfo.setStreet(baseInfo.getStreet());
						InsertOrUpdateInfo.setRegState(baseInfo.getRegState());
						InsertOrUpdateInfo.setEntType(baseInfo.getEntType());
						InsertOrUpdateInfo.setEntTypeCatg(baseInfo.getEntTypeCatg());
						InsertOrUpdateInfo.setArea(baseInfo.getArea());
						InsertOrUpdateInfo.setIsIndivid(baseInfo.getIsIndivid());
						InsertOrUpdateInfo.setIndustryCo(baseInfo.getIndustryCo());
						insertSuccessTotal = pubDtInfoMapper.insert(InsertOrUpdateInfo);
					}else{
						returnMsgInfo("false","通过主体代码未找到该企业","0",jsonObj);
				        return jsonObj;
					}
				}
			}
			if(updateSuccessTotal > 0){
				returnMsgInfo("success","数据同步成功,同步类型为【变更】","1",jsonObj);
			}if(insertSuccessTotal > 0){
				returnMsgInfo("success","数据同步成功,同步类型为【新增】","1",jsonObj);
			}
		} catch (Exception e) {
	        e.printStackTrace();
	        returnMsgInfo("false","发生异常:"+e.getMessage(),"0",jsonObj);
	        return jsonObj;
		}
		return jsonObj;
	}
	
	/**
     * 参数校验
     * @param priPID，exaCode，checkDep，jsonObj
     * @return JSONObject
     */
	private JSONObject doCheckArgsParam(String priPID, String exaCode,String checkDep,String dutyDeptCode,JSONObject jsonObj) {
		try {
			if(StringUtil.isBlank(priPID)){
				returnMsgInfo("false","主体代码不能为空","0",jsonObj);
				return jsonObj;
			}
			if(StringUtil.isBlank(exaCode)){
				returnMsgInfo("false","审批事项编码不能为空","0",jsonObj);
				return jsonObj;
			}
			if(StringUtil.isBlank(checkDep)){
				returnMsgInfo("false","审批部门编码不能为空","0",jsonObj);
				return jsonObj;
			}
			if(StringUtil.isBlank(dutyDeptCode)){
				returnMsgInfo("false","职能部门编码不能为空","0",jsonObj);
				return jsonObj;
			}
			CodeLicense codeLicense=codeLicenseService.selectCodeLicenseByExaCodeAndLicType(exaCode, "2");
			if(codeLicense==null || "0".equals(codeLicense.getLicDeptType()) 
					|| StringUtil.isEmpty(codeLicense.getLicZone().trim())){
				returnMsgInfo("false","没有找到审批事项编码为【"+exaCode+"】的审批事项或审批部门不明确","0",jsonObj);
				return jsonObj;
			}
			List<SysDepart> sysDepartList = sysDepartService.doGetSysDepartByAdcode(checkDep);
			if(sysDepartList == null || sysDepartList.size() < 1){
				returnMsgInfo("false","没有找到部门编码为【"+checkDep+"】的审批部门","0",jsonObj);
				return jsonObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	/**
	 * 描述   插入杭州，诸暨双告知历史表信息
	 * @author yujingwei
	 * @date 2017年06月20日 
	 * @param insertOrUpdateInfo
	 * @return int
	 * @throws Exception
	 */
	public int insertHzzjDtInfo(HzDtinfoHis insertOrUpdateInfo) throws Exception{
		return hzDtinfoHisMapper.insert(insertOrUpdateInfo);
	}
	
	/**
     * 描述：通过主体代码，审批事项，审批机关编码查询
     * @author yujingwei
     * @date 2017-06-20
     * @param priPID，exaCode，checkDep
     * @return List<PubDtInfo>
     */
	private List<PubDtInfo> doJudgeParamIsExsit(String priPID, String exaCode,String checkDep) {
		PubDtInfo dtInfo = new PubDtInfo();
		dtInfo.setPriPID(priPID);
		dtInfo.setExaCode(exaCode);
		dtInfo.setCheckDep(checkDep);
		return pubDtInfoMapper.select(dtInfo);
	}
	
	/**
	 * 描述   json键值处理
	 * @param InsertOrUpdateInfo,jsonObjDtInfo
	 * @return String
	 */
	public HzDtinfoHis doOptKeyOrValueHandle(HzDtinfoHis InsertOrUpdateInfo,JSONObject jsonObjDtInfo) throws Exception {
		// 主体代码
		String priPID = getContent(jsonObjDtInfo.get("priPID"));
		InsertOrUpdateInfo.setPriPID(priPID);
		// 统一社会信用代码
		String uniSCID = getContent(jsonObjDtInfo.get("uniSCID"));
		InsertOrUpdateInfo.setUniSCID(uniSCID);
		// 注册号
		String regNO = getContent(jsonObjDtInfo.get("regNO"));
		InsertOrUpdateInfo.setRegNO(regNO);
		// 企业名称
		String entName = getContent(jsonObjDtInfo.get("entName"));
		InsertOrUpdateInfo.setEntName(entName);
		// 审批事项编码
		String exaCode = getContent(jsonObjDtInfo.get("exaCode"));
		InsertOrUpdateInfo.setExaCode(exaCode);
		// 审批事项名称
		String exaName = getContent(jsonObjDtInfo.get("exaName"));
		InsertOrUpdateInfo.setExaName(exaName);
		// 审批部门编码
		String checkDep = getContent(jsonObjDtInfo.get("checkDep"));
		InsertOrUpdateInfo.setCheckDep(checkDep);
		// 审批部门名称
		String checkDepName = getContent(jsonObjDtInfo.get("checkDepName"));
		InsertOrUpdateInfo.setCheckDepName(checkDepName);
		// 推送时间
		Long checkPushDate = (Long) jsonObjDtInfo.get("checkPushDate");
		InsertOrUpdateInfo.setCheckPushDate(changeJsonFastTime(checkPushDate));
		// 认领人账号
		String claimCode = getContent(jsonObjDtInfo.get("claimCode"));
		InsertOrUpdateInfo.setClaimCode(claimCode);
		// 认领人姓名
		String claimName = getContent(jsonObjDtInfo.get("claimName"));
		InsertOrUpdateInfo.setClaimName(claimName);
		// 认领时间
		Long claimDate = (Long)jsonObjDtInfo.get("claimDate");
		InsertOrUpdateInfo.setClaimDate(changeJsonFastTime(claimDate));
		// 职能部门
		String dutyDeptCode = getContent(jsonObjDtInfo.get("dutyDeptCode"));
		InsertOrUpdateInfo.setDutyDeptCode(dutyDeptCode);
		// 职能部门
		String dutyDeptName = getContent(jsonObjDtInfo.get("dutyDeptName"));
		InsertOrUpdateInfo.setDutyDeptName(dutyDeptName);
		// 登记事项
		String checkRegType = getContent(jsonObjDtInfo.get("checkRegType"));
		InsertOrUpdateInfo.setCheckRegType(checkRegType);
		// 生成时间
		InsertOrUpdateInfo.setCreateTime(new Date());
		return InsertOrUpdateInfo;
	}


	/**
     * 日期格式转换
     * @param timeStr
     * @return date
     */
    private Date checkWorkTime(String timeStr) {
        if (StringUtil.isBlank(timeStr)) {
            return null;
        }
        try{
        	return DateUtil.stringToDate(timeStr, "yyyy-MM-dd");
        }catch(Exception e){
        	return null;
        }
    }
    
    /**
     * json日期格式转换
     * @param timeStr
     * @return date
     */
    private Date changeJsonFastTime(Long timeStr) {
    	try{
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		return DateUtil.stringToDate(sdf.format(timeStr), "yyyy-MM-dd");
    	}catch(Exception e){
    		return null;
    	}
    }
    
	/**
	 * 描述   获取json对象内容
	 * @author yujingwei
	 * @return String
	 */
	private String getContent(Object object) throws Exception{
		if(object == null){
			return null;
		}
		return object.toString();
	}
	
	/**
     * 返回信息
     * @param resultInfo，jsonObj，msg
     * @return void
     */
	private void returnMsgInfo(String resultInfo, String msg,String errorCode,JSONObject jsonObj) throws Exception {
		jsonObj.put("result", resultInfo);
//		jsonObj.put("msg", URLEncoder.encode(msg, "GBK"));
		jsonObj.put("msg", msg);
		jsonObj.put("returnCode", errorCode);
	}
}