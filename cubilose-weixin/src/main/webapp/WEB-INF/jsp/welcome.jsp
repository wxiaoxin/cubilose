<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>首页</title>
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

        .page_hd {
            text-align: center;
            height: 100%;
        }

        .page_ft {
            padding-top: 40px;
            padding-bottom: 10px;
            text-align: center;
        }

        .card-detail {
            position: relative;
            margin-top: 10px;
            width: 100%;
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
        }

        .card-detail-left,
        .card-detail-right {
            position: relative;
            float: left;
        }

        .card-detail-left {
            width: 25%;
        }

        .bd2 {
            border-right: 1px dashed #75A0BD;
        }

        .card-detail-left-top {
            width: 100%;
            height: 120px;
            border-radius: 8px 0 0 0;
        }

        .card-detail-left-top.bg2 {
            background-color: #1aad19;
        }

        .card-detail-left-top:before,
        .card-detail-left-top:after {
            content: "";
            position: absolute;
            right: -8px;
            display: block;
            width: 16px;
            height: 16px;
            background-color: #F5F5F5;
            border-radius: 50%;
            z-index: 11;
        }

        .card-detail-left-top:after {
            top: -10px;
        }

        .card-detail-left-top:before {
            bottom: -10px;
        }

        .card-detail-left-bottom {
            width: 100%;
            height: 40px;
            background-color: #fff;
            border-radius: 0 0 0 8px;
        }

        .left-circle {
            position: absolute;
            top: 50%;
            left: -25px;
            margin-top: -25px;
            width: 50px;
            height: 50px;
            background-color: #F5F5F5;
            border-radius: 50%;
        }

        .card-detail-right {
            position: relative;
            overflow: hidden;
            -webkit-box-flex: 1;
            -webkit-flex: 1;
            -ms-flex: 1;
            flex: 1;
        }

        .card-detail-right-top {
            position: relative;
            height: 120px;
            border-radius: 0 8px 0 0;
        }

        .card-detail-right-top.bg2 {
            background-color: #50bf4f;
        }

        .card-detail-right-top div:nth-child(1) {
            position: absolute;
            top: 8px;
            right: 12px;
            color: rgba(255, 255, 255, .8);
            font-size: 20px;
        }

        .card-detail-right-top div:nth-child(2) {
            position: absolute;
            top: 35px;
            right: 112px;
            color: #fff;
            font-size: 25px;
        }

        .card-detail-right-top div:nth-child(2) span {
            margin-left: 5px;
            font-size: 60px;
        }

        .card-detail-right-top div:nth-child(3) {
            position: absolute;
            top: 40px;
            right: 25px;
            color: #B87D01;
            font-size: 30px;
        }

        .card-detail-right-top div:nth-child(3).c1,
        .card-detail-right-top div:nth-child(4).c1 {
            color: #2671A5;
        }

        .card-detail-right-top div:nth-child(4) {
            position: absolute;
            margin-top: 5px;
            top: 80px;
            right: 12px;
            color: #B87D01;
            font-size: 16px;
        }

        .card-detail-right-bottom {
            width: 100%;
            padding-left: 8px;
            padding-right: 8px;
            height: 40px;
            font-size: 14px;
            background-color: #fff;
            border-radius: 0 0 8px 0;
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -webkit-align-items: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: justify;
            -webkit-justify-content: space-between;
            -ms-flex-pack: justify;
            justify-content: space-between;
        }

        .card-detail-right-bottom span:nth-child(2) {
            text-align: right;
        }
    </style>
</head>
<body>

<div id="app" class="page">

    <div class="page_hd">
        <img :src="user.wImg" alt="" style="height: 96px; width: 96px;">
        <h3>{{user.wName}}</h3>
    </div>

    <div class="page_bd" style="text-align: center">

        <template v-for="coupon in coupons">
            <a href="javascript:void(0);" @click="status(coupon.couponId)">
                <div class="card-detail clearfix">
                    <!-- 左边大色块 -->
                    <div class="card-detail-left">
                        <div class="card-detail-left-top bg2 bd2">
                            <!-- 左边半圆 -->
                            <div class="left-circle"></div>
                        </div>
                        <div class="card-detail-left-bottom bd2"></div>
                    </div>
                    <!-- 右边大色块 -->
                    <div class="card-detail-right">
                        <div class="card-detail-right-top bg2">
                            <div>{{coupon.code}}</div>
                            <div>&yen;<span>{{coupon.price}}</span></div>
                            <div class="c1"></div>
                            <div class="c1"></div>
                        </div>
                        <div class="card-detail-right-bottom" style="padding-left: 24px;">
                            <span style="color: dimgrey;">兑换时间：{{formatDateTime(coupon.getTime)}}</span>
                            <!--<span>已使用</span>-->
                        </div>
                    </div>
                </div>
            </a>
        </template>

    </div>

    <div class="page_ft">
        <a href="javascript:;" class="weui-btn weui-btn_primary" @click="goto">兑换优惠券</a>
    </div>
</div>

<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.bootcss.com/axios/0.16.2/axios.min.js"></script>
<script>

    var app = new Vue({
        el: '#app',
        data: {
            user: ${user},
            coupons: ${coupons}
        },
        methods: {
            getUserByWId () {
                axios.get('http://birdnesket.wenqie.info/user/query/w/' + user.wId)
                    .then((response) => {
                        let respData = response.data
                        console.log(respData)
                        if (respData.code === 0) {
                            this.user = respData.data
                        }
                    })
            },
            goto () {
                window.location.href = 'http://birdnesket.wenqie.info/coupon/' + this.user.wId
            },
            // 查询物流信息页
            status (couponId) {
                window.location.href = 'http://birdnesket.wenqie.info/status?couponId=' + couponId
            },
            // 格式化时间
            formatDateTime (time) {
                if (time === undefined || time === '') {
                    return ''
                }
                let date = new Date(time)
                let y = date.getFullYear() + ' ';
                let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
                let D = date.getDate() + ' '
                let h = date.getHours() + ':'
                let m = date.getMinutes()
                return y + M + D + h + m
            }
        },
        created () {
        }
    })
</script>
</body>
</html>