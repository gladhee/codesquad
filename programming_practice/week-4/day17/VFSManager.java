import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VFSManager implements Serializable {

    private final String filePath;
    private final VFSDirectory root;
    private final long maxSize;
    private long currentSize;

    public VFSManager(long maxSize) {
        this.filePath = "vfs.dat";
        this.root = new VFSDirectory("/", null);
        this.maxSize = maxSize;
        this.currentSize = 0;
    }

    // 경로 해석: "/"이면 루트를 반환하고, 그 외는 각 디렉토리를 순차 검색
    public VFSNode resolvePath(String path) {
        if (path.equals("/")) return root;
        String[] parts = path.split("/");
        VFSNode current = root;
        for (int i = 1; i < parts.length; i++) {
            if (current instanceof VFSDirectory) {
                current = ((VFSDirectory) current).findChild(parts[i]);
            } else {
                throw new IllegalStateException("경로의 중간에 파일이 있습니다: " + parts[i]);
            }
        }

        return current;
    }

    public void createFile(String directoryPath, String fileName, byte[] data) {
        VFSNode node = resolvePath(directoryPath);
        if (!node.isDirectory()) {
            throw new IllegalStateException("디렉토리 경로가 아닙니다: " + directoryPath);
        }
        if (currentSize + data.length > maxSize) {
            throw new IllegalStateException("공간 부족: 남은 공간 " + (maxSize - currentSize) + " byte");
        }
        VFSDirectory dir = (VFSDirectory) node;
        dir.findChild(fileName);
        dir.add(new VFSFile(fileName, dir, data));
        currentSize += data.length;
    }

    public void createDirectory(String currentPath, String newDirName) {
        VFSNode node = resolvePath(currentPath);
        if (!node.isDirectory()) {
            throw new IllegalStateException("디렉토리가 아닙니다: " + currentPath);
        }
        VFSDirectory dir = (VFSDirectory) node;
        dir.findChild(newDirName);
        dir.add(new VFSDirectory(newDirName, dir));
    }

    public void listDirectory(String directoryPath) {
        VFSNode node = resolvePath(directoryPath);
        if (!node.isDirectory()) {
            throw new IllegalStateException("디렉토리 경로가 아닙니다: " + directoryPath);
        }
        VFSDirectory dir = (VFSDirectory) node;
        dir.listContents();
        printSpaceInfo();
    }

    public String readTextFile(String filePath) {
        VFSNode node = resolvePath(filePath);
        if (node.isDirectory()) {
            throw new IllegalStateException("디렉토리는 읽을 수 없습니다: " + filePath);
        }
        VFSFile file = (VFSFile) node;
        return file.readContent();
    }

    public static VFSManager serializeFileSystem(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            VFSManager vfs = (VFSManager) ois.readObject();
            System.out.println("VFS가 " + filePath + "에서 불러와졌습니다.");
            return vfs;
        }
    }

    // 가상 파일 시스템 전체 구조 출력 (디버깅용)
    public void printStructure() {
        root.printSubTree("/");
    }

    // 실제 VFS 상태를 파일로 export (직렬화)
    public void deserializeFileSystem() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(this);
            System.out.println("VFS가 " + filePath + "에 저장되었습니다.");
        }
    }

    // 실제 파일 시스템의 파일을 VFS에 import
    public void importFile(String vfsDirectory,String realFilePath) throws IOException {
        Path path = Paths.get(realFilePath);
        byte[] data = Files.readAllBytes(path);
        createFile(vfsDirectory, fileName, data);
        System.out.printf("%s 디렉토리 아래 %s 파일을 import 했습니다. 여유 공간: %d byte\n",
                vfsDirectory, fileName, maxSize - currentSize);
    }

    // VFS의 파일을 실제 파일 시스템으로 export
    public void exportFile(String vfsFilePath, String realFilePath) throws IOException {
        VFSNode node = resolvePath(vfsFilePath);
        if (node.isDirectory()) {
            throw new IllegalStateException("디렉토리는 export 할 수 없습니다: " + vfsFilePath);
        }
        VFSFile file = (VFSFile) node;
        Path dest = Paths.get(realFilePath);
        Files.write(dest, file.size() > 0 ? file.readContent().getBytes() : new byte[0]);  // 텍스트 파일인 경우
        // 또는, 파일이 바이너리라면 아래와 같이:
//        file.exportToFile(dest);
        System.out.printf("%s 파일이 %s에 export 되었습니다.\n", vfsFilePath, realFilePath);
    }

    // 공간 정보 출력
    public void printSpaceInfo() {
        System.out.println("총 용량: " + maxSize + " byte, 사용 중: " + currentSize +
                " byte, 남은: " + (maxSize - currentSize) + " byte");
    }

}
