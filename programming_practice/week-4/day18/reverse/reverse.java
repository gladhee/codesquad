import java.util.*;
import java.io.*;

class Solution {
    public static int reverse(int x) {
        if (x == -2147483648) {
            return 0;
        }
        boolean flag = false;
        if (x < 0) {
            flag = true;
            x *= -1;
        }

        StringBuilder sb = new StringBuilder(String.valueOf(x));
        String revsb = sb.reverse().toString();
        
        long answer = Long.parseLong(revsb);
        if (answer > 2147483647) return 0;

        return flag ? (int)answer * -1 : (int)answer;
    }

	public static void main(String[] args) {

		System.out.println("before : -321");
		System.out.printf("After: %d\n", reverse(-131));
	}
}
