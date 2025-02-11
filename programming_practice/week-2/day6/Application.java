public class Application {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Sudoku sudoku = new Sudoku();
        sudoku.solve();
        sudoku.printBoard();
        long endTime = System.currentTimeMillis(); // 코드 끝난 시간

        long durationTimeSec = endTime - startTime;
        System.out.println(durationTimeSec + "m/s"); // 밀리세컨드 출력
        System.out.println((durationTimeSec / 1000) + "sec"); // 초 단위 변환 출력
    }

}
