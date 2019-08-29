package com.hansai.alldemo.demo.javase.bitwise_operators;

/**
 * <p>Description: </p>
 * <p> </p>
 *
 * @author: hansai
 * @date: 2019/6/18
 * @time: 10:44
 */
public class BitwiseTest {

    /**
     * >> <<位移运算
     * >>> 无符号右移
     */
    public void moveCompute() {
        // a>>b a的2进制数向右移b位，   相当于 a除以(2的b次方)取整
        System.out.println(20>>2);

        // a<<b a的2进制数向左移b位，   相当于 a乘以(2的b次方)
        System.out.println(20<<2);
        // 还没搞懂
        System.out.println(20>>>2);

    }

    /**
     * & 按位与
     * | 按位或
     * ^ 按位异或
     * ~ 按位取反
     */
    public void andCompute() {
        /*   &  按位与
             *  0011
            *------- => 0011   相当于3&11=3
              *  1011
         */
        System.out.println(3&11);

        /*  |  按位或
             *  0011
            *  ------- => 1011   相当于3|11=11
              * 1011
         */
        System.out.println(3|11);
        System.out.println(77|55);

        /*  ^  按位异或  若参加运算的两个二进制位值相同则为0，否则为1
             *  0011
            *  ------- => 1000   相当于3^11=8
              * 1011
         */
        System.out.println(3^11);


        /*  ~  按位取反  1变0  0变1 ，带符号位
               计算过程
                      补码         取反         原码           末位+1
            *  0 0011 ===> 0 0011 ===> 1 1100  ==>   1 0011  ==> 1 00100
            *  最终结果为 - 4
         */
        System.out.println(~3);


    }




}
