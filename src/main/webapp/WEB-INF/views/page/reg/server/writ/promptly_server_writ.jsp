<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>电子回执操作</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/reg.client.css"/>"/>
</head>
<body >

<!-- 用于判断显示不同的决定书 -->
<c:if test="${typeMark=='4'}">
    <!-- 异常决定书-->
    <%@include file="../../commpage/writepage/book4.jsp"%>
</c:if>


<!--************************************  电子回执  start*****************************************************-->

<!--回执状态-->
<c:if test="${!empty dzhz}"><p style="font-size: 16px;color: green;text-align: center;">已回执</p></c:if>
<c:if test="${empty dzhz}"><p style="font-size: 16px;color: red;text-align: center;">未回执</p></c:if>


<!-- 回执表单查看 start -->
<c:if test="${!empty dzhz}">
<div class="mod mod-border investment-info mt10 " >
    <div>
        <div class="title" style="text-align:center;">
            <h3>${sessionScope.midBaseInfoDto.entName} 电子回执</h3>
        </div>
    </div>
    <div class="mod-bd">
        <div class="content liaison-layer">
            <div> XX局（工商局）：</div>
            <br>
            <div>我司已收阅并知悉以上文书内容！</div>
            <br>
            <div>${sessionScope.midBaseInfoDto.entName}</div>
            <br/>
            <p style="font-weight: bold;font-size: 16px;">>>>>>>>:${dzqz.qzStatus=='1'?'签章成功':'签章失败'}</p>
            <br/>
            <form class="form-horizontal error-bottom" >
                <table>
                    <tr>
                        <td>回执人</td>
                        <td>
                            <input type="text"  name="receivePeople" value="${dzhz.receivePeople}" class="ipt-txt" readonly>
                        </td>
                        <td>联系电话</td>
                        <td>
                            <input type="text"  name="tel" value="${dzhz.tel}" class="ipt-txt"  readonly>
                        </td>
                        <td>回执日期</td>
                        <td>
                            <input type="text" name="receiveTime" value="<fmt:formatDate value="${dzhz.receiveTime}"  pattern="yyyy-MM-dd" />" class="ipt-txt" readonly >
                        </td>
                    </tr>
                </table>
                <br/>
            </form>
        </div>
    </div>
</div>
</c:if>
<!-- 回执表单查看  end -->


<!-- 填写回执表单 未填写 start -->
<c:if test="${empty dzhz}">
<div class="mod mod-border investment-info mt10 " id="js-change-UI" >
    <div>
        <div class="title" style="text-align:center;">
            <h3>${sessionScope.midBaseInfoDto.entName} 电子回执</h3>
        </div>
    </div>
    <div class="mod-bd">
        <div class="content liaison-layer">
            <div> XX局（工商局）：</div>
            <br>
            <div>我司已收阅并知悉以上文书内容！</div>
            <br>
            <div>${sessionScope.midBaseInfoDto.entName}</div>
            <br/>
            <form class="form-horizontal error-bottom" id="dzhzFrom">
                <table>
                    <tr>
                        <td>回执人</td>
                        <td>
                            <input type="text" id="hzr" name="receivePeople" value="${lianname}" class="ipt-txt js-content" placeholder="读取联络员姓名,可以修改" >
                        </td>
                        <td>联系电话</td>
                        <td>
                            <input type="text" id="lxdh" name="tel" value="${tel}" class="ipt-txt js-content"  placeholder="读取联系人的手机号码,可修改">
                        </td>
                        <td>回执日期</td>
                        <td>
                            <input type="text" name="receiveTime" value="${sysDate}" class="ipt-txt js-content" placeholder="默认读取系统时间">
                        </td>
                    </tr>
                </table>
               <br/>
            </form>

        </div>
    </div>
</div>
</c:if>
<!-- 填写回执表单 未填写 end -->

<!--************************************  电子回执  end *****************************************************-->

</body>
</html>
