package org.mdir;

import javax.swing.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public class FileHandler {

    /**
     * @param file      읽을 파일 객체
     * @param onSuccess 파일 읽기에 성공했을 때 텍스트 내용을 넘겨주는 콜백
     * @param onError   오류 발생 시 예외를 넘겨주는 콜백
     */
    public void readFileAsync(File file, Consumer<String> onSuccess, Consumer<Exception> onError) {
        new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
                Path path = file.toPath();
                List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
                return String.join("\n", lines);
            }

            @Override
            protected void done() {
                try {
                    onSuccess.accept(get()); // 파일 내용을 콜백에 전달
                } catch (Exception e) {
                    onError.accept(e);
                }
            }
        }.execute();
    }

    /**
     * @param file      저장할 파일 객체
     * @param content   저장할 텍스트 내용
     * @param onSuccess 저장 성공 시 실행할 콜백
     * @param onError   저장 중 오류가 발생했을 때 실행할 콜백
     */
    public void writeFileAsync(File file, String content, Runnable onSuccess, Consumer<Exception> onError) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Path path = file.toPath();
                Files.writeString(path, content);
                return null;
            }

            @Override
            protected void done() {
                try {
                    get(); // doInBackground()에서의 예외 확인
                    onSuccess.run();
                } catch (Exception e) {
                    onError.accept(e);
                }
            }
        }.execute();
    }

}
