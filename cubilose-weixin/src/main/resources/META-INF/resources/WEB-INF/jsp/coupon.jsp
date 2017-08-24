<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
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
            /*padding: 16px*/
        }

        .page_hd {
            padding: 16px;
            text-align: center;
            height: 100%;
        }

        .page_ft {
            padding: 32px 16px 10px 16px;
            text-align: center;
        }

    </style>
</head>
<body>

<div id="app" class="page">

    <div class="page_hd">
        <img :src="user.wImg" alt="" style="height: 96px; width: 96px;">
        <h3>{{user.wName}}</h3>
    </div>

    <div class="page_bd">

        <div class="weui-cells__title">优惠码</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" style="text-align: center; width: 85%;"
                           type="text" placeholder="输入优惠券码"
                           v-model="couponCode" v-bind:readonly="couponConvertSucceeded">
                    <i class="weui-icon-success-no-circle" v-show="couponConvertSucceeded"></i>
                </div>
            </div>
        </div>

        <%-- 收货地址信息 --%>
        <div class="address_info" v-show="couponConvertSucceeded">

            <div class="weui-cells__title">收货地址</div>
            <div class="weui-cells weui-cells_form">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <textarea class="weui-textarea" placeholder="" rows="3"></textarea>
                    </div>
                </div>
            </div>

            <div class="weui-cells__title">手机号</div>
            <div class="weui-cells">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入号码">
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="page_ft">
        <a href="javascript:;" class="weui-btn weui-btn_primary"
           @click="convert" v-show="!couponConvertSucceeded">兑换</a>

        <a href="javascript:;" class="weui-btn weui-btn_primary"
           @click="saveExpressageInfo" v-show="couponConvertSucceeded">保存</a>
    </div>

    <div id="loadingToast" v-show="showLoadingToast">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">保存信息中..</p>
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
            couponCode: '',
            couponConvertSucceeded: false,
            showLoadingToast: false
        },
        methods: {
            // 兑换优惠券
            convert () {
                console.log(this.couponCode)
                this.couponConvertSucceeded = true
            },
            // 保存
            saveExpressageInfo () {
                this.showLoadingToast = true
            }
        },
        created () {
        }
    })
</script>
</body>
</html>