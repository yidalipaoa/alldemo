package com.hansai.alldemo.demo.clusterAlgorithm.tfidf;

import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/7/15
 * @time: 10:06
 */
public class TfidfAlgorithm {
    /*
    http://www.ruanyifeng.com/blog/2013/03/tf-idf.html
    TFIDF的主要思想是：如果某个词或短语在一篇文章中出现的频率TF高，并且在其他文章中很少出现，则认为此词或者短语具有很好的类别区分能力，适合用来分类。
TFIDF实际上是：TF * IDF，TF词频(Term Frequency)，IDF逆向文件频率(Inverse Document Frequency)。TF表示词条在文档d中出现的频率。
IDF的主要思想是：如果包含词条t的文档越少，也就是n越小，IDF越大，则说明词条t具有很好的类别区分能力。如果某一类文档C中包含词条t的文档数为m，
而其它类包含t的文档总数为k，显然所有包含t的文档数n=m+k，当m大的时候，n也大，按照IDF公式得到的IDF的值会小，就说明该词条t类别区分能力不强。
但是实际上，如果一个词条在一个类的文档中频繁出现，则说明该词条能够很好代表这个类的文本的特征，这样的词条应该给它们赋予较高的权重，
并选来作为该类文本的特征词以区别与其它类文档。这就是IDF的不足之处. 在一份给定的文件里，
词频（term frequency，TF）指的是某一个给定的词语在该文件中出现的频率。
这个数字是对词数(term count)的归一化，以防止它偏向长的文件。（同一个词语在长文件里可能会比短文件有更高的词数，而不管该词语重要与否
     */

    public static void main(String[] args) {
        String url = "https://www.cnblogs.com/justcooo-oode/p/7831157.html?啊啊水电费";

        String[] strs = url.split("\\/|-");
        Arrays.asList(strs).forEach(System.out::println);
    }

    /**
     * 数据预处理 得到分词结果、TF
     * 单个单词的 TF= 单个单词出现的次数/总的单词数
     * @param urlList   url 列表
     * @return Map<String,Object>
     */
    public static Map<String,Object> tfCalculate(List<String> urlList) {
        Map<String, Object> resultMap = new HashMap<>();
        //url分词结果
        Map<String, List<String>> urlWordMap = new HashMap<>();
        //存放（单词，单词数量）
        Map<String, Integer> singleWordCountMap = new HashMap<String, Integer>();
        //存放（单词，单词词频）
        Map<String, Float> tfMap = new HashMap<String, Float>();
        //插入到返回结果的map
        resultMap.put("urlWord", urlWordMap);
        resultMap.put("singleWordCount", singleWordCountMap);
        resultMap.put("tf", tfMap);

        //单词总数
        int count = 0;
        List<String> urlWordsList = new ArrayList<>();
        for (String url : urlList){
            //1：分词  通过/分词，去除 http https www com cn org
            urlWordsList = divide(url);
            urlWordMap.put(url, urlWordsList);
            //2：遍历 分好的词
            for (String word : urlWordsList) {
                //向单词Map存放数据
                if (!StringUtils.isEmpty(word)) {
                    if (singleWordCountMap.containsKey(word)) {
                        singleWordCountMap.put(word,singleWordCountMap.get(word)+1) ;
                    }else {
                        singleWordCountMap.put(word,1) ;
                    }
                    //计算单词总数
                    count++;
                }
            }
        }

        //计算TF 词频 单个单词的 TF= 单个单词出现的次数/总的单词数
        for (Map.Entry<String, Integer> entry : singleWordCountMap.entrySet()) {
            float tf = (float)entry.getValue()/count;
            System.out.println("word=="+entry.getKey()+";;; tf=="+tf);
            tfMap.put(entry.getKey(), tf);
        }
        resultMap.put("wordCount", count);
        return resultMap;
    }

    /**
     * 分词
     * @return
     */
    public static List<String> divide(String url) {

        List<String> list = new ArrayList<>();
        // https://www.cnblogs.com/justcooooode/p/7831157.html?aaaa=
        //停用词
        String remove = ",http,http:,https:,www.,.com,.cn,.xyz,.top,.wang,.pub,.xin,.net,.org,.html,.jsp,.do,.vm,";
        //正则表达式，无用词
        String regex = "(http:|https:|www\\.|\\.com\\b|\\.cn\\b|\\.xyz\\b|\\.top\\b|\\.wang\\b|\\.pub\\b" +
                "|\\.xin\\b|\\.net\\b|\\.org\\b|\\.html\\b|\\.htm\\b|\\.jsp\\b|\\.do\\b|\\.vm\\b|[\\u4e00-\\u9fa5 0-9])";
        //去除参数部分 ?号后面,并转小写
        url = url.split("\\?")[0].toLowerCase();

        //去除无用词
        url = url.replaceAll(regex, "");
        //以 /或者-分隔
        String[] strs = url.split("\\/|-|_|\\.");
        //遍历
        String word = "";
        for (int i=0;i<strs.length;i++){
            word = strs[i];
            if (!StringUtils.isEmpty(word)) {
                list.add(word);
            }
        }
        return list;
    }

    /**
     *  计算权重 = TF*IDF
     *  单个词的IDF = log (总url数/包含该词的url数)
     *
     * @param urlList
     * @return
     */
    public static Map<String, Float> tfidfCalculate(List<String> urlList) {

        //计算TF
        Map<String, Object> dataMap = tfCalculate(urlList);
        //url的集合，key：url  value: url的分词list
        Map<String, List<String>> urlWordMap = (Map<String, List<String>>) dataMap.get("urlWord");
        //存放（单词，单词词频）
        Map<String, Float> tfMap = (Map<String, Float>) dataMap.get("tf");

        Map<String, Float> tfidfMap = new HashMap<>();

        //遍历tfMap
        for (Map.Entry<String, Float> wordsEntry : tfMap.entrySet()) {
            String word = wordsEntry.getKey();
            float tf = wordsEntry.getValue();
            int UrlContainsThisWordCount = 0;
            //遍历url 集合，计算包含该单词的url数
            for (Map.Entry<String, List<String>> urlEntry : urlWordMap.entrySet()){
                if (urlEntry.getValue().contains(word)) {
                    UrlContainsThisWordCount++;
                }
            }

            //计算idf

            float idf = (float)Math.log((float)urlWordMap.size() / (UrlContainsThisWordCount+1));
            System.out.println(word+"---tf==="+tf+"====idf===="+idf);


            //计算TF*IDF
            tfidfMap.put(word, tf * idf);

        }

        return tfidfMap;
    }
}
