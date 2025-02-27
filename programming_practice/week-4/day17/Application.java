import java.io.IOException;

public class Application {

    private static final String VFS_DATA_FILE = "vfs.dat";

    public static void main(String[] args) {
        VFSManager manager = setUp();
        CLI cli = new CLI(manager);
        cli.run();
        Input.close();
    }

    private static VFSManager setUp() {
        VFSManager manager;
        try {
            manager = VFSManager.serializeFileSystem(VFS_DATA_FILE);
            System.out.println("파일 시스템을 불러왔습니다.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("저장된 파일 시스템이 없습니다.");
            System.out.println("파일 시스템의 최대 크기를 입력해 주세요.");
            long maxSize = parseSize();
            manager = new VFSManager(maxSize);
            System.out.println(maxSize + " 파일 시스템의 초기화를 완료했습니다.");
        }
        return manager;
    }

    private static long parseSize() {
        while (true) {
            try {
                String sizeStr = Input.prompt().trim().toUpperCase();
                long parseLong = Long.parseLong(sizeStr.substring(0, sizeStr.length() - 1));
                if (sizeStr.endsWith("M")) {
                    return parseLong * 1024 * 1024;
                } else if (sizeStr.endsWith("K")) {
                    return parseLong * 1024;
                } else {
                    return Long.parseLong(sizeStr);
                }
            } catch (NumberFormatException e) {
                System.out.println("올바른 크기를 입력해 주세요.");
            }
        }
    }

}
