import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandSolver {

    private static final Pattern pattern = Pattern.compile("\"([^\"]*)\"|(\\S+)");

    public static Command<VFSManager> parse(String line) {
        List<String> tokens = tokenize(line);
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("명령을 입력해 주세요.");
        }
        String commandName = tokens.removeFirst();
        return switch (commandName) {
            case "list" -> listCommand(tokens);
            case "makedir" -> createDirectoryCommand(tokens);
            case "create" -> createFileCommand(tokens);
            case "read" -> readCommand(tokens);
            case "export" -> exportCommand(tokens);
            case "import" -> importCommand(tokens);
            default -> throw new IllegalArgumentException("알 수 없는 명령: " + commandName);
        };
    }

    private static List<String> tokenize(String input) {
        List<String> tokens = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            // 그룹 1: 따옴표 안의 내용, 그룹 2: 일반 토큰
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1));
            } else {
                tokens.add(matcher.group(2));
            }
        }
        return tokens;
    }

    private static Command<VFSManager> listCommand(List<String> argv) {
        return manager -> {
            if (argv.size() != 1) {
                throw new IllegalArgumentException("Usage: list [directory]");
            } else {
                manager.listDirectory(argv.getFirst());
            }
        };
    }

    private static Command<VFSManager> createDirectoryCommand(List<String> argv) {
        return manager -> {
            if (argv.size() != 2) {
                throw new IllegalArgumentException("Usage: makedir [currentPath] [newDirName]");
            }
            manager.createDirectory(argv.getFirst(), argv.getLast());
        };
    }

    private static Command<VFSManager> createFileCommand(List<String> argv) {
        return manager -> {
            if (argv.size() != 3) {
                throw new IllegalArgumentException("Usage: create [directoryPath] [fileName] [data]");
            }
            manager.createFile(argv.getFirst(), argv.get(1), argv.getLast().getBytes());
        };
    }

    private static Command<VFSManager> readCommand(List<String> argv) {
        return manager -> {
            if (argv.size() != 2) {
                throw new IllegalArgumentException("Usage: read [filePath]");
            }
            manager.readTextFile(argv.getFirst());
        };
    }

    private static Command<VFSManager> exportCommand(List<String> argv) {
        return manager -> {
            if (argv.size() != 2) {
                throw new IllegalArgumentException("Usage: export [filePath] [exportPath]");
            }
            manager.exportFile(argv.getFirst(), argv.getLast());
        };
    }

    private static Command<VFSManager> importCommand(List<String> argv) {
        return manager -> {
            if (argv.size() != 2) {
                throw new IllegalArgumentException("Usage: import [importPath] [vfsPath]");
            }
            manager.importFile(argv.getFirst(), argv.getLast());
        };
    }

}
