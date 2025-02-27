import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class VFSFile implements VFSNode {

    private final String name;
    private final VFSDirectory parent;
    private final byte[] data;

    public VFSFile(String name, VFSDirectory parent, byte[] data) {
        this.name = name;
        this.parent = parent;
        this.data = data;
    }

    public String readContent() {
        return new String(data);
    }

    public void exportToFile(Path path) throws IOException {
        Files.write(path, data);
    }

    @Override
    public void printSubTree(String indent) {
        System.out.println(indent + name);
    }

    @Override
    public long size() {
        return data.length;
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean equals(String name) {
        return this.name.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }

}
