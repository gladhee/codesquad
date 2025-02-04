public class Convertor {

    public boolean[] dec2bin(int decimal) {
        boolean[] answer;
        int size = 0;

        for (int i = decimal; i > 0; i /= 2) {
            size++;
        }

        answer = new boolean[size];

        for (int i = 0; i < size; ++i) {
            answer[i] = ((decimal & 1) == 1);
            decimal >>= 1;
        }

        return answer;
    }

    public int bin2dec(boolean[] binary) {
        int answer = 0;

        for (int i = binary.length; i > 0; i--) {
            if (binary[i - 1]) {
                answer += 1;
            }
            if (i != 1) {
                answer <<= 1;
            }
        }

        return answer;
    }

}
