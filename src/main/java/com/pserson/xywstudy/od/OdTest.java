package com.pserson.xywstudy.od;

import java.util.*;

public class OdTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Point> list = new ArrayList<>();
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int x = 0;
        while (scanner.hasNextInt()) { // 注意 while 处理多个 case
            for(int i = 0; i < n; i ++){
                int flagValue = scanner.nextInt();
                list.add(new Point(x, i, flagValue));
            }
            x += 1;
            if(x == m){
                break;
            }
        }
        Map<Integer, List<Point>> map = new HashMap<>();
        for (Point point : list) {
            if(map.containsKey(point.getFlagValue())){
                map.get(point.getFlagValue()).add(point);
            }else{
                List<Point> tempList = new ArrayList<>();
                tempList.add(point);
                map.put(point.getFlagValue(), tempList);
            }
        }
        int maxArea = 1;
        for (Integer flagValue : map.keySet()) {
            List<Point> pointList = map.get(flagValue);
            if(pointList.size() < 2 || flagValue == 0){
                continue;
            }
            int area = calArea(pointList);
            if(maxArea < area){
                maxArea = area;
            }
        }
        System.out.println(maxArea);


    }

    private static int calArea(List<Point> pointList) {
        int xMin = 0;
        int xMax = 0;
        int ymin = 0;
        int yMax = 0;
        for (Point point : pointList) {
            xMin = Math.min(xMin, point.getX());
            xMax = Math.max(xMax, point.getX());
            ymin = Math.min(ymin, point.getY());
            yMax = Math.max(yMax, point.getY());
        }
        return (xMax - xMin + 1) * (yMax - ymin + 1);
    }

    /**
     * 考题2
     */
    private static void test2() {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        map.put("5", "e");
        map.put("6", "f");
        map.put("7", "g");
        map.put("8", "h");
        map.put("10*", "i");
        map.put("11*", "j");
        map.put("12*", "l");
        map.put("13*", "m");
        map.put("14*", "n");
        map.put("15*", "o");
        map.put("16*", "p");
        map.put("17*", "q");
        map.put("18*", "r");
        map.put("19*", "s");
        map.put("20*", "t");
        map.put("21*", "u");
        map.put("22*", "v");
        map.put("23*", "w");
        map.put("24*", "x");
        map.put("25*", "y");
        map.put("26*", "z");

        StringBuilder newPassword = new StringBuilder();
        int index = 0;
        for(int i = 0; i < password.length();){
            if((index + 3) <= password.length() && password.substring(index, index + 3).endsWith("*")){
                newPassword.append(map.get(password.substring(index, index + 3)));
                index += 3;
                i += 3;
            }else{
                newPassword.append(map.get(password.substring(index, index + 1)));
                index += 1;
                i += 1;
            }
        }
        System.out.println(newPassword);
    }


}

class Point{
   private int x;
   private int y;
   private int flagValue;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFlagValue() {
        return flagValue;
    }

    public Point(int x, int y, int flagValue) {
        this.x = x;
        this.y = y;
        this.flagValue = flagValue;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", flagValue=" + flagValue +
                '}';
    }
}