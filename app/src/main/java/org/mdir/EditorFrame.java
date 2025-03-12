package org.mdir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class EditorFrame extends JFrame {

    private final FileHandler fileHandler;

    private JTextArea textArea;
    private JButton loadButton;    // 불러오기 버튼
    private JButton saveButton;    // 저장하기 버튼
    private JButton saveAsButton;  // 다른 이름으로 저장하기 버튼
    private File currentFile;

    public EditorFrame(FileHandler fileHandler) {
        super("간단 텍스트 편집기");
        this.fileHandler = fileHandler;
        initUI();
    }

    private void initUI() {
        setTitle("간단 텍스트 편집기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // 화면 중앙에 배치

        // 메인 패널: BorderLayout -> 패널 내부 여백 설정
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 텍스트 영역 + 스크롤
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 오른쪽 버튼 패널: BoxLayout으로 수직 배치
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        loadButton = new JButton("불러오기");
        saveButton = new JButton("저장하기");
        saveAsButton = new JButton("다른 이름으로 저장하기");

        // 버튼들을 추가하고, 간격을 주기 위해 Strut 추가
        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(saveButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(saveAsButton);

        // 버튼 패널을 BorderLayout의 동쪽(EAST)에 배치
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // 프레임에 메인 패널 추가
        add(mainPanel);

        loadButton.addActionListener(this::onLoad);
        saveButton.addActionListener(this::onSave);

        // 화면에 표시
        setVisible(true);
    }

    private void onLoad(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File selectedFile = fileChooser.getSelectedFile();

        fileHandler.readFileAsync(
                selectedFile,
                content -> {
                    textArea.setText(content);
                    currentFile = selectedFile;
                },
                ex -> showError("파일을 불러오는 중 오류가 발생했습니다.\n" + ex.getMessage())
        );
    }

    private void onSave(ActionEvent e) {
        if (currentFile == null) {
            showError("저장할 파일을 먼저 선택해주세요.");
            return;
        }

        File file = currentFile;
        String content = textArea.getText();
        fileHandler.writeFileAsync(
                file,
                content,
                () -> JOptionPane.showMessageDialog(this,
                        "파일이 성공적으로 저장되었습니다.",
                        "알림",
                        JOptionPane.INFORMATION_MESSAGE),
                ex -> showError("파일을 저장하는 중 오류가 발생했습니다.\n" + ex.getMessage())
        );
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "에러", JOptionPane.ERROR_MESSAGE);
    }

}
