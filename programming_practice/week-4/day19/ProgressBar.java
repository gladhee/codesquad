public class ProgressBar {
    private final long totalDuration;
    private final int barWidth;
    private final long startTime;

    public ProgressBar(long totalDuration, int barWidth) {
        this.totalDuration = totalDuration;
        this.barWidth = barWidth;
        this.startTime = System.currentTimeMillis();
    }

    // "화성까지 여행" 레이블과 퍼센티지, 진행바를 한 줄에 출력
    public void display() {
        long elapsed = System.currentTimeMillis() - startTime;
        double fraction = (double) elapsed / totalDuration;
        if (fraction > 1.0) {
            fraction = 1.0;
        }
        int percentage = (int)(fraction * 100);
        int progressUnits = (int)(fraction * barWidth);
        StringBuilder sb = new StringBuilder();
        sb.append("화성까지 여행 ").append(String.format("%3d%% ", percentage));
        sb.append("[");
        for (int i = 0; i < barWidth; i++) {
            sb.append(i < progressUnits ? "▓" : " ");
        }
        sb.append("]");
        System.out.print("\r" + sb.toString());
    }

    public boolean isComplete() {
        return System.currentTimeMillis() - startTime >= totalDuration;
    }
}