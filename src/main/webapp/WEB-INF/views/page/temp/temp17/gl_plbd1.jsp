<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta charset="utf-8">
    <title>批量导入</title>
    <link rel="stylesheet" href="/css/pub-syn.css">
</head>
<body class="outer-bg">
<div class="header mod">
    <img src="/img/pub-syn/top-img.png" alt="">
    <div class="header-tips">
        <div class="fl">
            <span><i class="icon icon1"></i>2016年12月20日</span>
            <span><i class="icon icon2"></i>您好！北京市环保局 李军</span>
        </div>
        <div class="fr">
            <a href=""><i class="icon icon3"></i>修改密码</a>
            <a href=""><i class="icon icon4"></i>用户退出</a>
        </div>
    </div>
</div>
<div class="mod clearfix">
    <div class="sidebar">
        <ul class="navbar">
            <li><a href="javascript:void(0)" class="info">信息提供</a>
                <ul class="subnavbar">
                    <li><a href="javascript:void(0)" class="selected">在线录入</a></li>
                    <li><a href="javascript:void(0)">批量导入</a></li>
                    <li><a href="javascript:void(0)">数据接口</a></li>
                    <li><a href="javascript:void(0)">数据交换</a></li>
                </ul>
            </li>
            <li><a href="javascript:void(0)" class="info">信息接收</a>
                <ul class="subnavbar">
                    <li><a href="javascript:void(0)">双告知</a></li>
                    <li><a href="javascript:void(0)">严重违法失信<br/>企业名单</a></li>
                    <li><a href="javascript:void(0)">经营异常名录</a></li>
                    <li><a href="javascript:void(0)">联合惩戒信息</a></li>
                </ul>
            </li>
            <li><a href="javascript:void(0)" class="info">信息管理</a>
                <ul class="subnavbar">
                    <li><a href="javascript:void(0)">本部门问题数据处理</a></li>
                    <li><a href="javascript:void(0)">本部门归集或接收<br/>信息的查询统计</a></li>
                    <li><a href="javascript:void(0)">数据应用反馈</a></li>
                    <li><a href="javascript:void(0)" class="selected">批量比对</a></li>
                </ul>
            </li>
            <li><a href="javascript:void(0)" class="info">信息查询</a>
                <ul class="subnavbar">
                    <li><a href="javascript:void(0)">企业高级查询</a></li>
                    <li><a href="javascript:void(0)">查询接口</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <div class="main">
        <div class="crumbs">
            <a href="javascript:void(0)">首页</a> &gt;
            <a href="javascript:void(0)" class="cur">起始页</a>
        </div>
        <hr/>

        <div class="dr-box gl-box">
            <div class="dr-tab-hd js-tab-hd">
                <ul class="clearfix dr-step01">
                    <li style="width: 260px">
                    </li>
                    <li style="width: 285px" class="gl-step02">
                    </li>
                </ul>
            </div>
            <div class="dr-tab-cont js-tab-cont">
                <div class="item select-step02">
                    <div class="clearfix">
                        <div class="fl">
                            <img src="/img/pub-syn/load-xl-demo.jpg" alt="">
                        </div>
                        <div class="fr mb-right select-mb">
                            <h5><span class="ml-20">请下载批量对比模板,【企业名称】,【注册号】,【统一社会信用代码】不能同时为空</span></h5>
                            <div class="active">
                                <img src="/img/pub-syn/load-xl-step02.jpg" alt="">
                        <span>
                            行政处罚
                        </span>
                                <i class="icon-check"></i>
                            </div>
                            <p class="sel-btn center mt39">
                                <input type="button" value="下载模板">
                            </p>
                        </div>
                    </div>
                </div>
                <div class="item select-step02" style="display: none">
                    <div class="clearfix">
                        <div class="fl">
                            <img src="/img/pub-syn/load-xl-demo.jpg" alt="">
                        </div>
                        <div class="fr mb-right select-mb">
                            <h5><span class="ml-20">请下载批量对比模板,【企业名称】,【注册号】,【统一社会信用代码】不能同时为空</span></h5>
                            <div class="active">
                                <img src="/img/pub-syn/load-xl-step02.jpg" alt="">
                        <span>
                            行政处罚
                        </span>
                                <i class="icon-check"></i>
                            </div>
                            <p class="sel-btn center mt39">
                                <input type="button" value="上传数据">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
<div class="mod">
    <div class="footer-box">
        <div class="footer">
            <p>版权所有：浙江省工商行政管理局</p>
            <p>年报公示业务咨询电话：010-82691101（年报）&nbsp;&nbsp;&nbsp;&nbsp;技术支持电话：010-82691768（公示）&nbsp;&nbsp;&nbsp;&nbsp;京备xxxxxxxxICP</p>
        </div>
    </div>
</div>
<script src="/js/lib/jquery/jquery-1.12.3.min.js"></script>
<script>
    $(function () {
        $('.gl-step02').click(function () {
            $('.js-tab-cont .item').eq($(this).index()).show().siblings().hide();
            $(this).parent('ul').attr('class', 'dr-step0' + ($(this).index() + 1));
        })
    })
</script>
</body>
</html>