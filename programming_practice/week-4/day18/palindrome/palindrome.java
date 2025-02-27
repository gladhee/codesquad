import java.util.*;
import java.io.*;

class Solution {
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        String str = String.valueOf(x);
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length / 2; i++) {
            if (ch[i] == ch[ch.length - 1 - i]) continue;
            return false;
        }

        return true;
    }

	public static void main(String[] args) {
		System.out.printf("3321233 is %s\n", isPalindrome(3321233));
	}
}
