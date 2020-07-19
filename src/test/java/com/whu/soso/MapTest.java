package com.whu.soso;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {
    /**
     * 单元测试
     * 时间：2020/7/18 21:00
     * 缺陷描述摘要：缺陷所在模块：OrderListService.java
     *            缺陷所在函数：MatchingDriver
     *            缺陷说明：迭代器遍历MAP失败
     * 单元测试用例编号：209
     * 程序员：江圣坤
     * 核准时间：22:00
     * 状态：完成
     */
    @Test
    public void mapTest(String[] args) {
        // 创建一个key和value均为String的Map集合
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");
        // 键和值
        String key = null;
        String value = null;
        // 获取键值对的迭代器
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            key = (String) entry.getKey();
            value = (String) entry.getValue();
            System.out.println("key:" + key + "---" + "value:" + value);
        }

    }

    @Test
    /**
     * 单元测试
     * 时间：2020/7/18 22:20
     * 缺陷描述摘要：缺陷所在模块：OrderListService.java
     *            缺陷所在函数：MatchingDriver
     *            缺陷说明：从MAP中取值失败，无法根据Value获取KEY
     * 单元测试用例编号：210
     * 程序员：郭香俊
     * 核准时间：22:40
     * 状态：完成
     */
    public void MapGetValueTest(){
        Map<String,Long> distanceMap = new HashMap<>();
        distanceMap.put("1",1L);
        distanceMap.put("2",2L);
        distanceMap.put("3",3L);
        Comparator<Map.Entry<String, Long>> valueComparator = new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };
        List<Map.Entry<String, Long>> list = new ArrayList<Map.Entry<String, Long>>(distanceMap.entrySet());
        // 排序
        Collections.sort(list, valueComparator);
        String telephone = list.get(0).getKey();
        System.out.println(telephone);
    }
}
