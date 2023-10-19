import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TypingSpeedProgram extends JFrame {
    private JTextField userInputField;
    private JTextArea textArea;
    private JLabel resultLabel;
    private JButton startButton;
    private long startTime;

    public TypingSpeedProgram() {
        setTitle("Typing Speed Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and configure components
        userInputField = new JTextField(20);
        userInputField.setEnabled(false);

        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        resultLabel = new JLabel();

        startButton = new JButton("Start");

        // Add components to the content pane
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JScrollPane(textArea));
        container.add(userInputField);
        container.add(resultLabel);
        container.add(startButton);

        pack();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                userInputField.setEnabled(true);
                userInputField.setText("");
                textArea.setEnabled(false);
                resultLabel.setText("Type the text above...");

                // Start the timer
                startTime = System.currentTimeMillis();
            }
        });

        userInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userText = userInputField.getText();

                // Calculate the typing speed
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                int wordsTyped = userText.split("\\s+").length;
                int typingSpeed = (int) ((wordsTyped * 60000) / totalTime); // Words per minute

                resultLabel.setText("Your typing speed: " + typingSpeed + " WPM");

                // Reset the interface
                userInputField.setEnabled(false);
                textArea.setEnabled(true);
                startButton.setEnabled(true);
                userInputField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TypingSpeedProgram program = new TypingSpeedProgram();
                program.setVisible(true);
            }
        });
    }
}