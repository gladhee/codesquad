import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        
        int[] pick1 = {1, 2, 3, 4, 5};
        int[] pick2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pick3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int score1 = 0;
        int score2 = 0;
        int score3 = 0;
        
        for (int i = 0; i < answers.length; ++i) {
            
            if (answers[i] == pick1[i % pick1.length]) {
                 score1 += 1;
            }
            
            if (answers[i] == pick2[i % pick2.length]) {
                score2 += 1;
            }
            
            if (answers[i] == pick3[i % pick3.length]) {
                score3 += 1;
            }
            
        }
        
        int maxScore = Math.max(score1, Math.max(score2, score3));
        
        List<Integer> answer = new ArrayList<>();
        if (score1 == maxScore) max.add(1);
        if (score2 == maxScore) max.add(2);
        if (score3 == maxScore) max.add(3);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}

