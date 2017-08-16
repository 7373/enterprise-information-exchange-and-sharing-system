require(['common/util', 'layer1', 'common/http', 'jquery.validate', 'jquery','jquery.serialize'], function (util, layer, http) {


    document.onkeydown = function (event) {
        var e = event || window.event;
        if (e.keyCode == 13) {
            $("#login").click();
        }
    };

    //执行初始化函数
    init();

    /**
     * 初始化集合
     */
    function init() {
        formValid();
        bind();
        debugger;
        layer.open({
            type: 1,
            content: $('#index-alert'),
            title: false,
            closeBtn:false,
            area: ['720px','390px']
        });
    }

    /**
     * 登录表单验证
     */
    function formValid() {
        $('#login-form').validate({
            showErrors: function (errorMap, errorList) {
                for (var i in errorList) {
                    $(errorList[i].element).addClass('error');
                    $('#errorContainer').html(errorList[i].message).addClass('errorContainer-bg');
                    break;
                }
            },
            onkeyup: function () {
                if ($(this.lastActive).valid()) {
                    $('#errorContainer').html('').removeClass('errorContainer-bg');
                    $(this.lastActive).removeClass('error');
                }
            },
            onfocusout: false,
            rules: {
                username: {
                    required: true,
                    rangelength: [2, 20]
                },
                password: {
                    required: true,
                    rangelength: [2, 20]
                },
                checkCode: {
                    required: true
                }
            },

            messages: {
                username: {
                    required: '登录账号不能为空',
                    rangelength: '登录账号长度必须是{0}到{1}之间'
                },
                password: {
                    required: '登录密码不能为空',
                    rangelength: '登录密码长度必须是{0}到{1}之间'
                },
                checkCode: {
                    required: '验证码不能为空'
                }
            },
            submitHandler: function (form) {

            }
        });
    }

    /**
     * 事件绑定
     */
    function bind() {
        util.bindEvents([
            {
                el: '.js-checkcode',
                event: 'click',
                handler: function () {
                    $('.js-check-img').attr('src', '/cscaptcha?t=' + Math.random());
                }
            }
            ,
            {
                el: '#login',
                event: 'click',
                handler: function () {
                    if($("#login-form").valid()){
                        var formParam = $("#login-form").serializeObject();
                        http.httpRequest({
                            url: 'reg/server/login',
                            type: 'post',
                            data: formParam,
                            success: function (data) {
                                if (data.status == 'success') {
                                    location.assign('/reg');
                                    return;
                                }
                                $('.js-checkcode').trigger('click');
                                data.msg && layer.msg(data.msg);
                            }
                        });
                    }

                }
            },{
                el: '.js-close-item',
                event: 'click',
                handler: function () {
                    layer.closeAll();
                }
            }
        ])
    }

})