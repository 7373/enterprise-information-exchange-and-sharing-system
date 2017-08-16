require(['component/iframeLayer', 'component/dataTable', 'common/util', 'common/http', 'handlebars', 'jquery',
         'jquery.serialize','laydate','common/validateRules'],
         function (layer, dataTable, util, http,handlebars) {
	
	init();
    /**
     * 初始化函数集合
     */
    function init() {
    	formValidate();
        bind();
    }
    
    function formValidate(){
    	$('#hx-form').validate({
    		rules: {
    			markName:{required:true,maxlength:40,minlength:6},//重点监管标识名称
    			markStartDate:{required:true},//重点监管标识有效期
    			markEndDate:{required:true},//重点监管标识有效期
    			markContent:{required:true,maxlength:200},//重点监管标识内容
    			markAppointDept:{
    				required:{
    					depends:function(){
    						 return '1' == $('#markAppointWay').val();
    					}
    				}
    			},
    			setDeptContact:{required:true,maxlength:10},//设置部门联系人
    			tel:{required:true,maxlength:15,checkMobile:true},//联系电话
    			fax:{maxlength:30},//传真
    			email:{required:true,email:true,maxlength:30}//邮箱
    		},
    		onkeyup:false,
            showErrors:function(errorMap,errorList){
            	 for(var i in errorMap){
                     layer.tips(errorMap[i],$('#hx-form input[name='+i+'],textarea[name='+i+']'),{
                         tips:3,
                         tipsMore:true,
                         ltype:0
                     });
                 }
            },
            submitHandler: function () {
                var formParam = $('#warnForm').serializeObject();
                http.httpRequest({
                    url: '/syn/risk/cswarnmark/doSaveSupMark',
                    serializable: true,
                    data: $("#hx-form").serializeObject(),
                    type: 'post',
                    success: function (data) {
                        layer.msg(data.msg, {time: 1000}, function () {
                        	if(data.status=='success'){
                        		window.location.href='/syn/risk/cswarnmark/doEnSupMark';
                        	}
                        });
                    }
                });
            }
        });
    }
    var markAppointDeptCode='';
    var markAppointDept='';
    if($('#markAppointWay').val()=="1"){
    	markAppointDeptCode = $('#markAppointDeptCode').val();
    	markAppointDept = $('#markAppointDept').val();
    	$('.js-show').css("display","block");
    } 
    $('#markAppointWay').on('change',function(){
    	if($(this).val()=="1"){
    		$('#markAppointDept').val(markAppointDept);
    		$('#markAppointDeptCode').val(markAppointDeptCode);
    		$('.js-show').css('display','block');
    	}else{
    		$('#markAppointDept').val('');
    		$('#markAppointDeptCode').val('');
    		$('.js-show').css('display','none');
    	}
    });
    
    function bind() {
        util.bindEvents([{
            el: '#cancel',
            event: 'click',
            handler: function () {
            	window.location.href='/syn/risk/cswarnmark/doEnSupMark';
            }
        },{
            el: '#selectMarkSet',
            event: 'click',
            handler: function () {
            	doSelectWarnMarkSetDept();
            }
        },{
        	el:'#print',
        	event:'click',
        	handler:function(){
        		window.open('/syn/supervise/doEnMarkPrint',"_blank");
        	}
        }
        ]);
    }
    
    
    //选择部门
    function doSelectWarnMarkSetDept() {
        var supCode='';
        //var select_dept_url=window._CONFIG.select_dept_url;
        layer.dialog({
            title: '选择设置部门',
            area: ['350px', '666px'],
            content: '/syn/system/sysdepart/alldeptcheckboxtreeselect',
            callback: function (data) {
            	var orgCode = data.adCodesExcParent;
            	var deptName = data.orgNamesExcParent;
                if(orgCode!=null&&deptName!=null){
                	$("#markAppointDept").val(deptName);
                    $("#markAppointDeptCode").val(orgCode);
                }
            }
        })
    }
})
