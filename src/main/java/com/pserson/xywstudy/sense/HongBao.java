package com.pserson.xywstudy.sense;

import java.util.Random;

public class HongBao {

    public static double getRandomMoney(Redpackage redpackage) {
        if (redpackage.redpackage_size == 1) {
            redpackage.redpackage_size--;
            return (double) Math.round(redpackage.redpackage_money * 100) / 100;
        }

        Random r = new Random();
//      设置每次发红包的最小值
        double min = 0.01;
//      控制当前发红包的最大值
        double max = redpackage.redpackage_money / redpackage.redpackage_size * 2;
//      用随机数与当前发红包的最大钱数相乘，算出当前要发的红包
        double money = r.nextDouble() * max;
//      如果当前发红包的钱小于0.01则，赋值为0.01（规范当前红包最小值）
        money = money <= min ? 0.01 : money;
//      统计为小数点后两位
        money = Math.floor(money * 100) / 100;
        redpackage.redpackage_size--;
        redpackage.redpackage_money -= money;
//      发红包
        return money;

    }


    static class Redpackage {

        double redpackage_money;
        double redpackage_size;

        Redpackage(double redpackage_money, double redpackage_size) {

            this.redpackage_money = redpackage_money;
            this.redpackage_size = redpackage_size;
        }
    }

    public static void main(String[] areg) {

        double redpackage_money = 100;
        double redpackage_size = 10;

        Redpackage redpackage = new Redpackage(redpackage_money, redpackage_size);

        HongBao Hongbao = new HongBao();

        for (int i = 1; i <= 10; i++) {

            double money = Hongbao.getRandomMoney(redpackage);
            System.out.println(money);

        }
    }
}