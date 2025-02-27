import java.io.IOException;

public class CLI {

    private final VFSManager manager;

    public CLI(VFSManager manager) {
        this.manager = manager;
    }

    public void run() {
        while (true) {
            String line = Input.prompt();

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                try {
                    manager.deserializeFileSystem();
                } catch (IOException e) {
                    System.out.println("파일 시스템 저장 중 오류가 발생했습니다.");
                }
                break;
            }

            if (line.isEmpty()) continue;
            try {
                Command<VFSManager> command = CommandSolver.parse(line);
                command.execute(manager);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

}
