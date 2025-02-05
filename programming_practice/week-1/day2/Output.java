public class Output {

    public void printResult(Player A, Player B) {
        StringBuilder sb = new StringBuilder();

        sb.append(A);

        if (A.getScore() > B.getScore()) {
            sb.append(">");
        } else if (A.getScore() < B.getScore()) {
            sb.append("<");
        } else {
            sb.append("=");
        }
        sb.append(B);

        System.out.println(sb);
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }

}
