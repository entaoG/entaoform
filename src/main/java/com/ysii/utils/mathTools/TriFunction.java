package com.ysii.utils.mathTools;

/**
 * @author yskkysll
 */
public class TriFunction {
    public static void main(String[] args) {
        //计算30°的正玄值
        Math.sin(Math.PI/6);
        //计算30°的余玄值
        Math.cos(Math.PI/6);
        //计算30°的正玄值
        Math.tan(Math.PI/6);

        /*
       值得注意角度与弧度互换通常是不精确的
        */
        System.out.println(Math.toRadians(120));//将角度转换成弧度 获取120度的弧度值
        System.out.println(Math.toDegrees(Math.PI/2));//将弧度转换成角度 获取π/2的角度
    }
}
