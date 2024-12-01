package com.pserson.xywstudy;


import org.assertj.core.util.Lists;
import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.*;

public class AlgoTest {
    /**
     * 字符换截取加拼接
     */
    @Test
    public void hj4(){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String str = scanner.nextLine();
            if(str.length() < 8){
                str = str + "00000000";
            }
            while(str.length() > 8){
                System.out.println(str.substring(0, 8));
                str = str.substring(8);

            }
        }
    }

    /**
     * 十六进制转化十进制
     */
    @Test
    public void hj5(){
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> map = new HashMap<>(8);
        map.put('a', 10);
        map.put('b', 11);
        map.put('c', 12);
        map.put('d', 13);
        map.put('e', 14);
        map.put('f', 15);
        while(scanner.hasNext()){
            String tempStr = scanner.nextLine();
            if(!tempStr.startsWith("0x")){
                return;
            }
            int des = 0;
            tempStr = tempStr.toLowerCase();
            char[] chars = tempStr.toCharArray();
            for(int i = 2; i < chars.length; i++){
                if(map.containsKey(chars[i])){
                    des = des * 16 + map.get(chars[i]);
                }else{
                    des = des * 16 + chars[i] - '0';
                }

            }
            System.out.println(des);
        }
    }

    /**
     * 质数因子
     */
    @Test
    public void hj6(){
        Scanner scanner = new Scanner(System.in);
        long num = scanner.nextLong();
        int i = 2;
        while(num > 1){
            if(num % i == 0){
                System.out.print(i + " ");
                num = num / i;
            }else{
                i++;
            }
        }
    }

    /**
     * 合并表记录
     */
    @Test
    public void hj8(){
        Scanner scanner = new Scanner(System.in);
        Map<Long, Long> map = new TreeMap<>();
        while(scanner.hasNext()){
            long index = scanner.nextLong();
            for (int i = 0; i < index; i++) {
                long key = scanner.nextLong();
                long value = scanner.nextLong();

                if(map.containsKey(key)){
                    map.put(key, map.get(key) + value);
                }else{
                    map.put(key, value);
                }
            }
            for(Map.Entry<Long, Long> entry : map.entrySet()){
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }
    }

    /**
     * 提取不重复的数字
     */
    @Test
    public void hj9(){
        Scanner in = new Scanner(System.in);
        String tempStr = in.nextLine();
        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<>();
        char[] strArr = tempStr.toCharArray();
        for(int i = strArr.length - 1; i >= 0; i--){
            char charStr = strArr[i];
            if(set.contains(charStr)){
                continue;
            }
            set.add(charStr);
            sb.append(charStr);
        }
        System.out.println(sb.toString());
    }

    /**
     * 字符串反转
     */
    @Test
    public void hj11(){
        Scanner in = new Scanner(System.in);
        String tempStr = in.nextLine();
        StringBuilder stringBuilder = new StringBuilder(tempStr);
        System.out.println(stringBuilder.reverse());
    }

    /**
     * 字符串排序
     */
    @Test
    public void hj14(){
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        String[] strArr = new String[count];
        for(int i = 0; i < count; i++){
            strArr[i] = scanner.next();
        }
        Arrays.sort(strArr);

        for(String str : strArr){
            System.out.println(str);
        }
        scanner.close();
    }

    /**
     * 求int型正整数在内存中存储时1的个数
     */
    @Test
    public void hj15() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int count = 0;
        while(num != 0){
            if((num & 1) == 1){
                count ++;
            }
            num = num >> 1;
        }
        System.out.println(count);
    }

    /**
     *  购物单 动态规划
     */
    @Test
    public void hj16() {
        Scanner scanner = new Scanner(System.in);

    }

    /**
     *  坐标移动
     */
    @Test
    public void hj17() {
        Scanner scanner = new Scanner(System.in);
        String xyStr = scanner.nextLine();
        String[] xyStrArr = xyStr.split(";");
        int x = 0, y = 0;
        for (int i = 0; i < xyStrArr.length; i++) {
            String xyStrTemp = xyStrArr[i];
            //判断坐标是否规范
            if (isCheck(xyStrTemp)) {
                char direct = xyStrTemp.charAt(0);
                int value = Integer.parseInt(xyStrTemp.substring(1));
                switch (direct) {
                    case 'A':
                        x -= value;
                        break;
                    case 'W':
                        y += value;
                        break;
                    case 'S':
                        y -= value;
                        break;
                    case 'D':
                        x += value;
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.println(x + "," + y);
    }

    private boolean isCheck(String xyStrTemp) {
        if(xyStrTemp.length() < 2 || xyStrTemp.length() > 3){
            return Boolean.FALSE;
        }

        switch (xyStrTemp.charAt(0)) {
            case 'A':
            case 'W':
            case 'S':
            case 'D':
                break;
            default:
                return Boolean.FALSE;
        }

        for(int i = 1; i < xyStrTemp.length(); i++){
            if(!Character.isDigit(xyStrTemp.charAt(i))){
                return Boolean.FALSE;
            }
        }

        if(Integer.parseInt(xyStrTemp.substring(1)) < 1 || Integer.parseInt(xyStrTemp.substring(1)) > 99){
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     *   密码验证合格程序
     */
    @Test
    public void hj20() {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String password = in.nextLine();
            if(checkPassWord(password)){
                System.out.println("OK");
            } else{
                System.out.println("NG");
            }
        }


    }

    private boolean checkPassWord(String password) {
        if(password.length() < 8 || password.length() > 100){
            return false;
        }
        int upNum = 0,digNum=0,lowNum=0,special=0;
        for(int i = 0; i < password.length(); i++){
            char charStr = password.charAt(i);
            if(Character.isDigit(charStr)){
                digNum ++;
            }
            if(Character.isUpperCase(charStr)){
                upNum ++;
            }
            if(Character.isLowerCase(charStr)){
                lowNum ++;
            }
            if(!Character.isAlphabetic(charStr) && !Character.isDigit(charStr)  && !Character.isWhitespace(charStr)){
                special ++;
            }
        }
        int count = 0;
        if(upNum > 0){
            count ++;
        }
        if(lowNum > 0){
            count ++;
        }
        if(digNum > 0){
            count ++;
        }
        if(special > 0){
            count ++;
        }
        if(count < 3){
            return false;
        }
        for (int i = 0; i < password.length() - 2; i++) {
            String s1 = password.substring(i, i + 3);
            for (int j = i + 3; j < password.length() - 2; j++) {
                String s2 = password.substring(j, j + 3);
                if(s1.contains(s2)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * HJ21 简单密码
     */
    @Test
    public void hj21(){
        Scanner in = new Scanner(System.in);
        String password = in.nextLine();
        if(password.isEmpty() || password.length() > 100){
            return;
        }
        StringBuilder newPassWord = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            Character charStr = password.charAt(i);
            if(Character.isDigit(charStr)){
                newPassWord.append(charStr);
            }
            if(Character.isLowerCase(charStr)){
                newPassWord.append(getDig(String.valueOf(charStr)));
            }
            if(Character.isUpperCase(charStr)){
                newPassWord.append(getLowChar(String.valueOf(charStr)));
            }

        }
        System.out.println(newPassWord);
    }

    private String getLowChar(String charStr) {
        if("Z".equals(charStr)){
            return "a";
        }
        char res = (char) (charStr.charAt(0) + 1);
        return String.valueOf(res).toLowerCase();
    }

    private int getDig(String charStr) {
        if("abc".contains(charStr)){
            return 2;
        }
        if("def".contains(charStr)){
            return 3;
        }
        if("ghi".contains(charStr)){
            return 4;
        }
        if("jkl".contains(charStr)){
            return 5;
        }
        if("mno".contains(charStr)){
            return 6;
        }
        if("pqrs".contains(charStr)){
            return 7;
        }
        if("tuv".contains(charStr)){
            return 8;
        }if("wxyz".contains(charStr)){
            return 9;
        }
        return 0;
    }

    /**
     * HJ23 删除字符串中出现次数最少的字符
     */
    @Test
    public void hj23(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        if(str.length() < 1 || str.length() > 20){
            return;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            } else{
                map.put(c, 1);
            }
        }
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() < min){
                min = entry.getValue();
            }
        }
        StringBuffer newStr = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if(map.get(c) != min){
                newStr.append(c);
            }
        }
        System.out.println(newStr);
    }


    /**
     *  汽水瓶
     *  思路 整数瓶 空瓶数量 / 3
     *      余数瓶  空瓶数量 % 3
     *     （整数瓶 + 余数瓶） / 3
     *     （整数瓶 + 余数瓶） % 3 值等于2 可以再借一瓶，小于2不可以借
     *
     */
    @Test
    public void hj22() {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            int num = in.nextInt();
            if (num == 0) {
                break;
            }
            System.out.println(num / 2);

        }

    }

    /**
     *  HJ26 字符串排序
     *
     */
    @Test
    public void hj26() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] chars = str.toCharArray();
        List<Character> list = Lists.newArrayList();
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            if(Character.isLetter(chars[i])){
                list.add(chars[i]);
            }else{
                map.put(i,chars[i]);
            }
        }
