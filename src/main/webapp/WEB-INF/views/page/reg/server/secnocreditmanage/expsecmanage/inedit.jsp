<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>异常类严违申请列入</title>
    <link rel="stylesheet" href="/css/reg.server.css">
</head>
<body class="pd10">
<h5 class="com-info-title left pdl10">企业基本信息</h5>
<table cellpadding="0" cellspacing="0" border="0" class="table-horizontal mb10">
    <tbody>
    <tr>
        <td class="bg-gray right">企业名称</td>
        <td colspan="3" width="40%">${midBaseInfo.entName}</td>
        <td class="bg-gray right">统一代码（注册号）</td>
        <td>${midBaseInfo.regNO}</td>
    </tr>
    <tr>
        <td class="bg-gray right" width="10%">法定代表人（负责人）</td>
        <td width="28%">${midBaseInfo.leRep}</td>
        <td class="bg-gray right" width="8%">联系电话</td>
        <td width="18%">${midBaseInfo.tel}</td>
        <td class="bg-gray right" width="11%">登记机关</td>
        <td>${midBaseInfo.regOrgName}</td>
    </tr>
    <tr>
        <td class="bg-gray right">企业联络员</td>
        <td>${pubEppassword.lianame}</td>
        <td class="bg-gray right">联系电话</td>
        <td>${pubEppassword.tel}</td>
        <td class="bg-gray right">管辖单位</td>
        <td>${midBaseInfo.localAdmName}</td>
    </tr>
    <tr>
        <td class="bg-gray right">登记状态</td>
        <td>
            <c:if test="${midBaseInfo.regState=='K'||midBaseInfo.regState=='A'||midBaseInfo.regState=='B'||midBaseInfo.regState=='DA'||midBaseInfo.regState=='X'}">
                <span class="icon-rectangle icon-blue">存续</span>
            </c:if>
            <c:if test="${midBaseInfo.regState=='XX'||midBaseInfo.regState=='DX'}">
                <span class="icon-rectangle icon-gray">注销</span>
            </c:if>
            <c:if test="${midBaseInfo.regState=='C'}">
                <span class="icon-rectangle icon-gray">撤销</span>
            </c:if>
            <c:if test="${midBaseInfo.regState=='D'}">
                <span class="icon-rectangle icon-red">吊销</span>
            </c:if>
            <c:if test="${midBaseInfo.regState=='Q'}">
                <span class="icon-rectangle icon-darkblue">迁出</span>
            </c:if>
        </td>
        <td class="bg-gray right">注销日期</td>
        <td><c:if test="${midBaseInfo.regState=='XX'||midBaseInfo.regState=='DX'}"><fmt:formatDate value="${midBaseInfo.altDate}" pattern="yyy年MM月dd日"/></c:if></td>
        <td class="bg-gray right">片区/商圈</td>
        <td>${midBaseInfo.sliceNOName}</td>
    </tr>
    <tr>
        <td class="bg-gray right">
            住所（经营场所）
        </td>
        <td colspan="5">${midBaseInfo.dom}</td>
    </tr>
    </tbody>
</table>
<h5 class="com-info-title left pdl10">异常届满信息<a class="fr mr10" href="##">异常届满历史记录</a></h5>
<table cellpadding="0" cellspacing="0" border="0" class="table-row mb10">
    <thead>
    <th class="bg-gray" width="30%">列入异常原因</th>
    <th class="bg-gray" width="12%">列入日期</th>
    <th class="bg-gray" width="30%">列入异常机关</th>
    <th class="bg-gray" width="11%">列入异常文号</th>
    <th class="bg-gray">异常届满日期</th>
    <th class="bg-gray">届满提醒公告日期</th>
    </thead>
    <tbody>
    <tr>
        <td>${expSeriousCrimeList.speCauseCN}</td>
        <td><fmt:formatDate value="${expSeriousCrimeList.abnTime}" pattern="yyy-MM-dd"/></td>
        <td>${expSeriousCrimeList.decorgCN}</td>
        <td>${expSeriousCrimeList.penDecNo}</td>
        <td><fmt:formatDate value="${expSeriousCrimeList.expExpiredDate}" pattern="yyy-MM-dd"/></td>
        <td></td>
    </tr>
    </tbody>
</table>
<h5 class="com-info-title left pdl10">列入严违信息
    <a href="javascript:void(0);" class="fr mr10">收起<i class="tran-icons"></i></a>
    <a class="fr mr10" href="##">异常类列入严违历史记录</a></h5>
    
