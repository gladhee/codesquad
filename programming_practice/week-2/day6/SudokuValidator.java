public class SudokuValidator {

    private static final int SIZE = 9;
    private static final int BLOCK_SIZE = 3;

    private SudokuValidator() {
    }

    public static boolean isValid(int[][] board, int row, int col, int num) {
        // col 검증
        for (int j = 0; j < SIZE; j++) {
            if (board[row][j] == num) {
                return false;
            }
        }
        // row 검증
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        // 3x3 block 검증
        int blockRowStart = (row / BLOCK_SIZE) * BLOCK_SIZE;
        int blockColStart = (col / BLOCK_SIZE) * BLOCK_SIZE;
        for (int i = 0; i < BLOCK_SIZE; i++) {
            for (int j = 0; j < BLOCK_SIZE; j++) {
                if (board[blockRowStart + i][blockColStart + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }



//    public static boolean isValidBLOCK_SIZExBLOCK_SIZESquare(int[][] sudoku, int row, int column) {
//
//        return false;
//    }

}
