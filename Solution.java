import java.util.*;

public class Solution {
    /**
     * 1. Two Sum
     *
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * You can return the answer in any order.
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> valueToIndex = new HashMap<>();
        int i1 = -1, i2 = -1;
        for(int i = 0; i < nums.length; i++){
            int num = nums[i];
            Integer targetSubtractIndex = valueToIndex.get(target - num);
            if(targetSubtractIndex != null){
                i1 = i;
                i2 = targetSubtractIndex;
                break;
            }
            valueToIndex.put(num, i);
        }
        int[] answer = new int[]{i1, i2};
        return answer;
    }

    /**
     * 20. Valid Parentheses
     *
     * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     *
     * An input string is valid if:
     *
     * Open brackets must be closed by the same type of brackets.
     * Open brackets must be closed in the correct order.
     * Every close bracket has a corresponding open bracket of the same type.
     */
    public boolean isValid(String s) {
        StringBuilder stack = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            char peek = stack.length() <= 0 ? 'X' : stack.charAt(stack.length()-1);
            if(c == '(' || c == '[' || c == '{'){
                stack.append(c);
            } else {
                if(c == ')'){
                    if(peek == '('){
                        //valid
                        stack.deleteCharAt(stack.length() - 1);
                    }else{
                        //invalid
                        return false;
                    }
                }
                if(c == ']'){
                    if(peek == '['){
                        //valid
                        stack.deleteCharAt(stack.length() - 1);
                    }else{
                        //invalid
                        return false;
                    }
                }
                if(c == '}'){
                    if(peek == '{'){
                        //valid
                        stack.deleteCharAt(stack.length() - 1);
                    }else{
                        //invalid
                        return false;
                    }
                }
            }

        }
        return stack.length() == 0;
    }

    /**
     *
     * 125. Valid Palindrome
     *
     * A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
     *
     * Given a string s, return true if it is a palindrome, or false otherwise.
     */
    public boolean isPalindrome(String s) {
        StringBuilder pa = new StringBuilder();
        //remove non alphanumeric
        for(int i = 0; i < s.length(); i++){
            char character = Character.toLowerCase(s.charAt(i));
            if((character >= '0' && character <= '9') || (character >= 'a' && character <='z')){
                pa.append(character);
            }
        }
        for(int i = 0; i < pa.length() / 2; i++){
            if(pa.charAt(i) != pa.charAt(pa.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    /**
     * 242. Valid Anagram
     *
     * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     */
    public boolean isAnagram(String s, String t) {
        //an anagram has same charset as original
        if(s.length() != t.length()){
            return false;
        }
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        for(int i = 0; i < sa.length;  i++){
            if(sa[i] != ta[i]){
                return false;
            }
        }
        return true;
    }


    /**
     * 141. Linked List Cycle
     *
     * Given head, the head of a linked list, determine if the linked list has a cycle in it.
     *
     * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
     *
     * Return true if there is a cycle in the linked list. Otherwise, return false.
     */
    //Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        ListNode current = head;
        while(current != null){
            //indicates the node is ever visited
            current.val = Integer.MAX_VALUE;
            if(current.next == null){
                return false;
            } else if(current.next.val == Integer.MAX_VALUE){
                return true;
            }

            current = current.next;


        }
        return false;
    }
    /**
     * 383. Ransom Note
     *
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     *
     * Each letter in magazine can only be used once in ransomNot
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] rnCount = new int[256];
        int[] magCount = new int[256];
        for(int i = 0; i < ransomNote.length(); i++){
            char c = ransomNote.charAt(i);
            rnCount[((int) c) + 128] += 1;
        }
        for(int i = 0; i < magazine.length(); i++){
            char c = magazine.charAt(i);
            magCount[((int) c) + 128] += 1;
        }
        //if ransom has more certain chars than mag
        for(int i = 0; i < 256; i++){
            if(rnCount[i] > magCount[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * 169. Majority Element
     *
     *  Given an array nums of size n, return the majority element.
     *
     * The majority element is the element that appears more than ⌊n / 2⌋ times.
     * You may assume that the majority element always exists in the array.
     */
    public int majorityElement(int[] nums) {
        int times = nums.length / 2 + 1;
        int secondary = times;
        int majority = nums[0];
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(n == majority){
                times--;
            }else{
                secondary--;
                if(times > secondary){
                    majority = n;
                    int temp = times;
                    times = secondary;
                    secondary = temp;
                }
            }

            if(times == 0){
                return majority;
            }
        }
        return majority;
    }

    /**
     * 498. Diagonal Traverse
     *
     * Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.
     */

    public int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length;
        int column = mat[0].length;
        int[] result = new int[row * column];
        //break movement direction (x,y) into x and y
        int rowDirection = 1;
        int columnDirection = -1;
        //current pos
        int icr = -1;
        int jcr = -1;
        final int l = result.length;
        final int imax = row - 1;
        final int jmax = column - 1;
        for(int i =0; i < l; i++){
            int ipos = icr + rowDirection;
            int jpos = jcr + columnDirection;
            boolean iflip = (ipos < 0) || (ipos > imax);
            boolean jflip = (jpos < 0) || (jpos > jmax);
            int move = 1;
            if(iflip){
                jcr++;
            }
            if(jflip){
                icr++;
            }
            //flip direction if hit edge / corner
            if(iflip || jflip){
                rowDirection = -rowDirection;
                columnDirection = -columnDirection;
                //already moved
                move = 0;
            }
            int inext = icr + rowDirection * move;
            int jnext = jcr + columnDirection * move;

            //clamp(0, size - 1)
            if (inext < 0){
                inext = 0;
            }
            if (inext > imax){
                inext = imax;
            }
            if(jnext < 0){
                jnext = 0;
            }
            if (jnext > jmax){
                jnext = jmax;
            }
            icr = inext;
            jcr = jnext;
            result[i] = mat[icr][jcr];

        }
        return result;
    }
    /**
     * 482. License Key Formatting
     *
     * You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
     * The string is separated into n + 1 groups by n dashes. You are also given an integer k.
     *
     * We want to reformat the string s such that each group contains exactly k characters, except for the first group,
     * which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.
     *
     * Return the reformatted license key.
     */
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder reformat = new StringBuilder(s.length());
        int kCount = 0;
        for(int i = s.length() - 1; i >= 0; i--){
            char c = s.charAt(i);
            if(c != '-'){
                reformat.append(Character.toUpperCase(c));
                kCount++;
            }
            if(kCount == k){
                kCount = 0;
                if(i != 0){
                    reformat.append('-');
                }
            }
        }
        if(reformat.length() > 0 && reformat.charAt(reformat.length() - 1) == '-'){
            reformat.deleteCharAt(reformat.length() - 1);
        }
        return reformat.reverse().toString();
    }

    /**
     *
     * 347. Top K Frequent Elements
     *
     * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
     */

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> valueToCountMap = new HashMap<>();
        for(int n : nums){
            Integer c = valueToCountMap.get(n);
            int ci = c == null ? 0 : c;
            valueToCountMap.put(n, ci + 1);
        }
        return valueToCountMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(k)
                .mapToInt(Integer::intValue)
                .toArray();
    }

    /**
     * 49. Group Anagrams
     *
     * Given an array of strings strs, group the anagrams together. You can return the answer in any order.
     *
     * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
     * typically using all the original letters exactly once.
     */

    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> anagramGroups = new HashMap<>();
        for(String s : strs){
            char[] sortedChars = s.toCharArray();
            Arrays.sort(sortedChars);
            String anagram = new String(sortedChars);
            List<String> group = anagramGroups.get(anagram);
            if(group == null){
                group = new ArrayList<>();
                anagramGroups.put(anagram, group);
            }
            group.add(s);
        }
        return new ArrayList(anagramGroups.values());
    }
    /**
     * 217. Contains Duplicate
     *
     * Given an integer array nums,
     * return true if any value appears at least twice in the array, and return false if every element is distinct.
     */

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for(int n : nums){
            if(!set.add(n)){
                return true;
            }
        }
        return false;
    }

    /**
     * 121. Best Time to Buy and Sell Stock
     *
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     *
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     *
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     */
    public int maxProfit(int[] prices) {
        int l = prices.length;
        //int i = 0;

        //two pointer
        int scout = 0;
        int buy = Integer.MAX_VALUE;
        //int sell = 0;
        int profit = 0;
        while(scout < l){

            int p = prices[scout];
            //if scout find a better buy price
            if(p < buy){
                buy = p;
            }
            int newProfit = p - buy;
            if(newProfit > profit){
                profit = newProfit;
            }
            scout++;
        }
        return profit;
    }
    /**
     * 344. Reverse String
     * Write a function that reverses a string. The input string is given as an array of characters s.
     *
     * You must do this by modifying the input array in-place with O(1) extra memory.
     */

    public void reverseString(char[] s) {
        //two pointer
        int i = 0;
        int j = s.length - 1;
        while(i < j){
            char c1 = s[i];
            char c2 = s[j];
            s[i] = c2;
            s[j] = c1;
            i++;
            j--;
        }
    }
    /**
     * 3. Longest Substring Without Repeating Characters
     *
     * Given a string s, find the length of the longest
     * substring
     *  without repeating characters.
     */

    public int lengthOfLongestSubstring(String s) {
        int sl = s.length();
        if(sl == 0 || sl == 1){
            return sl;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        //find min-max char code
        for(int i = 0; i < sl; i++){
            int c = ((int) s.charAt(i)) + 128;
            if(c > max){
                max = c;
            }
            if(c < min){
                min = c;
            }
        }
        //index is char [min~max]
        int[] map = new int[max - min + 1];
        Arrays.fill(map, -1);
        map[s.charAt(0) + 128 - min] = 0;

        //two pointer
        int left = 0;
        int maxlen = 1;
        //int charOfStart = s.charAt(0);
        for(int i = 1; i < sl; i++){
            int c = ((int) s.charAt(i)) + 128 - min;
            int occur = map[c];
            if(occur >= left){
                left = occur + 1;
            }
            map[c] = i;
            int len = i - left + 1;
            if(len > maxlen){
                maxlen = len;
            }
        }
        return maxlen;
    }

    /**
     * 219. Contains Duplicate II
     *
     * Given an integer array nums and an integer k,
     * return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
     */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>((int)(nums.length / 0.75) + 1);
        for(int i = 0; i< nums.length; i++){
            int n = nums[i];
            Integer p = map.get(n);
            int distance = p == null ? Integer.MAX_VALUE : Math.abs(p - i);
            if (distance <= k){
                return true;
            }
            map.put(n, i);
        }
        return false;
    }

    /**
     * 560. Subarray Sum Equals K
     *
     * Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     */

    public int subarraySum(int[] nums, int k) {
        if(nums.length == 1){
            return nums[0] == k ? 1 : 0;
        }
        //int[] prefixSums = new int[nums.length];
        int acc = 0;
        int kCount = 0;
        HashMap<Integer, Integer> subCount = new HashMap<>();
        subCount.put(0, 1);
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            acc += n;

            Integer c1 = subCount.get(acc - k);
            //Integer c2 = subCount.get(k - acc);
            kCount += (c1 == null ? 0 : c1);

            Integer count = subCount.get(acc);
            subCount.put(acc, count == null ? 1 : count + 1);
            //kCount += (c2 == null ? 0 : c2);

        }
        return kCount;
    }
    /**
     *
     * 1967. Number of Strings That Appear as Substrings in Word
     *
     * Given an array of strings patterns and a string word, return the number of strings in patterns that exist as a substring in word.
     *
     * A substring is a contiguous sequence of characters within a string.
     */

    public int numOfStrings(String[] patterns, String word) {
        int count = 0;
        for(String p: patterns){
            if(word.contains(p)){
                count++;
            }
        }
        return count;
    }

    /**
     * 767. Reorganize String
     *
     * Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
     *
     * Return any possible rearrangement of s or return "" if not possible.
     */

    static class Entry {
        public int count;
        public char c;

        public Entry(int count, char c) {
            this.count = count;
            this.c = c;
        }
    }

    public String reorganizeString(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer co = map.get(c);
            int count = co == null ? 1 : co + 1;

            //cannot be reorganized
            if (count > s.length() / 2 + s.length() % 2) {
                return "";
            }

            map.put(c, count);
        }
        char[] chars = new char[s.length()];

        //desc order
        PriorityQueue<Entry> pq = new PriorityQueue<>(
                new Comparator<Entry>() {
                    @Override
                    public int compare(Entry a, Entry b) {
                        return Integer.compare(b.count, a.count);
                    }
                });
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            pq.offer(new Entry(entry.getValue(), entry.getKey()));
        }
        char last = '!';
        for (int i = 0; i < chars.length; i++) {
            Entry e = pq.poll();
            if (e.c == last) {
                Entry e2 = pq.poll();
                if (e2 != null) {
                    last = e2.c;
                    e2.count--;
                    if (e2.count > 0) {
                        pq.offer(e2);
                    }
                }
                pq.offer(e);

            } else {
                last = e.c;
                e.count--;
                if (e.count > 0) {
                    pq.offer(e);
                }
            }
            chars[i] = last;

        }
        return new String(chars);
    }
    /**
     * 1464. Maximum Product of Two Elements in an Array
     *
     * Given the array of integers nums, you will choose two different indices i and j of that array.
     * Return the maximum value of (nums[i]-1)*(nums[j]-1).
     *
     */

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondary = max;
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(n > max){
                secondary = max;
                max = n;
            }else if(n > secondary){
                secondary = n;
            }
        }
        return (max - 1) * (secondary - 1);
    }

}
