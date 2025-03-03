import java.time.LocalTime;

public class HangulClock {

    private static final String[][] grid = {
            {"오", "전", "후", "영"},
            {"열", "한", "두", "세"},
            {"네", "다", "여", "섯"},
            {"일", "곱", "여", "덟"},
            {"아", "홉", "시", "\uD83C\uDF19"},
            {"이", "삼", "사", "오"},
            {"십", "일", "이", "삼"},
            {"사", "오", "육", "칠"},
            {"팔", "구", "분", "초"}
    };

    public static String render(TimeRepresentation time) {
        StringBuilder sb = new StringBuilder();
        final String bold = "\u001B[1m";
        final String dim = "\u001B[2m";
        final String reset = "\u001B[0m";

        String[][] renderGrid = new String[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            renderGrid[i] = grid[i].clone();
        }
        int currentHour = LocalTime.now().getHour();
        renderGrid[4][3] = (currentHour >= 7 && currentHour < 19) ? "☀️" : "🌙";
        renderGrid[8][3] = "[s]" + time.second + "[/s]";

        for (int r = 0; r < renderGrid.length; r++) {
            for (int c = 0; c < renderGrid[r].length; c++) {
                String cell = renderGrid[r][c];
                boolean highlight = false;
                switch (r) {
                    case 0:
                        if (time.ampm.equals("오전") && (cell.equals("오") || cell.equals("전"))) {
                            highlight = true;
                        } else if (time.ampm.equals("오후") && (cell.equals("오") || cell.equals("후"))) {
                            highlight = true;
                        }
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        if (time.hour.contains(cell)) highlight = true;
                        break;
                    case 6:
                        if (time.minuteTens.contains(cell)) highlight = true;
                        break;
                    case 7:
                        if (time.minuteOnes.contains(cell)) highlight = true;
                        break;
                    case 8:
                        if (cell.equals("분")) {
                            highlight = true;
                        }
                    default:
                        break;
                }
                if (highlight) {
                    sb.append(bold).append(cell).append(reset);
                } else {
                    sb.append(dim).append(cell).append(reset);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