//        list.sort(Comparator.comparingInt(Character::toLowerCase));
        list.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Character.toLowerCase(o2) - Character.toLowerCase(o1);
            }
        });
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if(map.containsKey(i)){
                sb.append(String.valueOf(map.get(i)));
            }else{
                sb.append(list.get(index));
                index++;
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * HJ31 单词倒排
     */
    @Test
    public void hj31() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] strArr = str.split("[^A-Za-z]");

        for(int i = strArr.length -1; i >= 0; i--) {
            if (!strArr[i].isEmpty()) {
                System.out.print(strArr[i] + " ");
            }
        }

    }

    /**
     * HJ40 统计字符
     */
    @Test
    public void hj40() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int letter = 0;
        int blank = 0;
        int digit = 0;
        int other = 0;
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isAlphabetic(chars[i])) {
                letter++;
            } else if (Character.isDigit(chars[i])) {
                digit++;
            } else if (chars[i] == ' ') {
                blank++;
            } else {
                other++;
            }
        }
        System.out.println(letter);
        System.out.println(blank);
        System.out.println(digit);
        System.out.println(other);

    }


    /**
     * HJ45 统计字符
     */
    @Test
    public void hj45() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            in.nextLine();
            for (int i = 0; i < n; i++) {
                String s = in.nextLine();
                System.out.println(solve(s));
            }

        }
    }
    public int solve(String s) {
        int[] count = new int[26]; // 统计每个字母的出现顺序
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i) - 'a';
            count[ch]++;
        }
        Arrays.sort(count); //将每个字母出现次数由小到大排序
        int ans = 0;
        for (int i = 26; i > 0;
             i--) { //从出现次数最多的字母*最大权值，依次递减
            if (count[i - 1] == 0) break;
            ans += i * count[i - 1];
        }
        return ans;
    }

    /**
     * HJ48 从单向链表中删除指定值的节点
     */
    @Test
    public void hj48() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] arrTemp = str.split(" ");
        int[] arr = new int[arrTemp.length];
        for(int i = 0; i < arr.length; i ++){
            arr[i] = Integer.parseInt(arrTemp[i]);
        }
        int num = arr[0];
        int head = arr[1];
        int delNum = arr[arr.length - 1];
        LinkedList<Integer> list = new LinkedList<>();
        list.add(head);
        for(int i = 2; i <= (num - 1) * 2; i += 2){
            int index = arr[i + 1];
            int nodeNum = arr[i];
            int realIndex = list.indexOf(index);
            list.add(realIndex + 1, nodeNum);
        }

        list.remove(delNum);
        list.forEach( item -> System.out.print(item + " "));

    }

    /**
     * HJ50 四则运算
     */
    @Test
    public void hj50() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        input = input.replace("[", "(");
        input = input.replace("{", "(");
        input = input.replace("}", ")");
        input = input.replace("]", ")");
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("www");
        try {
            System.out.println(scriptEngine.eval(input));
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> res = new ArrayList<>();
    /**
     * HJ55 火车进出站
     */
    @Test
    public void hj55() {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int[] trains = new int[num];
        for(int i = 0; i < num; i ++){
            trains[i] = in.nextInt();
        }
        Stack<Integer> stack = new Stack<>();
        trainOut(trains, 0, stack, "", 0);
//        Collections.sort(res);
        res.forEach(System.out::println);

    }

    public static void trainOut(int[] trains, int in, Stack<Integer> station,
                                String res_temp, int out) {
        if (out == trains.length) {   //out表示已经出站的火车数量。当所有火车出站时，表示一个出站序列完成，将其添加到结果中
            res.add(res_temp);
        }
        if (!station.empty()) {  //当车站还有火车时
            int train = station.pop();  //出站一辆火车
            trainOut(trains, in, station, res_temp + train + " ", out + 1);//该出站火车添加到当前出站序列红，出站火车数量+1
            station.push(train);
        }
        if (in < trains.length) { //当还有火车未进站时
            station.push(trains[in]);//进站一辆火车
            trainOut(trains, in + 1, station, res_temp, out);//已进站火车数量+1
            station.pop();
        }
    }


    /**
     * HJ52 计算字符串的编辑距离
     */
    @Test
    public void hj52() {
        Scanner in = new Scanner(System.in);
        String a = in.nextLine();
        String b = in.nextLine();
        int[][] dp = new int[a.length() + 1][b.length() + 1];
        for (int i = 0; i <= a.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= b.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1) ){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                }
            }
        }
        System.out.println(dp[a.length()][b.length()]);

    }



    /**
     * HJ54 表达式求值
     */
    @Test
    public void hj54() {
        Scanner scanner = new Scanner(System.in);
        String bds = scanner.nextLine();
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            System.out.println(engine.eval(bds));
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * HJ75 公共子串计算
     */
    @Test
    public void hj75() {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        String a = in.nextLine();
        String b = in.nextLine();

        if (a.length() > b.length()) {
            String c = a;
            a = b;
            b = c;
        }
        String result = "";
        for (int i = 0; i < a.length(); i++) {
            for (int j = a.length() ; j > i; j--) {
                if (b.contains(a.substring(i, j)) &&
                        a.substring(i, j).length() > result.length()) {
                    result = a.substring(i, j);
                    break;
                }
            }

        }
        System.out.println(result.length());
    }

    /**
     * HJ83 二维数组操作
     */
    @Test
    public void hj83() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            if(m < 0 || m > 9){
                System.out.println(-1);
            }
            if(n < 0 || n > 9){
                System.out.println(-1);
            }
            int[][] arr = new int[m][n];
            System.out.println(0);
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            if (x1 < m && x2 < m && y1 < n && y2 < n) {
                int tmp = arr[x1][y1];
                arr[x1][y1] = arr[x2][y2];
                arr[x2][y2] = tmp;
                System.out.println(0);
            } else {
                System.out.println(-1);
            }
            int x = scanner.nextInt();
            if (x < m && m<9) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }
            int y = scanner.nextInt();
            if (y < n && n<9) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            if (i < m && i >= 0 && j < n && j >= 0) {
                System.out.println(0);
            } else {
                System.out.println(-1);
            }
        }

    }

    /**
     *  走方格的方案数
     *
     */
    @Test
    public void hj91() {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        System.out.println(dp[m][n]);

    }

    /**
     *  在字符串中找出连续最长的数字串
     *
     */
    @Test
    public void hj92() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            String[] sp = s.split("[^\\d]+");
            int maxLength = 0;
            String result = "";
            for (int i = 0; i < sp.length; i++) {
                if (sp[i].length() > 0 && maxLength < sp[i].length()) {
                    result = sp[i];
                    maxLength = sp[i].length();

                }
            }

            System.out.println(result + "," + maxLength);
        }

    }

    /**
     *   记票统计
     *
     */
    @Test
    public void hj94() {
        Scanner in = new Scanner(System.in);
        int hxNum = in.nextInt();
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < hxNum; i ++){
            map.put(in.next(), 0);
        }
        map.put("Invalid", 0);
        int tpNum = in.nextInt();
        for (int i = 0; i < tpNum; i++) {
            String tp = in.next();
            if(map.containsKey(String.valueOf(tp))){
                map.put(String.valueOf(tp), map.get(String.valueOf(tp)) + 1);
            }else{
                map.put("Invalid", map.get("Invalid") + 1);
            }
        }
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + " : " + stringIntegerEntry.getValue());
        }

    }

    /**
     *  字符统计
     *
     */
    @Test
    public void hj102() {
        Scanner in = new Scanner(System.in);
        TreeMap<Character, Integer> map = new TreeMap<>();

        for(char c : in.nextLine().toCharArray()){
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }else{
                map.put(c, 0);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> {
            if (!Objects.equals(o1.getValue(), o2.getValue())) {
                return o2.getValue() - o1.getValue();
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for(Map.Entry<Character,Integer> entry : list){
            System.out.print(entry.getKey());
        }

    }

    /**
     *  求最小公倍数
     *  思路  计算最大公约数 除以 最小公约数
     */
    @Test
    public void hj108() {
        Scanner in = new Scanner(System.in);
        int num1 = in.nextInt();
        int num2 = in.nextInt();
        int maxNum = num1 * num2;
        //计算最小公约数
        int gcd = gcd(num1, num2);
        System.out.println(maxNum / gcd);

    }

    private int gcd(int num1, int num2) {
        int gcd = 0;
        int maxNum = Math.max(num1, num2);
        int minNum = Math.min(num1, num2);
        gcd = maxNum % minNum;
        if(gcd == 0){
            gcd = minNum;
        }else{
            gcd = gcd(minNum, gcd);
        }
        return gcd;
    }
}
