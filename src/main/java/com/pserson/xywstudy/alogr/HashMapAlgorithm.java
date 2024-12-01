package com.pserson.xywstudy.alogr;

import java.util.*;

/**
 * hashmap算法
 */
public class HashMapAlgorithm {

    public static void main(String[] args) {

    }

    /**
     * 560. 和为K的子数组 leetcode 560
     * @param nums
     * @param k
     * @return
     */
    public int lt_560_subarraySum(int[] nums, int k) {
        int total = 0;
        if(nums == null || nums.length == 0){
            return total;
        }
        //key为前缀和 value为出现次数
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 1; i < nums.length; i++){
            nums[i] = nums[i] + nums[i - 1];
        }
        for (int num : nums) {
            if (map.containsKey(num - k)) {
                total += map.get(num - k);
            }
            int temp = map.containsKey(num) ? map.get(num) + 1 : 1;
            map.put(num, temp);
        }

        return total;

    }

    /**
     * 133. 克隆图 leetcode 133
     * @param node
     * @return
     */
    public static Node lt_133_cloneGraph(Node node) {
        if(node == null){
            return null;
        }

        List<Node> nodes = getAllNodes(node);
        Map<Node, Node> map = new HashMap<>();
        //拷贝所有节点
        nodes.forEach(temp -> {
            Node newNode = new Node(temp.val);
            map.put(temp, newNode);
        });
        //拷贝每个节点对应的临节点
        for (Node item : nodes) {
            Node newNode = map.get(item);
            for (Node neighborNode : item.neighbors) {
                Node newNeighbors = map.get(neighborNode);
                newNode.neighbors.add(newNeighbors);
            }
        }
        return map.get(node);
    }

    private static List<Node> getAllNodes(Node node) {
        List<Node> result = new ArrayList<>();
        Set<Node> set = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        set.add(node);
        while(!queue.isEmpty()){
            Node tempNode = queue.poll();
            for (Node neighbor : tempNode.neighbors) {
                if(!set.contains(neighbor)){
                    set.add(neighbor);
                    queue.add(neighbor);
                }
            }

        }
        return new ArrayList<>(set);
    }

    /**
     * 3. 无重复字符的最长子串 leetcode 3
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public static int lt_3_lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        while(right < s.length()){
            Character c = s.charAt(right);
            if(!set.contains(c)){
                set.add(c);
                ans = Math.max(ans, set.size());
                right ++;
            }else{
                set.remove(s.charAt(left));
                left ++;
            }
        }

        return ans;
    }

    /**
     * 3. 无重复字符的最长子串 leetcode 3
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * HashMap实现
     * @param s
     * @return
     */
    public static int lt_3_HashMapLengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        //true表示出现、false表示未出现
        Map<Character, Boolean> map = new HashMap<>();
        //遍历的位置
        int index = 0;
        for (int i = 0; i < s.length(); i++) {

            while(index < s.length() && (!map.containsKey(s.charAt(index)) || !map.get(s.charAt(index)))){
                map.put(s.charAt(index), true);
                ans = Math.max(ans, index - i + 1);
                index ++;
            }
            map.put(s.charAt(i), false);
        }

        return ans;
    }

    /**
     * 3. 无重复字符的最长子串 leetcode 3
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 数组实现
     * @param s
     * @return
     */
    public static int lt_3_ArrayLengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int ans = 0;
        //true表示出现、false表示未出现
        int[] cArr = new int[256];
        //遍历的位置
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            while(index < s.length() && cArr[s.charAt(index)] == 0){
                cArr[s.charAt(index)] = 1;
                ans = Math.max(ans, index - i + 1);
                index ++;
            }
            cArr[s.charAt(i)] = 0;
        }
        return ans;
    }

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

}
