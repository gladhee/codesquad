import java.util.*;
import java.io.*;

class Solution {
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        List<char[]> chrr = new ArrayList<>();
        int minLength = strs[0].length();
        for (String str : strs) {
            chrr.add(str.toCharArray());
            minLength = Math.min(minLength, str.length());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minLength; i++) {
            char current = chrr.get(0)[i];
            for (char[] ch : chrr) {
                if (ch[i] != current) {
                    return sb.toString();
                }
            }
            sb.append(current);
        }
        return sb.toString();
    }

	public static void main(String[] argv) {
		String[] strs = {"cars", "carrs", "carsss"};
		System.out.println("input");
		StringBuilder sb = new StringBuilder();
		for (String str : strs) {
			sb.append(str);
			sb.append(", ");
		}
		System.out.println(sb);
		System.out.println("\noutput");
		System.out.println(longestCommonPrefix(strs));
	}
}
