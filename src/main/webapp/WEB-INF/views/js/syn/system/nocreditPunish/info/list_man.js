require(['component/iframeLayer', 'component/dataTable', 'common/util', 'common/http', 'handlebars', 'jquery','jquery.serialize'], function (layer, dataTable, util, http, handlebars) {

	var urlHead=window._CONFIG.urlHead;
    var man_tb;
	var $searchForm=$("#searchForm");
    var params = {};//查询参数
    init();
    /**
     * 初始化函数集合
     */
    function init() {
        bind();
        createManList();
    }
    
    //我的惩戒单列表
    function createManList(){
    	man_tb = dataTable.load({
            //需要初始化dataTable的dom元素
            el: "#man_table",
            scrollX: true,
            ajax : {
                url : urlHead+'/noCreditPunish/info/searchlist.json?type=man',
                data : function(d) {
                	d.params = params;
                }
            },
            showIndex: true,
            columns: [
              	{data: '_idx', width:"80px", className: 'center'},
                {data: null, width:"80px", className: 'center'},
                {data: 'createTime', width:"120px"},
                {data: 'litiName', width:"130px"},
                {data: 'cardNo', width:"120px"},
                {data: 'punField', width:"140px"},
                {data: 'legBasis', width:"150px"},
                {data: 'punCause', width:"150px"},
                {data: 'punRule', width:"150px"},
                {data: 'punReqDept', width:"130px"},
                {data: 'exeBegin', width:"160px", className: 'center'},
                {data: 'punExeDept', width:"130px"},
                {data: 'punMea', width:"150px"},
                {data: 'contact', width:"110px"},
                {data: 'phone', width:"110px"},
                {data: 'fax', width:"110px"},
                {data: 'email', width:"130px"}
            ],
            columnDefs: [{
                    targets: 1,
                    render: function (data, type, row, meta) {
                    	var url=urlHead+"/noCreditPunish/info/edit?deal=3&batchNo="+row.batchNo;
                    	return '<a href="'+url+'">查看</a>';
                    }
                },{
                    targets: 10,
                    render: function (data, type, row, meta) {
                    	if(row.exeBegin != null && row.exeEnd !=null){
                    		return row.exeBegin+"至"+row.exeEnd;
                    	}else
                    		return "-"
                    }
                }
            ]
        })
    }
    
    //表格之外的查询按钮事件
    $("#search").click(function(){
    	params=$searchForm.serializeObject();
    	man_tb.ajax.reload();
    });
    $("#rest").click(function() {
    	$searchForm[0].reset();
    	$("#punReqDeptCode").val("");
    	$("#punExeDeptCode").val("");
	});
    
    // 部门单选
    function doSelectExeDeptCode() {
        layer.dialog({
            title: '选择惩戒执行部门',
            area: ['350px', '666px'],
            content: window._CONFIG.select_dept_url,
            callback: function(data) {
            	if(data.adCode!=null&&data.orgName!=null){
                    $("#punExeDept").val(data.orgName);
                    $("#punExeDeptCode").val(data.adCode);
                }
            }
        })
    }
    
    function doSelectReqDeptCode() {
        layer.dialog({
            title: '选择惩戒提请部门',
            area: ['350px', '666px'],
            content: window._CONFIG.select_dept_url,
            callback: function(data) {
            	if(data.adCode!=null&&data.orgName!=null){
                    $("#punReqDept").val(data.orgName);
                    $("#punReqDeptCode").val(data.adCode);
                }
            }
        })
    }
    
    $("#selectRule").click(function(){
    	layer.dialog({
            title: '选择事由..',
            area: ['600px', '350px'],
            content: urlHead+'/noCreditPunish/rule/selectrule',
            callback: function(data) {
            	if(data.punCause !=undefined){
                	$("#punCause").val(data.punCause);
            	}
            }
        })
    })
    $("#selectMea").click(function(){
    	layer.dialog({
            title: '选择措施..',
            area: ['600px', '350px'],
            content: urlHead+'/noCreditPunish/rule/selectrule',
            callback: function(data) {
            	if(data.punMea !=undefined){
                	$("#punMea").val(data.punMea);
            	}
            }
        })
    })

    function bind() {
        util.bindEvents([{
            el: '#selectPunReqDept',
            event: 'click',
            handler: function() {
            	doSelectReqDeptCode();
            }
        }, {
            el: '#selectPunExeDept',
            event: 'click',
            handler: function() {
            	doSelectExeDeptCode();
            }
        }])
    }

})

