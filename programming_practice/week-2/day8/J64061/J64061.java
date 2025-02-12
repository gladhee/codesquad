import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            answer += grepDoll(board, move, stack);
        }
        
        return answer;
    }
    
    private int grepDoll(int[][] board, int move, Stack<Integer> stack) {
        int ret = 0;
        
        for (int i = 0; i < board.length; ++i) {
            int temp = board[i][move - 1];
            if (temp == 0) {
                continue ;
            }
            
            stack.push(temp);
            ret += removeDoll(stack);
            board[i][move - 1] = 0;
            break;
        }
        
        return ret;
    }
    
    private int removeDoll(Stack<Integer> stack) {
        int ret = 0;
        
        while (true) {
            if (stack.size() < 2) {
                break;
            }
            int a = stack.pop();
            int b = stack.pop();
            if (a != b) {
                stack.push(b);
                stack.push(a);
                break;
            }
            ret += 2;
        }
        
        return ret;
    }
}

