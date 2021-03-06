<%--
   Copyright© 2003-2016 浙江汇信科技有限公司, All Rights Reserved.
  --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="entTypeImpExtend" value="1122,1130,1151,1152,1212,1222,2122,2130,2151,2152,2212,2222,4500,4510,4550,4530,4570,4560,4580"/>
<c:set var="entType" value="${entType}"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
    <title>年报经营情况页面</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/reg.client.css"/>"/>
</head>
<body>



    <div class="mod caiwu">
        <div class="mod-hd">
            <div class="title">
                <h3>企业生产经营情况 <strong class="light" style="font-size:10px;">（币种：人民币）</strong></h3>
            </div>
        </div>
        <div class="mod-bd">
            <div class="content">
                <table class="table-public table-web" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">你企业属于</div>
                        </td>
                        <td colspan="3" class="column-l">
                            <div class="radio-box">
                                <label><input type="radio" name="entBelong" <c:if test="${yrAsset.entBelong=='1'}">checked</c:if>  value="1" disabled >独立核算</label>
                                <label><input type="radio" name="entBelong" <c:if test="${yrAsset.entBelong=='2'}">checked</c:if>  value="2" disabled >非独立核算</label>
                            </div>
                            <strong class="light">（非独资核算的分支机构无需填写“主营业务收入、净利润、纳税总额”信息。）</strong>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="bg column-r">
                            <div class="item-name">主营业务收入</div>
                        </td>
                        <td class="column-l">
                            <div class="ipt-box">
                                <input type="text" id="maiBusInc" name="maiBusInc" value="${yrAsset.maiBusInc}"   class="ipt-txt  units " readonly = "readonly" >
                                元
                            </div>
                            <div class="radio-box">
                                <label><input type="radio" name="maiBusIncIsPub"  <c:if test="${yrAsset.maiBusIncIsPub=='1'}">checked</c:if>  value="1" disabled>公示</label>
                                <label><input type="radio" name="maiBusIncIsPub"  <c:if test="${yrAsset.maiBusIncIsPub=='0'}">checked</c:if>  value="0" disabled>不公示</label>
                            </div>
                        </td>
                        <td class="bg column-r">
                            <div class="item-name">净利润</div>
                        </td>
                        <td class="column-l">
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="netInc" name="netInc" value="${yrAsset.netInc}"  class="ipt-txt  units" readonly >
                                元
                            </div>
                            <div class="radio-box">
                                <label><input type="radio" name="netIncIsPub"  <c:if test="${yrAsset.netIncIsPub=='1'}">checked</c:if> value="1" disabled>公示</label>
                                <label><input type="radio" name="netIncIsPub"  <c:if test="${yrAsset.netIncIsPub=='0'}">checked</c:if> value="0" disabled>不公示</label>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="bg column-r">
                            <div class="item-name">你企业当前的经营状态</div>
                        </td>
                        <td colspan="3" class="column-l">
                            <div class="ipt-box js-ipt-box">
                                <div class="radio-box">
                                    <label><input type="radio" name="busSt"   value="1" data-text="正常开业"   <c:if test="${yrAsset.busSt=='1'}">checked</c:if> disabled>正常开业</label>
                                    <label><input type="radio" name="busSt"   value="2" data-text="筹建"      <c:if test="${yrAsset.busSt=='2'}">checked</c:if> disabled>筹建</label>
                                    <label><input type="radio" name="busSt"   value="4" data-text="停业、歇业" <c:if test="${yrAsset.busSt=='4'}">checked</c:if> disabled>停业、歇业</label>
                                    <label><input type="radio" name="busSt"   value="5" data-text="正在清算中" <c:if test="${yrAsset.busSt=='5'}">checked</c:if> disabled>正在清算中</label>
                                    <input type="hidden" id="busStatusCN" name="busStatusCN" value="${yrAsset.busStatusCN}">
                                </div>
                            </div>
                        </td>
                    </tr>
                    <c:if test="${!empty yrAsset.noBusRea}">
                    <tr class="focus-state" id="_noBusRea" >
                        <td class="bg column-r">
                            <div class="item-name">无经营活动收入或歇业的原因</div>
                        </td>
                        <td  class="column-l" colspan="3">
                            <div class="ipt-box js-ipt-box">
                                <div><textarea id="noBusRea" name="noBusRea" cols="100" rows="3" readonly >${yrAsset.noBusRea}</textarea></div>
                            </div>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${year>2015}">
                        <tr class="focus-state"  >
                            <td class="bg column-r">
                                <div class="item-name">企业主营业务活动</div>
                            </td>
                            <td  class="column-l" colspan="3">
                                <div class="ipt-box js-ipt-box">
                                    <div>
                                        <textarea id="entMainBusActivity" name="entMainBusActivity" cols="100" rows="3" >${yrAsset.entMainBusActivity}</textarea>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:if>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="mod subcapital">
        <div class="mod-hd">
            <div class="title">
                <h3>企业税费信息 <strong class="light" style="font-size:10px;">（币种：人民币）</strong></h3>
                <p>以下信息请按照你企业${year}年1月1日至12月31日期间全年应纳税金额填写。</p>
            </div>
        </div>
        <div class="mod-bd">
            <div class="content">
                <table class="table-public table-web table-fix" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr class="focus-state">
                        <td class="column-r" width="25%">
                            <div class="item-name">增值税</div>
                        </td>
                        <td width="25%">
                            <div class="ipt-box js-ipt-box">
                                <input type="text"   id="addTax" name="addTax" value="${yrAsset.addTax}" class="ipt-txt w93  units" readonly="readonly"  >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r" width="25%">
                            <div class="item-name">城市维护建设税</div>
                        </td>
                        <td width="25%">
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="cityTax" name="cityTax" value="${yrAsset.cityTax}" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">营业税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="mngTax" name="mngTax" value="${yrAsset.mngTax}"  class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r">
                            <div class="item-name">印花税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="floTax" name="floTax" value="${yrAsset.floTax}"  class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">消费税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text"  id="expenseTax" name="expenseTax" value="${yrAsset.expenseTax}" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r">
                            <div class="item-name">土地增值税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text"  id="landTax"  name="landTax" value="${yrAsset.landTax }" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">企业所得税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text"  id="entTax" name="entTax"  value="${yrAsset.entTax}" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r">
                            <div class="item-name">关税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text"  id="dutyTax" name="dutyTax"  value="${yrAsset.dutyTax}" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">个人所得税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="personTax"  name="personTax"  value="${yrAsset.personTax}" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r">
                            <div class="item-name">教育费附加</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="techTax"  name="techTax" value="${yrAsset.techTax}" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">房产税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="houseTax"  name="houseTax" value="${yrAsset.houseTax }" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r">
                            <div class="item-name">水利基金</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="waterTax"  name="waterTax"  value="${yrAsset.waterTax }" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">城镇土地使用税</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="lanUseTax"  name="lanUseTax" value="${yrAsset.lanUseTax}"  class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                        <td class="column-r">
                            <div class="item-name">其他</div>
                        </td>
                        <td>
                            <div class="ipt-box js-ipt-box">
                                <input type="text"   id="otherTax" name="otherTax" value="${yrAsset.otherTax }" class="ipt-txt w93  units" readonly="readonly" >
                                <strong class="light">  元</strong>
                            </div>
                        </td>
                    </tr>
                    <tr class="focus-state">
                        <td class="column-r bg">
                            <div class="item-name">纳税总额</div>
                        </td>
                        <td colspan="3" class="column-l">
                            <div class="ipt-box js-ipt-box ipt-box-wauto">
                                <input type="text" id="ratGro"  name="ratGro"  value="${yrAsset.ratGro}" class="ipt-txt w184 units " readonly="readonly">
                                <strong class="light">  元</strong>
                            </div>
                            <div class="radio-box">
                                <label><input type="radio" name="ratGroIsPub" <c:if test="${yrAsset.ratGroIsPub=='1'}">checked</c:if> value="1" disabled>公示</label>
                                <label><input type="radio" name="ratGroIsPub" <c:if test="${yrAsset.ratGroIsPub=='0'}">checked</c:if> value="0" disabled>不公示</label>
                            </div>
                            <strong class="light">（纳税总额由系统自动合计为以上各项纳税金额之和）</strong>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <div class="mod subcapital yonggong">
        <div class="mod-hd">
            <div class="title">
                <h3>企业用工情况 </h3>
            </div>
        </div>
        <div class="mod-bd">
            <div class="content">
                <table class="table-public table-web" border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                    <tr class="focus-state">
                        <td class="column-r bg" width="148">
                            <div class="item-name">企业从业人员数</div>
                        </td>
                        <td class="column-l" colspan="2">
                            <div class="ipt-box js-ipt-box">
                                <input type="text" id="empNum" name="empNum" value="${yrAsset.empNum }"   class="ipt-txt " readonly>
                            </div>
                            人
                            <div class="radio-box">
                                <label><input type="radio" name="empNumDis" <c:if test="${yrAsset.empNumDis=='1'}">checked</c:if> class="required" value="1" disabled>公示</label>
                                <label><input type="radio" name="empNumDis" <c:if test="${yrAsset.empNumDis=='0'}">checked</c:if> class="required" value="0" disabled>不公示</label>
                            </div>
                            <strong class="light">（“企业从业人员”指企业雇佣且签订劳动合同的所有人员）</strong>
                        </td>
                    </tr>

                    <c:if test="${year>2015}">
                        <tr class="focus-state">
                            <td class="column-r bg" width="148">
                                <div class="item-name">(其中女性从业人数)</div>
                            </td>
                            <td class="column-l" colspan="2">
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="femaleEmploye" name="femaleEmploye" value="${yrAsset.femaleEmploye}" class="ipt-txt required digits">
                                </div>
                                人
                                <div class="radio-box">
                                    <label><input type="radio" name="femaleEmployeIsPub" <c:if test="${yrAsset.femaleEmployeIsPub=='1'}">checked</c:if> class="required" value="1">公示</label>
                                    <label><input type="radio" name="femaleEmployeIsPub" <c:if test="${yrAsset.femaleEmployeIsPub=='0'}">checked</c:if> class="required" value="0">不公示</label>
                                </div>
                            </td>
                        </tr>
                    </c:if>


                <c:if test="${fn:indexOf(entTypeImpExtend,entType) != -1}">
                    <tr class="focus-state">
                        <td class="column-r">
                            <div class="item-name">企业法定代表人是否属于</div>
                        </td>
                        <td class="column-l" colspan="2">
                            <div class="radio-box">
                                <label><input type="radio" name="isLegRep" class="required" <c:if test="${yrAsset.isLegRep=='1'}">checked</c:if>  value="1" disabled>高校毕业生</label>
                                <label><input type="radio" name="isLegRep" class="required" <c:if test="${yrAsset.isLegRep=='2'}">checked</c:if> value="2"  disabled>退役士兵</label>
                                <label><input type="radio" name="isLegRep" class="required" <c:if test="${yrAsset.isLegRep=='3'}">checked</c:if> value="3"  disabled>残疾人</label>
                                <label><input type="radio" name="isLegRep" class="required" <c:if test="${yrAsset.isLegRep=='4'}">checked</c:if> value="4"  disabled>失业人员再就业</label>
                                <label><input type="radio" name="isLegRep" class="required" <c:if test="${yrAsset.isLegRep=='5'}">checked</c:if> value="5"  disabled>都不是</label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="column-r">
                            <div class="item-name">从业人员中属于</div>
                        </td>
                        <td class="column-l">
                            <div class="item-type focus-state">
                                <span class="people">高校毕业生</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text"  id="uniGradOpers" name="uniGradOpers" value="${yrAsset.uniGradOpers}" class="ipt-txt small " readonly>
                                   
                                </div>
                                人
                            </div>
                            <div class="item-type focus-state">
                                <span class="people">退役士兵</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="exSoldOpers" name="exSoldOpers" value="${yrAsset.exSoldOpers}" class="ipt-txt small " readonly>
                                    
                                </div>
                                人
                            </div>
                            <div class="item-type focus-state">
                                <span class="people">残疾人</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="disOpers" name="disOpers" value="${yrAsset.disOpers}" class="ipt-txt small " readonly>
                                </div>
                                人
                            </div>
                            <div class="item-type focus-state">
                                <span class="people">失业人员再就业</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="unemOpers" name="unemOpers" value="${yrAsset.unemOpers}" class="ipt-txt small " readonly>
                                </div>
                                人
                            </div>
                        </td>
                        <td rowspan="2" width="200" class="column-l">
                        </td>
                    </tr>
                    <tr>
                        <td class="column-r">
                            <div class="item-name">从业人员中${year }年新雇佣</div>
                        </td>
                        <td class="column-l">
                            <div class="item-type focus-state">
                                <span class="people">高校毕业生</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text"  id="uniGradEmps" name="uniGradEmps" value="${yrAsset.uniGradEmps}" class="ipt-txt small " readonly>
                                </div>
                                人
                            </div>
                            <div class="item-type focus-state">
                                <span class="people">退役士兵</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="exSoldEmps" name="exSoldEmps" value="${yrAsset.exSoldEmps}" class="ipt-txt small " readonly>
                                </div>
                                人
                            </div>
                            <div class="item-type focus-state">
                                <span class="people">残疾人</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="disEmps" name="disEmps" value="${yrAsset.disEmps}" class="ipt-txt small " readonly>
                                </div>
                                人
                            </div>
                            <div class="item-type focus-state">
                                <span class="people">失业人员再就业</span>
                                <div class="ipt-box js-ipt-box">
                                    <input type="text" id="unemEmps" name="unemEmps" value="${yrAsset.unemEmps}" class="ipt-txt small " readonly>
                                </div>
                                人
                            </div>
                        </td>
                    </tr>
                </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>



<script src="<c:url value="/js/lib/require.js"/>"></script>
<script src="<c:url value="/js/config.js"/>"></script>
<script src="<c:url value="/js/reg/server/yr/entreportsearch/yrreport/assetBranch_view.js"/>"></script>


</body>
</html>
