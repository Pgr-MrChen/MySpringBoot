//注册
let username_date = '.username';//输入框  用户名
let password_date = '.password';//输入框  密码
let telephone_date = '.telephone';//输入框 电话号码
let captcha_date = '.captcha';//输入框 验证码
let sms_button = '.smsBtn'; //按钮 发送验证码短信
let register_button = ".registerBtn";//按钮  注册

//发送验证码
$(sms_button).on('click', () => {
    const telephone = $(telephone_date).val(); //获取电话号码
    $.ajax({
        type: "post",
        url: "http://localhost:8088/demo/smsRegister",
        data: {
            telephone: telephone,
        },
        dataType: "json",
        success: (res) => {
            console.log("成功", res);
            if (res.code == 502) {
                alert(res.message);
            }
        },
        error: (er) => {
            console.log("错误", er);
        }
    });
});

//注册用户
$(register_button).on('click', () => {
    const username = $(username_date).val();
    const password = $(password_date).val();
    const telephone = $(telephone_date).val(); //获取电话号码
    const captcha = $(captcha_date).val();
    $.ajax({
        type: "post",
        url: "http://localhost:8088/demo/register",
        data: {
            username: username,
            password: password,
            telephone: telephone,
            captcha: captcha,
        },
        dataType: "json",
        success: (res) => {
            console.log("成功", res);
            if (res.code == 102) {
                alert("注册成功！");
                location.href = "./login.html";
            } else if (res.code == 101) {
                alert(res.message);
                location.href = "./login.html";
            }
        },
        error: (er) => {
            console.log("错误", er);
        }
    });
});
