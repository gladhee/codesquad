import java.util.List;

public class Sudoku {

    private static final int START = 0;
    private static final int EMPTY = 0;
    private static final int SIZE = 9;
    private static final int BLOCK_SIZE = 3;
    private static final int TERMINATE = 9;

    private final int[][] board;

    public Sudoku() {
        this.board = initSudoku();
    }

    // 게임판 생성
    public int[][] initSudoku() {
        int[][] sudoku = new int[SIZE][SIZE];

        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                sudoku[i][j] = EMPTY;
            }
        }

        return sudoku;
    }

    public void solve() {
        solveBlock(START);
    }

    /**
     * blockIndex: 0부터 8까지의 3×3 블록 번호
     * 블록 순서는 좌상단(0) → 오른쪽(1) → ... → 우하단(8) 입니다.
     */
    private boolean solveBlock(int blockIndex) {
        if (blockIndex == TERMINATE) {
            return true;
        }
        int startRow = (blockIndex / BLOCK_SIZE) * BLOCK_SIZE;
        int startCol = (blockIndex % BLOCK_SIZE) * BLOCK_SIZE;

        return fillBlockCells(startRow, startCol, 0, blockIndex);
    }

    /**
     * startRow, startCol: 현재 3×3 블록의 좌측 상단 좌표
     * cellIndex: 블록 내 셀 인덱스 (0 ~ 8)
     * blockIndex: 현재 블록 번호
     */
    private boolean fillBlockCells(int startRow, int startCol, int cellIndex, int blockIndex) {
        if (cellIndex == TERMINATE) {
            // 현재 블록의 모든 셀을 채웠으면 다음 블록으로 이동
            return solveBlock(blockIndex + 1);
        }
        // 블록 내의 행, 열 계산
        int row = startRow + cellIndex / BLOCK_SIZE;
        int col = startCol + cellIndex % BLOCK_SIZE;

        // 이미 값이 채워진 셀은 건너뛰기
        if (board[row][col] != EMPTY) {
            return fillBlockCells(startRow, startCol, cellIndex + 1, blockIndex);
        }

        // 1부터 9까지의 숫자를 랜덤하게 섞어서 시도
        List<Integer> numbers = NumberGenerator.pickUniqueNumbersInRange(1, 9, 9);
        for (int num : numbers) {
            // 해당 자리에 숫자가 들어갈 수 있는지 검사
            if (SudokuValidator.isValid(board, row, col, num)) {
                board[row][col] = num;
                if (fillBlockCells(startRow, startCol, cellIndex + 1, blockIndex)) {
                    return true;  // 성공
                }
                board[row][col] = 0;  // 실패
            }
        }
        // 가능한 숫자를 모두 시도했으나 성공하지 못하면 상위 단계로 되돌아감
        return false;
    }

    // 게임판 출력
    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
