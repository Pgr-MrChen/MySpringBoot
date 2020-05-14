//注册
let register_button = '.register'; //按钮 注册

//账号密码登陆
let pwd_login = '.pwd_login'; //账号密码登陆模块
let username_date = '.username';//输入框  用户名
let password_date = '.password';//输入框  密码
let pwd_login_button = ".pwd_login_btn";//按钮   登陆
let pwdToCaptcha_button = ".captcha_switch_method"; //按钮 切换成验证登陆

//验证码登陆
let captcha_login = '.captcha_login'; //验证码密码登陆模块
let telephone_date = '.telephone';//输入框  手机号码
let captcha_date = '.captcha';//输入框  验证码
let captcha_sms_code = '.send_code';//按钮 发送验证码
let captcha_login_button = ".captcha_login_btn";//按钮   登陆
let captchaToPwd_button = ".pwd_switch_method"; //按钮 切换成账户密码登陆


/*切换登陆模块*/
//账号密码登陆转成验证码登陆
$(pwdToCaptcha_button).on('click', () => {
    $(pwd_login).css("display", "none");
    $(captcha_login).css("display", "block");
});

//验证码登陆转成账号密码登陆
$(captchaToPwd_button).on('click', () => {
    $(pwd_login).css("display", "block");
    $(captcha_login).css("display", "none");
});


//注册
$(register_button).on('click', () => {
    location.href = './register.html';
});


/*账户密码登陆模块*/
//账户密码登陆验证
$(pwd_login_button).on('click', () => {
    const username = $(username_date).val(); //获取用户名
    const pwd = $(password_date).val(); //获取密码
    $.ajax({
        type: "post",
        url: "http://localhost:8088/demo/pwdLogin",
        data: {
            username: username,
            password: pwd
        },
        dataType: "json",
        success: (res) => {
            console.log("成功", res);
            if (res.code == 200) {
                location.href = "./index.html";
            } else if (res.code == 201) {
                alert("密码错误，请重新输入！");
            } else {
                alert("账户不存在，请先注册。")
            }

        },
        error: (er) => {
            console.log("错误", er);
        }
    });
});


/*验证码登陆模块*/

//获取验证码
$(captcha_sms_code).on('click', () => {
    const phone = $(telephone_date).val(); //获取电话号码
    $.ajax({
        type: "post",
        url: "http://localhost:8088/demo/smsLogin",
        data: {
            telephone: phone
        },
        dataType: "json",
        success: (res) => {
            console.log("成功", res);
            const data = res.data;
            if (res.code == 200) {
                console.log(data)
            }
        },
        error: (er) => {
            console.log("错误", er);
        }
    });
});

//验证验证码 登录
$(captcha_login_button).on('click', () => {
    const phone = $(telephone_date).val(); //获取电话号码
    const code = $(captcha_date).val(); //获取验证码
    $.ajax({
        type: "post",
        url: "http://localhost:8088/demo/msgLogin",
        data: {
            telephone: phone,
            code: code
        },
        dataType: "json",
        success: (res) => {
            console.log("成功", res);
            const data = res.data;
            if (res.code == 200) {
                location.href = "./index.html";
            } else if (res.code == 501) {
                alert(res.message);
            } else if (res.code == 500) {
                alert(res.message);
            }
        },
        error: (er) => {
            console.log("错误", er);
        }
    });
});
