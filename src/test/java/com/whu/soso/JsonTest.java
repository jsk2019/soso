package com.whu.soso;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonTest {

    /**
     * 单元测试
     * 时间：2020/7/18 19:10
     * 缺陷描述摘要：缺陷所在模块：APIService.java
     *            缺陷所在函数：GetDistance
     *            缺陷说明：读取嵌套json格式错误
     * 单元测试用例编号：205
     * 程序员：江圣坤
     * 核准时间：20:00
     * 状态：完成
     */

    @Test
    public void jsontest(){
        String myJsonObj2 = "{\n" +
                "    \"name\":\"网站\",\n" +
                "    \"num\":3,\n" +
                "    \"sites\": [\n" +
                "        { \"name\":\"Google\", \"info\":[ \"Android\", \"Google 搜索\", \"Google 翻译\" ] },\n" +
                "        { \"name\":\"Runoob\", \"info\":[ \"菜鸟教程\", \"菜鸟工具\", \"菜鸟微信\" ] },\n" +
                "        { \"name\":\"Taobao\", \"info\":[ \"淘宝\", \"网购\" ] }\n" +
                "    ]\n" +
                "}";
        JSONObject jsonobj2 = JSON.parseObject(myJsonObj2); //将json字符串转换成jsonObject对象
        JSONArray jsonArray = jsonobj2.getJSONArray("sites");
        System.out.println(jsonArray.getJSONObject(1));
    }


}
