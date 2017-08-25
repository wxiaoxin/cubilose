<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>兑换优惠券</title>
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

        <div class="weui-cells__title">优惠码 <span style="color: red;">{{errorMessage}}</span></div>
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
                        <textarea class="weui-textarea" placeholder=""
                                  rows="3" v-model="address"></textarea>
                    </div>
                </div>
            </div>

            <div class="weui-cells__title">手机号</div>
            <div class="weui-cells">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <input class="weui-input" type="number"
                               pattern="[0-9]*" placeholder="请输入号码" v-model="phoneNumber">
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

    <div id="toast_1" v-show="receiveSucceed">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">领取成功</p>
        </div>
    </div>

    <div id="toast_2" v-show="saveSucceed">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">已完成</p>
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
            showLoadingToast: false,
            saveSucceed: false,
            receiveSucceed: false,
            errorMessage: '',
            userCouponId: '',
            address: '',
            phoneNumber: ''
        },
        methods: {
            // 兑换优惠券
            convert () {
                console.log(this.couponCode)
                axios.get('http://localhost:10086/user/receive', {
                    params: {
                        wId: this.user.wId,
                        couponCode: this.couponCode
                    }
                }).then((response) => {
                    let respData = response.data
                    if (respData.code < 0) {
                        let code = respData.code
                        if (code == -1) {
                            this.errorMessage = '该优惠券无法使用'
                        } else if (code == -2) {
                            this.errorMessage = '该优惠券已使用'
                        } else if (code == -3) {
                            this.errorMessage = '保存优惠券失败'
                        } else if (code == -4) {
                            this.errorMessage = '该优惠券不存在'
                        } else {
                            this.errorMessage = '未知异常'
                        }
                    } else {
                        this.userCouponId = respData.data
                        this.receiveSucceed = true
                        setTimeout(() => {
                            this.receiveSucceed = false
                        }, 1000);
                        this.couponConvertSucceeded = true
                    }
                })
            },
            // 保存收货信息
            saveExpressageInfo () {
                this.showLoadingToast = true
                console.log(this.user.id)
                console.log(this.address)
                console.log(this.phoneNumber)
                console.log(this.userCouponId)
                axios.get('http://localhost:10086/user/saa', {
                    params: {
                        userId: this.user.id,
                        address: this.address,
                        phoneNumber: this.phoneNumber,
                        userCouponId: this.userCouponId
                    }
                }).then((response) => {
                    let respData = response.data
                    if (respData.data == 0) {
                        setTimeout(() => {
                            this.showLoadingToast = false
                            window.location.href = 'http://localhost:10086/success/' + this.user.wId
                        }, 1000)
                    }
                })
            }
        },
        watch: {
            couponCode: function (value, oldValue) {
                this.errorMessage = ''
            }
        },
        created () {
        }
    })
</script>
</body>
</html>