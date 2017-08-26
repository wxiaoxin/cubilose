<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>物流状态页</title>
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
        <%--<img :src="user.wImg" alt="" style="height: 96px; width: 96px;">--%>
        <%--<h3>{{user.wName}}</h3>--%>
    </div>

    <div class="page_bd">

            <div class="weui-panel weui-panel_access">
                <div class="weui-panel__hd">收货信息</div>
                <div class="weui-panel__bd">
                    <div class="weui-media-box weui-media-box_text">
                        <h4 class="weui-media-box__title">地址</h4>
                        <p class="weui-media-box__desc">{{userAddress.address}}</p>
                    </div>
                    <div class="weui-media-box weui-media-box_text">
                        <h4 class="weui-media-box__title">电话</h4>
                        <p class="weui-media-box__desc">{{userAddress.phoneNumber}}</p>
                    </div>
                </div>
            </div>


            <div class="weui-panel weui-panel_access">
                <div class="weui-panel__hd">物流信息</div>
                <div class="weui-panel__bd">
                    <template v-if="userCoupon.deliveryTime === undefined">
                        <div class="weui-media-box weui-media-box_text">
                            <h4 class="weui-media-box__title">未发货</h4>
                        </div>
                    </template>
                    <template v-else>
                        <div class="weui-media-box weui-media-box_text">
                            <h4 class="weui-media-box__title">时间</h4>
                            <p class="weui-media-box__desc">{{formatDateTime(userCoupon.deliveryTime)}}</p>
                        </div>
                        <div class="weui-media-box weui-media-box_text">
                            <h4 class="weui-media-box__title">公司</h4>
                            <p class="weui-media-box__desc">{{getExpressageName(userCoupon.logisticsNumber)}}</p>
                        </div>
                        <div class="weui-media-box weui-media-box_text">
                            <h4 class="weui-media-box__title">单号</h4>
                            <p class="weui-media-box__desc">{{getExpressageNumber(userCoupon.logisticsNumber)}}</p>
                        </div>
                        <div class="weui-panel__ft">
                            <a :href="getUrl(userCoupon.logisticsNumber)"
                               class="weui-cell weui-cell_access weui-cell_link">
                                <div class="weui-cell__bd">查看物流状态</div>
                                <span class="weui-cell__ft"></span>
                            </a>
                        </div>
                    </template>
                </div>

            </div>

        </div>

    </div>

</div>

<script src="https://cdn.bootcss.com/vue/2.4.2/vue.min.js"></script>
<script src="https://cdn.bootcss.com/axios/0.16.2/axios.min.js"></script>
<script>

    var app = new Vue({
        el: '#app',
        data: {
            userAddress: ${userAddress},
            userCoupon: ${userCoupon},
            expressages: [
                {
                    'code': 'debangwuliu',
                    'name': '德邦物流'
                },
                {
                    'code': 'ems',
                    'name': 'ems快递'
                },
                {
                    'code': 'guotongkuaidi',
                    'name': '国通快递'
                },
                {
                    'code': 'quanfengkuaidi',
                    'name': '全峰快递'
                },
                {
                    'code': 'rufengda',
                    'name': '如风达'
                },
                {
                    'code': 'shentong',
                    'name': '申通'
                },
                {
                    'code': 'shunfeng',
                    'name': '顺丰'
                },
                {
                    'code': 'tiantian',
                    'name': '天天快递'
                },
                {
                    'code': 'yuantong',
                    'name': '圆通速递'
                },
                {
                    'code': 'yunda',
                    'name': '韵达快运'
                },
                {
                    'code': 'zhaijisong',
                    'name': '宅急送'
                },
                {
                    'code': 'zhongtong',
                    'name': '中通速递'
                },
                {
                    'code': 'zhimakaimen',
                    'name': '芝麻开门'
                }
            ]
        },
        methods: {
            getExpressageName (logisticsNumber) {
                if (logisticsNumber === '' || logisticsNumber === undefined) {
                    return '暂无'
                } else {
                    let split = logisticsNumber.split('-');
                    expressageCode = split[0]
                    for (let i = 0; i < this.expressages.length; i++) {
                        let e = this.expressages[i]
                        if (e['code'] === expressageCode) {
                            return e['name']
                        }
                    }
                }
            },
            getExpressageNumber (logisticsNumber) {
                if (logisticsNumber === '' || logisticsNumber === undefined) {
                    return ''
                } else {
                    let split = logisticsNumber.split('-');
                    return split[1]
                }
            },
            getUrl (logisticsNumber) {
                if (logisticsNumber !== undefined && logisticsNumber !== '') {
                    let split = logisticsNumber.split('-')
                    return 'https://m.kuaidi100.com/result.jsp?com=' + split[0] + '&nu=' + split[1]
                } else {
                    return ''
                }
            },
            // 格式化时间
            formatDateTime (time) {
                if (time === undefined || time === '') {
                    return ''
                }
                let date = new Date(time)
                let Y = (date.getFullYear()) + ' '
                let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
                let D = date.getDate() + ' '
                let h = date.getHours() + ':'
                let m = date.getMinutes()
                return Y + M + D + h + m
            }
        }
    })
</script>
</body>
</html>