package com.pserson.xywstudy.od;

import java.util.*;

public class OdTest2 {
    public static void main(String[] args) {
        test2();

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
                if(map.containsKey(password.substring(index, index + 1))){
                    newPassword.append(map.containsKey(password.substring(index, index + 1)));
                }else{
                    newPassword.append(password.substring(index, index + 1));
                }
                index += 1;
                i += 1;
            }
        }
        System.out.println(newPassword);
    }


}
