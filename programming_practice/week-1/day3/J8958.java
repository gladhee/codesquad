import java.io.*;

public class J8958 {

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String arr = br.readLine();
            int score = 0;
            int sum = 0;
            for (int j = 0; j < arr.length(); j++) {
                if (arr.charAt(j) == 'O') {
                    score++;
                    sum += score;
                } else {
                    score = 0;
                }
            }
            bw.write(sum + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
