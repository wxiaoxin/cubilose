package com.cubilose;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    @Test
    public void test1() {

        List<Character> characters = new ArrayList<>();

        for (int i = 48; i < 58; i++) {
            characters.add((char) i);
        }

        for (int i = 65; i < 91; i++) {
            characters.add((char) i);
        }

        characters.forEach(c -> {
            System.out.print("'" + c + "',");
        });

        System.out.println();

        Set<String> code = new HashSet<>();
        StringBuffer sbf = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int next = 0;
            for (int j = 0; j < 10; j++) {
                next = random.nextInt(36);
                sbf.append(characters.get(next));
            }
            code.add(sbf.toString());
            sbf.delete(0, sbf.length());
        }

        System.out.println(code.size());
        code.forEach(s -> {
            System.out.print(s + " ");
        });
    }

    @Test
    public void test2() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(System.currentTimeMillis());
        // 1503147667965
        // 1498040822000
        Date date = new Date(1498040822000L);
        System.out.println(dateFormat.format(date));
    }

    @Test
    public void test3() {
        String s = "[\"a\",\"b\",\"c\",\"d\"]";
        JSONArray objects = JSONObject.parseArray(s);
        Iterator<Object> iterator = objects.iterator();
        while (iterator.hasNext()) {
            String next = (String) iterator.next();
            System.out.println(next);
        }
    }


    @Test
    public void test4() {
        String s = "aae\n" +
                "aae全球专递\n" +
                "anjie\n" +
                "安捷快递\n" +
                "anxindakuaixi\n" +
                "安信达快递\n" +
                "biaojikuaidi\n" +
                "彪记快递\n" +
                "bht\n" +
                "bht\n" +
                "baifudongfang\n" +
                "百福东方国际物流\n" +
                "coe\n" +
                "中国东方（COE）\n" +
                "changyuwuliu\n" +
                "长宇物流\n" +
                "datianwuliu\n" +
                "大田物流\n" +
                "debangwuliu\n" +
                "德邦物流\n" +
                "dhl\n" +
                "dhl\n" +
                "dpex\n" +
                "dpex\n" +
                "dsukuaidi\n" +
                "d速快递\n" +
                "disifang\n" +
                "递四方\n" +
                "ems\n" +
                "ems快递\n" +
                "fedex\n" +
                "fedex（国外）\n" +
                "feikangda\n" +
                "飞康达物流\n" +
                "fenghuangkuaidi\n" +
                "凤凰快递\n" +
                "feikuaida\n" +
                "飞快达\n" +
                "guotongkuaidi\n" +
                "国通快递\n" +
                "ganzhongnengda\n" +
                "港中能达物流\n" +
                "guangdongyouzhengwuliu\n" +
                "广东邮政物流\n" +
                "gongsuda\n" +
                "共速达\n" +
                "huitongkuaidi\n" +
                "汇通快运\n" +
                "hengluwuliu\n" +
                "恒路物流\n" +
                "huaxialongwuliu\n" +
                "华夏龙物流\n" +
                "haihongwangsong\n" +
                "海红\n" +
                "haiwaihuanqiu\n" +
                "海外环球\n" +
                "jiayiwuliu\n" +
                "佳怡物流\n" +
                "jinguangsudikuaijian\n" +
                "京广速递\n" +
                "jixianda\n" +
                "急先达\n" +
                "jjwl\n" +
                "佳吉物流\n" +
                "jymwl\n" +
                "加运美物流\n" +
                "jindawuliu\n" +
                "金大物流\n" +
                "jialidatong\n" +
                "嘉里大通\n" +
                "jykd\n" +
                "晋越快递\n" +
                "kuaijiesudi\n" +
                "快捷速递\n" +
                "lianb\n" +
                "联邦快递（国内）\n" +
                "lianhaowuliu\n" +
                "联昊通物流\n" +
                "longbanwuliu\n" +
                "龙邦物流\n" +
                "lijisong\n" +
                "立即送\n" +
                "lejiedi\n" +
                "乐捷递\n" +
                "minghangkuaidi\n" +
                "民航快递\n" +
                "meiguokuaidi\n" +
                "美国快递\n" +
                "menduimen\n" +
                "门对门\n" +
                "ocs\n" +
                "OCS\n" +
                "peisihuoyunkuaidi\n" +
                "配思货运\n" +
                "quanchenkuaidi\n" +
                "全晨快递\n" +
                "quanfengkuaidi\n" +
                "全峰快递\n" +
                "quanjitong\n" +
                "全际通物流\n" +
                "quanritongkuaidi\n" +
                "全日通快递\n" +
                "quanyikuaidi\n" +
                "全一快递\n" +
                "rufengda\n" +
                "如风达\n" +
                "santaisudi\n" +
                "三态速递\n" +
                "shenghuiwuliu\n" +
                "盛辉物流\n" +
                "shentong\n" +
                "申通\n" +
                "shunfeng\n" +
                "顺丰\n" +
                "sue\n" +
                "速尔物流\n" +
                "shengfeng\n" +
                "盛丰物流\n" +
                "saiaodi\n" +
                "赛澳递\n" +
                "tiandihuayu\n" +
                "天地华宇\n" +
                "tiantian\n" +
                "天天快递\n" +
                "tnt\n" +
                "tnt\n" +
                "ups\n" +
                "ups\n" +
                "wanjiawuliu\n" +
                "万家物流\n" +
                "wenjiesudi\n" +
                "文捷航空速递\n" +
                "wuyuan\n" +
                "伍圆\n" +
                "wxwl\n" +
                "万象物流\n" +
                "xinbangwuliu\n" +
                "新邦物流\n" +
                "xinfengwuliu\n" +
                "信丰物流\n" +
                "yafengsudi\n" +
                "亚风速递\n" +
                "yibangwuliu\n" +
                "一邦速递\n" +
                "youshuwuliu\n" +
                "优速物流\n" +
                "youzhengguonei\n" +
                "邮政包裹挂号信\n" +
                "youzhengguoji\n" +
                "邮政国际包裹挂号信\n" +
                "yuanchengwuliu\n" +
                "远成物流\n" +
                "yuantong\n" +
                "圆通速递\n" +
                "yuanweifeng\n" +
                "源伟丰快递\n" +
                "yuanzhijiecheng\n" +
                "元智捷诚快递\n" +
                "yunda\n" +
                "韵达快运\n" +
                "yuntongkuaidi\n" +
                "运通快递\n" +
                "yuefengwuliu\n" +
                "越丰物流\n" +
                "yad\n" +
                "源安达\n" +
                "yinjiesudi\n" +
                "银捷速递\n" +
                "zhaijisong\n" +
                "宅急送\n" +
                "zhongtiekuaiyun\n" +
                "中铁快运\n" +
                "zhongtong\n" +
                "中通速递\n" +
                "zhongyouwuliu\n" +
                "中邮物流\n" +
                "zhongxinda\n" +
                "忠信达\n" +
                "zhimakaimen\n" +
                "芝麻开门\n";

        String[] split = s.split("\\n");
        System.out.println(split.length);

        JSONArray array = new JSONArray();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < split.length; i = i + 2) {
            JSONObject obj = new JSONObject();
            obj.put("name", split[i + 1]);
            obj.put("code", split[i]);
            array.add(obj);
        }

        System.out.println(array.toJSONString());
    }

}
