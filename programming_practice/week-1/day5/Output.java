public class Output {

    public void printError(String message) {
        System.out.println(message);
    }

    public void printTurnResult(TurnResult turnResult) {
        if (turnResult.toString().isEmpty()) {
            return;
        }
        System.out.println(turnResult);
    }

    public void printWinner(TurnResult turnResult) {
        System.out.println("승자: " + turnResult.putWinner());
    }

}
