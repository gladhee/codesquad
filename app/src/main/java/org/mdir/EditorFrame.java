package org.mdir;

import javax.swing.*;
import java.awt.*;

public class EditorFrame extends JFrame {

    private JTextArea textArea;
    private JButton loadButton;    // 불러오기 버튼
    private JButton saveButton;    // 저장하기 버튼

    public EditorFrame() {
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

        // 버튼들을 추가하고, 간격을 주기 위해 Strut 추가
        buttonPanel.add(loadButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(saveButton);

        // 버튼 패널을 BorderLayout의 동쪽(EAST)에 배치
        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // 프레임에 메인 패널 추가
        add(mainPanel);

        // 화면에 표시
        setVisible(true);
    }

    public JButton getLoadButton() {
        return loadButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

}
