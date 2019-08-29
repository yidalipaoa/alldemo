package com.hansai.alldemo.demo.netty.common;

public class Utils {
    public static byte intToByte(int x) {
        return (byte) x;
    }

    public static int byteToInt(byte b) {
        // Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值
        return b & 0xFF;
    }

    public static int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF + (b[2] & 0xFF) * 0xFF + (b[1] & 0xFF) * 0xFFFF
                + (b[0] & 0xFF) * 0xFFFFFF;
    }

    public static int byteArrayToInt2(byte[] b) {
        return b[1] & 0xFF + (b[0] & 0xFF) * 0xFF;
    }

    public static byte[] intToByteArray(int a) {
        return new byte[] { (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF) };
    }

    public static byte[] shortToByteArray2(short a) {
        return new byte[] {  (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF) };
    }

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }


    public static int byteToInt(byte[] bytes) {
        int num = 0;
        int temp;
        temp = (0x000000ff & (bytes[0])) << 0;
        num = num | temp;
        temp = (0x000000ff & (bytes[1])) << 8;
        num = num | temp;
        temp = (0x000000ff & (bytes[2])) << 16;
        num = num | temp;
        temp = (0x000000ff & (bytes[3])) << 24;
        num = num | temp;
        return num;
    }

}
