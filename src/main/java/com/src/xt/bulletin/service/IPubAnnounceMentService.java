/*
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. 
 */
package com.icinfo.cs.bulletin.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.icinfo.cs.bulletin.model.PubAnnounceMent;
import com.icinfo.framework.core.service.BaseService;
import com.icinfo.framework.mybatis.pagehelper.datatables.PageRequest;

/**
 * 描述:    cs_pub_announcement 对应的Service接口.<br>
 *
 * @author framework generator
 * @date 2016年10月26日
 */
public interface IPubAnnounceMentService extends BaseService {
    
	/**
     * 获取信息公示公告列表数据
     * @author yujingwei
     * @date 2016-10-17
     * @param request
     * @return PageResponse
     * @throws Exception
     */
	public List<PubAnnounceMent> queryBulletinInfoList(PageRequest request) throws Exception;
	
	/**
     * 公告信息数据插入
     * @author yujingwei
     * @date 2016-10-17
     * @param request
     * @return PageResponse
     * @throws Exception
     */
	public void doCreatBulletinAllInfo() throws Exception;
    
	/**
     * 根据公告类型返回不同的视图
     * @author yujingwei
	 * @param view 
     * @date 2016-10-17
     * @param request
     * @return PageResponse
     * @throws Exception
     */
	public ModelAndView doGetViewByPubType(String uID, String pubType, ModelAndView view) throws Exception;
    
	/**
     * 通过UID获取公告信息
     * @author yujingwei
     * @date 2016-10-17
     * @param uID
     * @return PubAnnounceMent
     * @throws Exception
     */
	public PubAnnounceMent doGetpubAnnounceMentInfo(String uID,String pubType) throws Exception;
	
	/**
	 * 
	 * 描述: 新增
	 * @auther gaojinling
	 * @date 2017年6月8日 
	 * @param pubAnnounceMent
	 * @return
	 * @throws Exception
	 */
	public int insertOne(PubAnnounceMent pubAnnounceMent) throws Exception;
	
	/**
	 * 
	 * 描述: 修改(通过linkUid更新)
	 * @auther gaojinling
	 * @date 2017年6月8日 
	 * @param pubAnnounceMent
	 * @return
	 * @throws Exception
	 */
	public int UpdateOne(PubAnnounceMent pubAnnounceMent) throws Exception;
	
	/**
	 * 
	 * 描述: 删除
	 * @auther gaojinling
	 * @date 2017年6月8日 
	 * @param linkuid
	 * @return
	 * @throws SQLException
	 */
    public int deleteOne(String linkuid) throws Exception;
}