/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.sccheck.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.icinfo.cs.sccheck.dto.PubScentResultDto;
import com.icinfo.cs.sccheck.dto.SccheckCount;
import com.icinfo.cs.sccheck.model.PubScentResult;
import com.icinfo.cs.system.dto.SysUserDto;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述: cs_pub_scent_result 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2017年05月17日
 */
public interface IPubScentResultService extends BaseService {

	/**
	 * 描述：抽查检查数据列表
	 * 
	 * @author baifangfang
	 * @date 2017年5月17日
	 * @param request
	 * @param sysUserDto
	 * @return
	 */
	List<PubScentResultDto> queryPageResult(PageRequest request, SysUserDto sysUserDto);
	
	/**
	 * 描述：抽查检查数据列表
	 * 
	 * @author chenxin
	 * @date 2017年6月28日
	 * @param request
	 * @return
	 */
	List<PubScentResultDto> queryPageSearchResult(PageRequest request);

	/**
	 * 描述：抽查查询结果统计
	 * 
	 * @author baifangfang
	 * @date 2017年5月17日
	 * @param request
	 * @param sysUserDto
	 * @return
	 */
	SccheckCount querySccheckCount(PageRequest request, SysUserDto sysUserDto);
	
	/**
	 * 描述：抽查查询结果统计
	 * 
	 * @author baifangfang
	 * @date 2017年5月17日
	 * @param request
	 * @param sysUserDto
	 * @return
	 */
	SccheckCount querySccheckSearchCount(PageRequest request);

	// /**
	// * 描述：根据任务序号和内部序号查询抽查检查结果信息
	// *
	// * @author baifangfang
	// * @date 2017年5月18日
	// * @param taskUid
	// * @param priPID
	// * @return
	// */
	// PubScentResultDto queryPubScentResultDtoByTaskUidAndPriPID(String
	// taskUid, String priPID);

	/**
	 * 描述：保存录入结果信息
	 * 
	 * @author baifangfang
	 * @date 2017年5月18日
	 * @param map
	 * @return
	 */
	int savePubScentResult(Map<String, Object> map,SysUserDto sysUser) throws Exception;

	/**
	 * 描述：抽查检查结果录入修改
	 * 
	 * @author baifangfang
	 * @date 2017年5月20日
	 * @param map
	 * @return
	 */
	int alterPubScentResult(Map<String, Object> map) throws Exception;

	/**
	 * 描述：抽查检查结果审核
	 * 
	 * @author baifangfang
	 * @date 2017年5月20日
	 * @param map
	 * @return
	 */
	int auditPubScentResult(Map<String, Object> map) throws Exception;

	/**
	 * 描述：插入结果表记录
	 * 
	 * @author chenxin
	 * @date 2017年5月20日
	 * @param pubScentResult
	 * @return
	 */
	public void savePubScentResult(PubScentResult pubScentResult);

	/**
	 * 描述：查看是否本次任务涉及的所有企业都已经录入结果并且公示
	 * 
	 * @author chenxin
	 * @date 2017年5月27日
	 * @param deptTaskUid
	 * @return
	 */
	public int seleteUnfinishedEnt(String deptTaskUid);

	/**
	 * 描述：公示系统抽查检查结果信息
	 * 
	 * @author baifangfang
	 * @date 2017年6月2日
	 * @param request
	 * @return
	 */
	List<PubScentResultDto> queryPageResultPub(PageRequest request);

	/**
	 * 描述：根据uid查询抽查检查结果信息
	 * 
	 * @author baifangfang
	 * @date 2017年6月2日
	 * @param uid
	 * @return
	 */
	PubScentResultDto queryPubScentResultDtoByUid(String uid);

	/**
	 * 描述：综合抽查实施准备列表JSON数据列表
	 * 
	 * @author baifangfang
	 * @date 2017年6月12日
	 * @param request
	 * @param sysUserDto
	 * @return
	 */
	List<PubScentResultDto> queryPreParePageResult(PageRequest request, SysUserDto sysUserDto);

	/**
	 * 描述：抽查检查结果补录
	 * @author baifangfang
	 * @date 2017年6月19日
	 * @param map
	 * @return
	 */
	int supplyPubScentResult(Map<String, Object> map) throws ParseException;

	/**
	 * 
	 * 描述: 综合抽查结果统计
	 * @auther chenxin
	 * @date 2017年6月26日 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<PubScentResultDto> selectScentResultCount(PageRequest request) throws Exception;

	/**
	 * 描述: 更新
	 * @auther chenxin
	 * @date 2017年6月26日 
	 * @param pubScentResult
	 */
	public void updatePubScentResultByUid(PubScentResult pubScentResult);
}