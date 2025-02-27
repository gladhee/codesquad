import java.io.Serializable;

public interface VFSNode extends Serializable {

    void printSubTree(String indent);

    long size();

    boolean isDirectory();

    boolean equals(String name);

}
