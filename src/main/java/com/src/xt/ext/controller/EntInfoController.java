/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.ext.controller;

import java.util.List;

import com.icinfo.cs.yr.model.*;
import com.icinfo.cs.yr.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.base.model.CodeDzLicense;
import com.icinfo.cs.base.service.ICodeDzLicenseService;
import com.icinfo.cs.common.constant.Constants;
import com.icinfo.cs.common.utils.AESEUtil;
import com.icinfo.cs.common.utils.RepTypeUtil;
import com.icinfo.cs.common.utils.StringUtil;
import com.icinfo.cs.es.dto.PubEntSearchDto;
import com.icinfo.cs.es.service.IEntSearchService;
import com.icinfo.cs.simpleesc.model.ErEscAppinfo;
import com.icinfo.cs.simpleesc.service.IErEscAppinfoService;
import com.icinfo.cs.yr.dto.BaseInfoDto;
import com.icinfo.cs.yr.dto.PbBaseinfoDto;
import com.icinfo.cs.yr.dto.SfcBaseInfoDto;
import com.icinfo.framework.core.web.BaseController;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageResponse;

/**
 * @author baifangfang
 */
@Controller
@RequestMapping("/entinfo/")
public class EntInfoController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(EntInfoController.class);

	@Autowired
	IEntSearchService entSearchService;

	@Autowired
	IBaseInfoService baseInfoService;

	@Autowired
	IPbBaseinfoService pbBaseinfoService;

	@Autowired
	ISfcBaseInfoService sfcBaseInfoService;

	@Autowired
	IYrRegCheckService yrRegCheckService;

	@Autowired
	IErEscAppinfoService erEscAppinfoService;

	// 电子公章
	@Autowired
	private ISignatureService signatureService;

	@Autowired
	ICodeDzLicenseService codeDzLicenseService;

	/**
	 * 描述：企业基本信息
	 * 
	 * @author baifangfang
	 * @date 2016年9月26日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("baseinfo")
	public ModelAndView yrbaseinfo(@RequestParam String encryPriPID, @RequestParam String classFlag) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/baseinfo");

		// 查es
		PubEntSearchDto entBasicInfoES = entSearchService.selectByDocId(priPID);
		// 查表
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setIsOpanomaly(entBasicInfoES.getIsOpanomaly());
		entBasicInfo.setIsSimpleCancle(entBasicInfoES.getIsSimpleCancle());
		if (!StringUtil.isEmpty(entBasicInfo.getIsSimpleCancle())) {
			// 查询该企业简易注销信息
			ErEscAppinfo erEscAppinfo = erEscAppinfoService.getErEscAppinfoByPriPID(entBasicInfo.getPriPID());
			mav.addObject("erEscAppinfo", erEscAppinfo);
		}
		entBasicInfo.setEncryPriPID(encryPriPID);
		String basx = entBasicInfo.getBasx();
		String contents = "";
		if (!StringUtil.isEmpty(basx)) {
			String[] basxs = basx.split(",");
			for (int i = 0; i < basxs.length; i++) {
				CodeDzLicense codeDzLicense = codeDzLicenseService.selectCodeLicenseByExaCodeAndLicType(basxs[i], "2");
				String content = codeDzLicense.getExaName();
				contents = content + "，" + contents;
			}
		}
		if (!StringUtil.isEmpty(contents)) {
			contents = contents.substring(0, contents.length() - 1);
		}
		entBasicInfo.setBasxCN(contents);
		logger.info("entBasicInfo {}", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("classflag", 1);// 1.基础信息2.行政许可信息3.行政处罚信息4.纳入经营异常5.纳入严重违法
		mav.addObject("classFlag", classFlag);// 1.企业信用信息2.经营异常名录3.严重违法失信
		// 即时信息报表配置
		mav.addObject("imReportType",
				RepTypeUtil.getImReportTypeList(entBasicInfo.getEntTypeCatg(), entBasicInfo.getEntType()).toString());
		return mav;
	}

	/**
	 * 描述：行政许可信息
	 * 
	 * @author
	 * @date 2016年10月25日
	 * @param encryPriPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("licenseinfo")
	public ModelAndView licenseinfo(@RequestParam String encryPriPID, @RequestParam String classFlag) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/licenseinfo");

		// 查es
		PubEntSearchDto entBasicInfoES = entSearchService.selectByDocId(priPID);
		// 查表
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setIsOpanomaly(entBasicInfoES.getIsOpanomaly());
		entBasicInfo.setIsSimpleCancle(entBasicInfoES.getIsSimpleCancle());
		if (!StringUtil.isEmpty(entBasicInfo.getIsSimpleCancle())) {
			// 查询该企业简易注销信息
			ErEscAppinfo erEscAppinfo = erEscAppinfoService.getErEscAppinfoByPriPID(entBasicInfo.getPriPID());
			mav.addObject("erEscAppinfo", erEscAppinfo);
		}
		entBasicInfo.setEncryPriPID(encryPriPID);
		logger.info("entBasicInfo {}", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("classflag", 2);// 1.基础信息2.行政许可信息3.行政处罚信息4.纳入经营异常5.纳入严重违法
		mav.addObject("classFlag", classFlag);// 1.企业信用信息2.经营异常名录3.严重违法失信
		return mav;
	}

	/**
	 * 描述：行政处罚信息
	 * 
	 * @author
	 * @date 2016年10月25日
	 * @param encryPriPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("punishinfo")
	public ModelAndView punishinfo(@RequestParam String encryPriPID, @RequestParam String classFlag) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/punishinfo");

		// 查es
		PubEntSearchDto entBasicInfoES = entSearchService.selectByDocId(priPID);
		// 查表
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setIsOpanomaly(entBasicInfoES.getIsOpanomaly());
		entBasicInfo.setIsSimpleCancle(entBasicInfoES.getIsSimpleCancle());
		if (!StringUtil.isEmpty(entBasicInfo.getIsSimpleCancle())) {
			// 查询该企业简易注销信息
			ErEscAppinfo erEscAppinfo = erEscAppinfoService.getErEscAppinfoByPriPID(entBasicInfo.getPriPID());
			mav.addObject("erEscAppinfo", erEscAppinfo);
		}
		entBasicInfo.setEncryPriPID(encryPriPID);
		logger.info("entBasicInfo {}", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("classflag", 3);// 1.基础信息2.行政许可信息3.行政处罚信息4.纳入经营异常5.纳入严重违法
		mav.addObject("classFlag", classFlag);// 1.企业信用信息2.经营异常名录3.严重违法失信
		return mav;
	}

	/**
	 * 描述：纳入经营异常名录信息
	 * 
	 * @author
	 * @date 2016年10月25日
	 * @param encryPriPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("anomalyinfo")
	public ModelAndView anomalyinfo(@RequestParam String encryPriPID, @RequestParam String classFlag) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/anomalyinfo");

		// 查es
		PubEntSearchDto entBasicInfoES = entSearchService.selectByDocId(priPID);
		// 查表
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setIsOpanomaly(entBasicInfoES.getIsOpanomaly());
		entBasicInfo.setIsSimpleCancle(entBasicInfoES.getIsSimpleCancle());
		if (!StringUtil.isEmpty(entBasicInfo.getIsSimpleCancle())) {
			// 查询该企业简易注销信息
			ErEscAppinfo erEscAppinfo = erEscAppinfoService.getErEscAppinfoByPriPID(entBasicInfo.getPriPID());
			mav.addObject("erEscAppinfo", erEscAppinfo);
		}
		entBasicInfo.setEncryPriPID(encryPriPID);
		logger.info("entBasicInfo {}", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("classflag", 4);// 1.基础信息2.行政许可信息3.行政处罚信息4.纳入经营异常5.纳入严重违法
		mav.addObject("classFlag", classFlag);// 1.企业信用信息2.经营异常名录3.严重违法失信
		return mav;
	}

	/**
	 * 描述：纳入严重违法失信企业名单（黑名单）信息
	 * 
	 * @author
	 * @date 2016年10月25日
	 * @param encryPriPID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("illegalinfo")
	public ModelAndView illegalinfo(@RequestParam String encryPriPID, @RequestParam String classFlag) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/illegalinfo");

		// 查es
		PubEntSearchDto entBasicInfoES = entSearchService.selectByDocId(priPID);
		// 查表
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setIsOpanomaly(entBasicInfoES.getIsOpanomaly());
		entBasicInfo.setIsSimpleCancle(entBasicInfoES.getIsSimpleCancle());
		if (!StringUtil.isEmpty(entBasicInfo.getIsSimpleCancle())) {
			// 查询该企业简易注销信息
			ErEscAppinfo erEscAppinfo = erEscAppinfoService.getErEscAppinfoByPriPID(entBasicInfo.getPriPID());
			mav.addObject("erEscAppinfo", erEscAppinfo);
		}
		entBasicInfo.setEncryPriPID(encryPriPID);
		logger.info("entBasicInfo {}", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("classflag", 5);// 1.基础信息2.行政许可信息3.行政处罚信息4.纳入经营异常5.纳入严重违法
		mav.addObject("classFlag", classFlag);// 1.企业信用信息2.经营异常名录3.严重违法失信
		return mav;
	}

	/**
	 * 描述：企业年报信息查看详情列表页
	 * 
	 * @author baifangfang
	 * @date 2016年10月25日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "list.json", "list.xml" })
	@ResponseBody
	public PageResponse<BaseInfoDto> yrinfoJSON(PageRequest request) throws Exception {
		List<BaseInfoDto> baseInfoDtos = baseInfoService.queryPageResult(request);
		logger.info("baseInfoDtos {}", baseInfoDtos);
		return new PageResponse<BaseInfoDto>(baseInfoDtos);
	}

	/**
	 * 描述：个体户年报信息查看详情列表页
	 * 
	 * @author baifangfang
	 * @date 2016年10月31日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "pblist.json", "pblist.xml" })
	@ResponseBody
	public PageResponse<PbBaseinfoDto> yrinfoPbJSON(PageRequest request) throws Exception {
		List<PbBaseinfoDto> pbBaseInfoDtos = pbBaseinfoService.queryPageResult(request);
		logger.info("pbBaseInfoDtos {}", pbBaseInfoDtos);
		return new PageResponse<PbBaseinfoDto>(pbBaseInfoDtos);
	}

	/**
	 * 描述：农专年报信息查看详情列表页
	 * 
	 * @author baifangfang
	 * @date 2016年10月31日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "sfclist.json", "sfclist.xml" })
	@ResponseBody
	public PageResponse<SfcBaseInfoDto> yrinfoSfcJSON(PageRequest request) throws Exception {
		List<SfcBaseInfoDto> sfcBaseInfoDtos = sfcBaseInfoService.queryPageResult(request);
		logger.info("sfcBaseInfoDtos {}", sfcBaseInfoDtos);
		return new PageResponse<SfcBaseInfoDto>(sfcBaseInfoDtos);
	}

	/**
	 * 描述：企业年报信息
	 * 
	 * @author baifangfang
	 * @date 2016年10月25日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("yrinfo")
	public ModelAndView yrcheckinfo(@RequestParam String encryPriPID, @RequestParam String year,
			@RequestParam String classFlag) throws Exception {
		logger.info("encryPriPID {},year {}", encryPriPID, year);
		logger.info("classFlag {}", classFlag);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setEncryPriPID(encryPriPID);

		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/yrinfo");
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("classFlag", classFlag);

		String entTypeCatgStr = entBasicInfo.getEntTypeCatg();// 企业大类

		// 50 个体户，16,17 农专，其他 企业
		if ("50".equals(entTypeCatgStr)) {
			PbBaseinfoDto pbBaseinfoDto = pbBaseinfoService.queryPbYrInfoByPriPIDAndYear(priPID, year);
			String ifFilter = pbBaseinfoService.queryAnduitStateInfoByAncheID(priPID, Integer.parseInt(year));
			if ("0".equals(ifFilter)) {
				pbBaseinfoDto.doFilterForbidword();
			}
			mav.addObject("baseInfo", pbBaseinfoDto);
		} else if ("16".equals(entTypeCatgStr) || "17".equals(entTypeCatgStr)) {
			SfcBaseInfoDto sfcBaseInfoDto = sfcBaseInfoService.querySfcYrInfoByPriPIDAndYear(priPID, year);
			String ifFilter = sfcBaseInfoService.queryAnduitStateInfoByAncheID(priPID, Integer.parseInt(year));
			if ("0".equals(ifFilter)) {
				sfcBaseInfoDto.doFilterForbidword();
			}
			mav.addObject("baseInfo", sfcBaseInfoDto);
		} else {
			BaseInfoDto baseInfoDto = baseInfoService.queryYrInfoByPriPIDAndYear(priPID, year);
			String ifFilter = baseInfoService.queryAnduitStateInfoByAncheID(priPID, Integer.parseInt(year));
			if ("0".equals(ifFilter)) {
				baseInfoDto.doFilterForbidword();
			}
			mav.addObject("baseInfo", baseInfoDto);
		}

		// 获取企业报表类型
		String repType = RepTypeUtil.getReportTypeList(entTypeCatgStr, entBasicInfo.getEntType(), year).toString();
		mav.addObject("repType", repType);
		return mav;
	}

	/**
	 * 描述：工商端企业综合查询详情企业公示信息
	 * 
	 * @author baifangfang
	 * @date 2016年11月11日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("yrpub")
	public ModelAndView yrpub(@RequestParam String priPID, @RequestParam String year, @RequestParam String entTypeCatg,
			@RequestParam String currencyCn) throws Exception {
		logger.info("priPID {},year {}", priPID, year);
		// 查询年报主表
		YrRegCheck yrRegCheck = yrRegCheckService.selectCheckInfoByPripidAndYear(priPID, Integer.parseInt(year));
		if (yrRegCheck == null) {
			yrRegCheck = new YrRegCheck();
		}
		yrRegCheck.setEntTypeCatg(entTypeCatg);
		ModelAndView mav = new ModelAndView("reg/server/search/entreportsearch/yrreport/yrpub_view");
		mav.addObject("yrRegCheck", yrRegCheck);

		// 50 个体户，16,17 农专，其他 企业
		if (entTypeCatg.equals(Constants.entTypeCatgPB)) {
			PbBaseinfo pbBaseInfo = pbBaseinfoService.selectPbBaseInfoByPriPIdAndYear(priPID, year);
			mav.addObject("pbBaseInfo", pbBaseInfo);
		} else if (Constants.entTypeCatgSFC.indexOf(entTypeCatg) != -1) {
			SfcBaseInfo sfcBaseInfo = sfcBaseInfoService.selectSfcBaseInfoByPriPIdAndYear(priPID,
					Integer.parseInt(year));
			mav.addObject("sfcBaseInfo", sfcBaseInfo);
		} else {
			BaseInfo baseInfo = baseInfoService.selectInfoByPripidAndYear(priPID, Integer.parseInt(year));
			mav.addObject("baseInfo", baseInfo);
		}

		// 获取电子公章图片字符串路径
		Signature signature = signatureService.selectByYearAndPripid(year, priPID);
		if (signature != null && StringUtils.isNotBlank(signature.getTextCode())) {
			mav.addObject("qzImgBase64", signature.getTextCode());
		}

		// 年份及priPID加密用于打印
		mav.addObject("encodeYear", AESEUtil.encodeYear(year));
		mav.addObject("encodePriPID", AESEUtil.encodeCorpid(priPID));
		mav.addObject("currencyCn", currencyCn);// 币种类型

		return mav;
	}

	/**
	 * 描述：企业即时信息
	 * 
	 * @author baifangfang
	 * @date 2016年9月26日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("iminfo")
	public ModelAndView iminfo(@RequestParam String encryPriPID) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		System.out.println(encryPriPID + "dones it ism ");
		ModelAndView mav = new ModelAndView("/pub/infopublic/infodetail/iminvestalter");

		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setEncryPriPID(encryPriPID);
		logger.info("entBasicInfo {}", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("priPID", encryPriPID);
		return mav;
	}

	/**
	 * 描述：其他信息
	 * 
	 * @author baifangfang
	 * @date 2016年9月26日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("otherinfo")
	public ModelAndView otherinfo(@RequestParam String encryPriPID) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/otherinfo");

		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setEncryPriPID(encryPriPID);
		mav.addObject("entBasicInfo", entBasicInfo);
		return mav;
	}

	/**
	 * 描述：地方特色信息
	 * 
	 * @author baifangfang
	 * @date 2016年9月26日
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("featureinfo")
	public ModelAndView featureinfo(@RequestParam String encryPriPID) throws Exception {
		logger.info("encryPriPID {}", encryPriPID);
		String priPID = AESEUtil.decodeCorpid(encryPriPID);
		ModelAndView mav = new ModelAndView("pub/infopublic/infodetail/featureinfo");

		PubEntSearchDto entBasicInfo = entSearchService.selectByPriPID(priPID);
		entBasicInfo.setEncryPriPID(encryPriPID);
		mav.addObject("entBasicInfo", entBasicInfo);
		mav.addObject("entBasicInfo", entBasicInfo);
		return mav;
	}
}