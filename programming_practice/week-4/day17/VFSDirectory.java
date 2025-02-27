import java.util.ArrayList;
import java.util.List;

public class VFSDirectory implements VFSNode {

    private final String name;
    private final VFSDirectory parent;
    private final List<VFSNode> children;

    public VFSDirectory(String name, VFSDirectory parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public void add(VFSNode node) {
        children.add(node);
    }

    public VFSNode findChild(String name) {
        for (VFSNode node : children) {
            if (node.equals(name)) {
                return node;
            }
        }

        throw new IllegalStateException("No such file or directory");
    }

    public void listContents() {
        if (children.isEmpty()) {
            System.out.println("Directory is empty");
            return;
        }
        for (VFSNode node : children) {
            System.out.println(node);
        }
    }

    public String showFullPath() {
        if (parent == null) {
            return name;
        }
        return parent.showFullPath() + "/" + name;
    }

    @Override
    public void printSubTree(String indent) {
        System.out.println(indent + name);
        for (VFSNode node : children) {
            node.printSubTree(indent + "  ");
        }
    }

    @Override
    public long size() {
        long size = 0;
        for (VFSNode node : children) {
            size += node.size();
        }
        return size;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean equals(String name) {
        return this.name.equals(name);
    }

}
