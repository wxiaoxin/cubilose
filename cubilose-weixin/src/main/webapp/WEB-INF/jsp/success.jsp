<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>恭喜，兑换成功</title>
    <link href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            padding: 0;
            background: #F5F5F5;
        }

        .page {
            padding: 16px
        }

    </style>
</head>
<body>

<div id="app" class="page">

    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">恭喜，兑换成功</h2>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="javascript:0;" class="weui-btn weui-btn_primary" @click="toIndex">去首页</a>
            </p>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.bootcss.com/axios/0.16.2/axios.min.js"></script>
<script>

    var app = new Vue({
        el: '#app',
        data: {
            user: ${user},
            couponCode: ''
        },
        methods: {
            toIndex() {
                window.location.href
                    = 'http://localhost:10086/index/' + this.user.wId
            }
        },
        created () {}
    })
</script>
</body>
</html>