import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ResumeBuilder extends JFrame {

    private final JTextField nameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField phoneField = new JTextField();
    private final JTextField skillsField = new JTextField();
    private final JTextField educationField = new JTextField();
    private final JTextField experienceField = new JTextField();
    private final JTextArea previewArea = new JTextArea();

    public ResumeBuilder() {
        setTitle("Resume Builder");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 620);
        setLocationRelativeTo(null);

        JPanel root = new JPanel(new BorderLayout(16, 16));
        root.setBorder(new EmptyBorder(16, 16, 16, 16));
        root.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("Конструктор резюме");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        title.setForeground(new Color(33, 37, 41));
        root.add(title, BorderLayout.NORTH);

        JPanel content = new JPanel(new GridLayout(1, 2, 16, 0));
        content.setOpaque(false);

        JPanel formPanel = buildFormPanel();
        JPanel previewPanel = buildPreviewPanel();

        content.add(formPanel);
        content.add(previewPanel);
        root.add(content, BorderLayout.CENTER);

        setContentPane(root);
        setVisible(true);
    }

    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 226, 230)),
                new EmptyBorder(16, 16, 16, 16)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        addField(panel, gbc, 0, "ФИО", nameField);
        addField(panel, gbc, 1, "Email", emailField);
        addField(panel, gbc, 2, "Телефон", phoneField);
        addField(panel, gbc, 3, "Навыки", skillsField);
        addField(panel, gbc, 4, "Образование", educationField);
        addField(panel, gbc, 5, "Опыт работы", experienceField);

        JButton generateButton = createButton("Сформировать резюме", new Color(25, 135, 84));
        generateButton.addActionListener(e -> generateResume());

        JButton saveButton = createButton("Сохранить в .txt", new Color(13, 110, 253));
        saveButton.addActionListener(e -> saveResume());

        JButton resetButton = createButton("Очистить", new Color(220, 53, 69));
        resetButton.addActionListener(e -> resetFields());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 8, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(generateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(resetButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(14, 6, 6, 6);
        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel buildPreviewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(222, 226, 230)),
                new EmptyBorder(16, 16, 16, 16)
        ));

        JLabel previewLabel = new JLabel("Предпросмотр");
        previewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        previewArea.setEditable(false);
        previewArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        previewArea.setLineWrap(true);
        previewArea.setWrapStyleWord(true);

        panel.add(previewLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(previewArea), BorderLayout.CENTER);
        return panel;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField field) {
        JLabel label = new JLabel(labelText + ":");
        label.setFont(new Font("SansSerif", Font.PLAIN, 14));

        field.setFont(new Font("SansSerif", Font.PLAIN, 14));

        gbc.gridy = row;
        gbc.gridx = 0;
        gbc.weightx = 0.35;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.65;
        panel.add(field, gbc);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void generateResume() {
        String resume = "==================== РЕЗЮМЕ ====================\n"
                + "ФИО: " + nameField.getText() + "\n"
                + "Email: " + emailField.getText() + "\n"
                + "Телефон: " + phoneField.getText() + "\n"
                + "Навыки: " + skillsField.getText() + "\n"
                + "Образование: " + educationField.getText() + "\n"
                + "Опыт работы: " + experienceField.getText() + "\n"
                + "===============================================\n";

        previewArea.setText(resume);
    }

    private void saveResume() {
        try (FileWriter writer = new FileWriter("MyResume.txt")) {
            writer.write(previewArea.getText());
            JOptionPane.showMessageDialog(this, "Резюме сохранено в MyResume.txt");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Не удалось сохранить файл");
        }
    }

    private void resetFields() {
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        skillsField.setText("");
        educationField.setText("");
        experienceField.setText("");
        previewArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ResumeBuilder::new);
    }
}