<form id="content_form">
<input id="secBasedStr" name="secBasedStr" type="hidden" />
<table cellpadding="0" cellspacing="0" border="0" class="table-horizontal mb10">
    <tbody>
    <tr>
        <td class="bg-gray right" width="10%">列入严违原因</td>
        <td width="66%">
            <div class="ipt-box">
                <input type="text" class="ipt-txt" name="addSecCause" value="${expSeriousCrimeList.addSecCause}" />
            </div>
        </td>
        <td class="bg-gray right" width="10%">异常届满日期</td>
        <td><fmt:formatDate value='${expSeriousCrimeList.expExpiredDate}' pattern='yyy-MM-dd'/></td>
    </tr>
    <tr>
        <td class="bg-gray right">
            列入严违说明
        </td>
        <td colspan="3">
            <div class="">
                <div class="ipt-box">
                    <textarea name="addSecExplain" rows="5" >
            			${expSeriousCrimeList.addSecExplain}
                    </textarea>
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td class="bg-gray right">
            列入严违依据
        </td>
        <td colspan="3">
            <div class="ipt-box col-3">
                <select name="addSecBasedNum" id="secbased">
                	<option value="1" ${expSeriousCrimeList.addSecBasedNum == 1 ?'selected':''}>1份</option>
                    <option value="2" ${expSeriousCrimeList.addSecBasedNum == 2?'selected':''}>2份</option>
                    <option value="3" ${expSeriousCrimeList.addSecBasedNum == 3?'selected':''}>3份</option>
                    <option value="4" ${expSeriousCrimeList.addSecBasedNum == 4?'selected':''}>4份</option>
                    <option value="5" ${expSeriousCrimeList.addSecBasedNum ==5 ?'selected':''}>5份</option>
                    <option value="6" ${expSeriousCrimeList.addSecBasedNum==6?'selected':''}>6份</option>
                    <option value="7" ${expSeriousCrimeList.addSecBasedNum==7?'selected':''}>7份</option>
                    <option value="8" ${expSeriousCrimeList.addSecBasedNum==8?'selected':''}>8份</option>
                    <option value="9" ${expSeriousCrimeList.addSecBasedNum==9?'selected':''}>9份</option>
                    <option value="10" ${expSeriousCrimeList.addSecBasedNum==10?'selected':''}>10份</option>
                </select>
            </div>
        </td>
    </tr>
    
    <c:forEach items="${basedList}" var="base" varStatus="status">
	    <tr class="tr${status.count} tr">
	        <td class="bg-gray right">依据${status.count}</td>
	        <td colspan="3">
	        	<div class="ipt-box yj-ipt clearfix">
		            <span class="light-dark-blue"><input type="text" class="ipt-txt" value="${base.based}"/></span>
<!-- 		            <span class="close-box"><i class="close-icon"></i></span> -->
	        </td>
	    </tr>
    </c:forEach>
    
    <c:forEach var="i" begin="${expSeriousCrimeList.addSecBasedNum}" end="9" varStatus="status">
	    <tr class="tr${status.index+1} tr" style="display: none;">
	        <td class="bg-gray right">依据${status.index+1}</td>
	        <td colspan="3" >
	        	<div class="ipt-box yj-ipt clearfix">
		            <span class="light-dark-blue"><input class="ipt-txt" type="text" /></span>
<!-- 		            <span class="close-box"><i class="close-icon"></i></span> -->
	            </div>
	        </td>
	    </tr>
    </c:forEach>
    
    </tbody>
</table>
<h5 class="com-info-title left pdl10">经办人意见信息&emsp;<a style="color: red">请于<fmt:formatDate value="${expSeriousCrimeList.addSecTerm}" pattern="yyy年MM月dd日"/>前列入严违失信名单</a></h5>
<input type="hidden" name="sourceId" value="${expSeriousCrimeList.sourceId}" />
<table cellpadding="0" cellspacing="0" border="0" class="table-horizontal">
    <tbody>
    <tr>
        <td class="bg-gray right" width="10%">经办人</td>
        <td width="20%"><div class="ipt-box"><input type="text" class="ipt-txt" name="applyMan" value="${expSeriousCrimeList.applyMan}" /></div></td>
        <td class="bg-gray right" width="9%">经办人意见</td>
            <td><div class="ipt-box"><input type="text" class="ipt-txt" name="applyCheck" value="拟将该企业列入严重违法失信企业名单，请处领导审批。" /></div></td>
        <td class="bg-gray right" width="9%">申请日期</td>
        <td><fmt:formatDate value="${expSeriousCrimeList.applyDate}" pattern="yyy-MM-dd"/></td>
  
</form>    </tbody>
</table>
</form>
<div class="center mt20">
    <input id="save" type="button" class="btn mr10" value="提交">
    <input id="cancel" type="button" class="btn" value="取消">
</div>

<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/reg/server/secnocreditmanage/expsecmanage/inedit.js"></script>
</body>
</html>