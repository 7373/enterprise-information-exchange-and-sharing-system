/**
 * Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved. <br/>
 */
package com.icinfo.cs.yr.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述:    cs_sfc_branchinfo_his 对应的实体类.<br>
 * WARNING：不是表中字段的属性必须加@Transient注解
 * @author framework generator
 * @date 2016年10月08日
 */
@Table(name = "cs_sfc_branchinfo_his")
public class SfcBranchInfoHis implements Serializable {
    /**
     * 自增主键ID
     */
    @Id
    @Column(name = "id")
    private Integer id;

    /**
     * 名称
     */
    @Column(name = "BrName")
    private String brName;

    /**
     * 统一社会信用代码/注册号
     */
    @Column(name = "UniCode")
    private String uniCode;

    /**
     * 时间戳
     */
    @Column(name = "CreateTime")
    private Date createTime;

    /**
     * 年报ID
     */
    @Column(name = "AnCheID")
    private String anCheID;

    /**
     * 分支机构标识
     */
    @Column(name = "BRID")
    private String BRID;
    
    /**
     * 年报提交编号
     */
    @Column(name = "ReportNo")
    private String reportNo;
    

    public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	private static final long serialVersionUID = 1L;

    /**
     * 获取自增主键ID
     *
     * @return id - 自增主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键ID
     *
     * @param id 自增主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return BrName - 名称
     */
    public String getBrName() {
        return brName;
    }

    /**
     * 设置名称
     *
     * @param brName 名称
     */
    public void setBrName(String brName) {
        this.brName = brName;
    }

    /**
     * 获取统一社会信用代码/注册号
     *
     * @return UniCode - 统一社会信用代码/注册号
     */
    public String getUniCode() {
        return uniCode;
    }

    /**
     * 设置统一社会信用代码/注册号
     *
     * @param uniCode 统一社会信用代码/注册号
     */
    public void setUniCode(String uniCode) {
        this.uniCode = uniCode;
    }

    /**
     * 获取时间戳
     *
     * @return CreateTime - 时间戳
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置时间戳
     *
     * @param createTime 时间戳
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取年报ID
     *
     * @return AnCheID - 年报ID
     */
    public String getAnCheID() {
        return anCheID;
    }

    /**
     * 设置年报ID
     *
     * @param anCheID 年报ID
     */
    public void setAnCheID(String anCheID) {
        this.anCheID = anCheID;
    }

    /**
     * 获取分支机构标识
     *
     * @return BRID - 分支机构标识
     */
    public String getBRID() {
        return BRID;
    }

    /**
     * 设置分支机构标识
     *
     * @param BRID 分支机构标识
     */
    public void setBRID(String BRID) {
        this.BRID = BRID;
    }
}