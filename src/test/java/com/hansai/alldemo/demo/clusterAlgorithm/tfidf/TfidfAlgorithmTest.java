package com.hansai.alldemo.demo.clusterAlgorithm.tfidf;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TfidfAlgorithmTest {
    @Test
    public void main() throws Exception {
    }

    @Test
    public void tfCalculate() throws Exception {
    }

    @Test
    public void divide() throws Exception {
        String url = "https://www.cnb-logs.com/justcoo-ooode/p/7831157.html?山东省地方=啊啊啊啊啊";
        List<String> urlList = new ArrayList<>();

        urlList.add(url);
        urlList.add("http://tool.oschina.net/regex/");
        urlList.add("https://www.jianshu.com/p/6f3bd52ed211");
        urlList.add("https://blog.csdn.net/weixin_42634808/article/details/86563048");
        urlList.add("https://blog.sdfsf.net/weixiwerc_42634808/article/detailssss/86563048");
        urlList.add("https://blog.afdsfs.net/weixsdfs_42634808/article/detasdfsd/86563048");



//        List<String> list = TfidfAlgorithm.divide(url);
        Map<String, Float> map =  TfidfAlgorithm.tfidfCalculate(urlList);
        map.forEach((key,value)-> System.out.println("key=="+key+";"+"value=="+map.get(key)));
    }

    @Test
    public void tfidfCalculate() throws Exception {
    }

}