<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>知识产权出质信息打印</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/print.css"/>"/>
<style media="print" type="text/css">
    .STYLE1 {
        font-size: 16px;
        font-weight: bold;
    }
    object { display: none}
    .Noprint{ display:none; }
    .PageNext{ page-break-after: always; clear: both; width: 100%}
</style>

</head>
<body>
<div class="button Noprint" style="width:600px;">
    <a href="javascript:Print();"><strong style="font-size:16px">打印</strong></a>
    <a href="javascript:PrintView();"><strong style="font-size:16px">预览</strong></a>
    <a href="javascript:PrintSetup();"><strong style="font-size:16px">设置</strong></a>
    <a id="closeBtn" href="javascript:closeWindow();"><strong style="font-size:16px">关闭</strong></a>
</div>

<object id="factory" viewastext="" classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814" codebase="/js/common/print/ScriptX.cab#version=6,2,433,14"> </object>
<object id="WebBrowser" classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>


<div id="printarea">
<div class="print2">
    <table>
        <tr >
            <td  colspan="8"><h2>知识产权出质信息</h2></td>
        </tr>
        <tr >
             <th width="190"> 出质商标注册号asdf</th>
                    <th>名称</th>
                    <th>种类</th>
                    <th>出质人名称</th> 
                     <th width="90">质权人名称</th>
                    <th width="186">质权登记期限</th>
                    <th width="80">登记状态</th>
                    <th width="75">公示状态</th>
                  
        </tr>
        <c:forEach var="ippLdg" varStatus="status"  items="${ippldgList}">
            <c:if test="${status.index<12}">
            <tr >
                 <td >
                    ${ippLdg.TMRegNo}
                </td>
                <td >
                    ${ippLdg.TMName}
                </td>
                 <td>
                 <c:choose>
                 <c:when test="${ippLdg.kinds=='1'}">
                                              商标
                 </c:when>
                 <c:when test="${ippLdg.kinds=='2'}">
                                           版权                        
                 </c:when>
                 <c:when test="${ippLdg.kinds=='3'}">
                                           专利                       
                 </c:when>
                 <c:otherwise>
                 ${ippLdg.kinds}
                 </c:otherwise>
                 </c:choose>
                 </td>
                <td>${ippLdg.pledgor }</td>
                  <td>${ippLdg.impOrg }</td>
                <td >
                    <fmt:formatDate value='${ippLdg.pleRegPerFrom}'
                                    pattern='yyyy年MM月dd日'/>-
                                       <fmt:formatDate value='${ippLdg.pleRegPerTo}'
                                    pattern='yyyy年MM月dd日'/>
                </td>
                
                <td >
                <c:choose>
                <c:when test="${ippLdg.pubType=='1'}">
                                                变更
                </c:when>
                <c:when test="${ippLdg.pubType=='2'}">
                                                 注销                               
                </c:when>
                  
                 <c:when test="${ippLdg.pubType=='3'}">
                                              撤销                            
                </c:when> 
                <c:otherwise>
                                           有效
                </c:otherwise>
                
                </c:choose>
                
                  
                </td>
               
                <td >
             
                   <c:choose>
                   <c:when test="${ippLdg.pubFlag=='1'}">
                                                  已公示 
                   </c:when>
                      <c:when test="${ippLdg.pubFlag=='2'}">
                                                  未公示 
                   </c:when>
                   
                   </c:choose>
                   
                   
                </td>
                
                
            </tr>
            </c:if>
            <c:if test="${status.index>11&&(status.index+1)%12==1}">
    </table></div>
    <div class="print2">
        <table>
            <tr>
                   <td style="width:18%">出质商标注册号</td>
                <td style="width:18%">名称</td>
                <td style="width:%">种类</td>
                <td style="width:8%">出质人名称</td>
                <td style="width:30%">质权登记期限</td>
                   <td style="width:8%">登记状态</td>
                 <td style="width:18%">公示状态</td>
            </tr>
            <tr>
                <td >
                    ${ippLdg.TMRegNo}
                </td>
                <td >
                    ${ippLdg.TMName}
                </td>
                 <td>
                 <c:choose>
                 <c:when test="${ippLdg.kinds=='1'}">
                                              商标
                 </c:when>
                 <c:when test="${ippLdg.kinds=='2'}">
                                           版权                        
                 </c:when>
                 <c:when test="${ippLdg.kinds=='3'}">
                                           专利                       
                 </c:when>
                 <c:otherwise>
                 ${ippLdg.kinds}
                 </c:otherwise>
                 </c:choose>
                 </td>
                <td>${ippLdg.pledgor }</td>
                <td >
                    <fmt:formatDate value='${ippLdg.pleRegPerFrom}'
                                    pattern='yyyy年MM月dd日'/>-
                                       <fmt:formatDate value='${ippLdg.pleRegPerTo}'
                                    pattern='yyyy年MM月dd日'/>
                </td>
                
                <td >
                <c:choose>
                <c:when test="${ippLdg.pubType=='1'}">
                                                变更
                </c:when>
                <c:when test="${ippLdg.pubType=='2'}">
                                                 注销                               
                </c:when>
                 
                 <c:when test="${ippLdg.pubType=='3'}">
                                              撤销                            
                </c:when> 
                <c:otherwise>
                                           有效
                </c:otherwise>
                
                </c:choose>
                
                  
                </td>
               
                <td >
             
                   <c:choose>
                   <c:when test="${ippLdg.pubFlag=='1'}">
                                                  已公示 
                   </c:when>
                      <c:when test="${ippLdg.pubFlag=='2'}">
                                                  未公示 
                   </c:when>
                   
                   </c:choose>
                   
                   
                </td>
            </tr>
        </c:if>
        <c:if test="${status.index>11&&(status.index+1)%12!=1}">
            <tr >
                <td >
                    ${ippLdg.TMRegNo}
                </td>
                <td >
                    ${ippLdg.TMName}
                </td>
                 <td> 
                 <c:choose>
                 <c:when test="${ippLdg.kinds=='1'}">
                                              商标
                 </c:when>
                 <c:when test="${ippLdg.kinds=='2'}">
                                           版权                        
                 </c:when>
                 <c:when test="${ippLdg.kinds=='3'}">
                                           专利                       
                 </c:when>
                 <c:otherwise>
                 ${ippLdg.kinds}
                 </c:otherwise>
                 </c:choose>
                 </td>
                <td>${ippLdg.pledgor }</td>
                <td >
                    <fmt:formatDate value='${ippLdg.pleRegPerFrom}'
                                    pattern='yyyy年MM月dd日'/>-
                                       <fmt:formatDate value='${ippLdg.pleRegPerTo}'
                                    pattern='yyyy年MM月dd日'/>
                </td>
                
                <td >
                <c:choose>
                <c:when test="${ippLdg.pubType=='1'}">
                                                变更
                </c:when>
                <c:when test="${ippLdg.pubType=='2'}">
                                                 注销                               
                </c:when>
                 
                 <c:when test="${ippLdg.pubType=='3'}">
                                              撤销                            
                </c:when> 
                <c:otherwise>
                                           有效
                </c:otherwise>
                
                </c:choose>
                
                  
                </td>
               
                <td >
             
                   <c:choose>
                   <c:when test="${ippLdg.pubFlag=='1'}">
                                                  已公示 
                   </c:when>
                      <c:when test="${ippLdg.pubFlag=='2'}">
                                                  未公示 
                   </c:when>
                   
                   </c:choose>
                   
                   
                </td>
            </tr>
        </c:if>
        </c:forEach>
    </table>
</div>
</div>

<script src="/js/lib/require.js"></script>
<script src="/js/config.js"></script>
<script src="/js/common/print/printsetup.js"></script>
<script src="/js/common/print/print.js"></script>
 
</body>
</html>