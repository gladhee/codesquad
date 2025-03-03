import java.time.LocalTime;

public class Application {

    private static final String ANSI_CLEAR_SCREEN = "\u001B[H\u001B[2J";
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread detectorThread = new Thread(Application::detectSecondChange);
        detectorThread.setDaemon(true);
        detectorThread.start();

        while (true) {
            synchronized (LOCK) {
                LOCK.wait();
            }
            clearConsole();
            TimeRepresentation time = TimeRepresentation.now();
            String output = HangulClock.render(time);
            System.out.print(output);
        }
    }

    private static void detectSecondChange() {
        int lastSecond = -1;

        while (true) {
            LocalTime now = LocalTime.now();
            int currentSecond = now.getSecond();

            if (currentSecond != lastSecond) {
                // 초가 바뀌었다면 메인 스레드에게 알림
                lastSecond = currentSecond;
                synchronized (LOCK) {
                    LOCK.notify();
                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private static void clearConsole() {
        System.out.print(ANSI_CLEAR_SCREEN);
        System.out.flush();
    }

}