//首页
let email_data = '.email'; //输入框 邮件
let sendMail_button = '.sendMailBtn'; //按钮 发送邮件按钮

//通过密码登陆
$(sendMail_button).on('click', () => {
    const email = $(email_data).val(); //获取邮箱
    $.ajax({
        type: "get",
        url: "http://localhost:8088/demo/sendMail",
        dataType: "json",
        data: {
            email: email,
        },
        success: (res) => {
            console.log("成功", res);
        },
        error: (er) => {
            console.log("系统错误", er);
        }

    })
});
