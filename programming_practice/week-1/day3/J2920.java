import java.io.*;

public class J2920 {

    public static void main(String[] args) throws IOException {
        solution();
    }

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String arr = br.readLine();

        if (arr.equals("1 2 3 4 5 6 7 8")) {
            bw.write("ascending");
        } else if (arr.equals("8 7 6 5 4 3 2 1")) {
            bw.write("descending");
        } else {
            bw.write("mixed");
        }

        bw.flush();
        bw.close();
        br.close();
    }

}
