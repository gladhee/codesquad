import java.io.*;
import java.util.*;

public class MetaFileManager {

    // 테이블명 -> 컬럼 리스트
    private final Map<String, List<ColumnDefinition>> schemaMap = new HashMap<>();

    private final File metaFile;

    public MetaFileManager(String metaFilePath) {
        this.metaFile = new File(metaFilePath);
        load();
    }

    // 메타파일 읽어서 schemaMap 초기화
    private void load() {
        if (!metaFile.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(metaFile))) {
            String line;
            while (true) {
                // 테이블명 읽기
                line = readNonEmptyLine(br);
                if (line == null) {
                    break; // EOF
                }
                String tableName = line.trim();
                List<ColumnDefinition> columns = new ArrayList<>();

                // 이어서 컬럼들 읽기(빈 줄 또는 EOF 만날 때까지)
                while (true) {
                    br.mark(10000); // 위치 기억
                    String colLine = br.readLine();
                    if (colLine == null || colLine.trim().isEmpty()) {
                        // 테이블 정의 끝
                        break;
                    }

                    // "singer,String" 형태 -> split
                    String[] parts = colLine.split(",");
                    if (parts.length != 2) {
                        throw new RuntimeException("Invalid column definition: " + colLine);
                    }
                    columns.add(new ColumnDefinition(parts[0].trim(), parts[1].trim()));
                }

                schemaMap.put(tableName, columns);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load meta file: " + e.getMessage(), e);
        }
    }

    // CREATE TABLE
    public void createTable(String tableName, List<ColumnDefinition> columns) {
        if (schemaMap.containsKey(tableName)) {
            throw new RuntimeException("Table '" + tableName + "' already exists in meta.");
        }
        schemaMap.put(tableName, columns);
        save(); // 파일에 쓰기
    }

    // DROP TABLE
    public void dropTable(String tableName) {
        if (!schemaMap.containsKey(tableName)) {
            throw new RuntimeException("Table '" + tableName + "' does not exist in meta.");
        }
        schemaMap.remove(tableName);
        save();
    }

    // 테이블 컬럼 정보 조회
    public List<ColumnDefinition> getColumns(String tableName) {
        List<ColumnDefinition> cols = schemaMap.get(tableName);
        if (cols == null) {
            throw new RuntimeException("Table '" + tableName + "' not found in meta.");
        }
        return cols;
    }

    // readLine() 하되, 공백만 있는 줄은 무시 (테이블명은 빈 줄이 아님)
    private String readNonEmptyLine(BufferedReader br) throws IOException {
        while (true) {
            br.mark(10000);
            String line = br.readLine();
            if (line == null) {
                return null; // EOF
            }
            if (!line.trim().isEmpty()) {
                return line;
            }
        }
    }

    // (2) 메타파일에 schemaMap 저장
    private void save() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(metaFile, false))) {
            // schemaMap 순회
            for (Map.Entry<String, List<ColumnDefinition>> entry : schemaMap.entrySet()) {
                String tableName = entry.getKey();
                List<ColumnDefinition> cols = entry.getValue();
                // 테이블명
                pw.println(tableName);
                // 컬럼들
                for (ColumnDefinition cd : cols) {
                    pw.println(cd.columnName() + "," + cd.dataType());
                }
                pw.println(); // 빈 줄로 테이블 정의 구분
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save meta file: " + e.getMessage(), e);
        }
    }

}