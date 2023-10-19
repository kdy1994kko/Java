import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TypingSpeedProgram extends JFrame {
    private JTextField userInputField;
    private JTextArea textArea;
    private JLabel resultLabel;
    private JButton startButton;
    private JLabel timerLabel;
    private Timer timer;
    private long startTime;

    private static String[] defaultTexts = {
            "Praise be to the Lord my Rock, who trains my hands for war and my fingers for battle ~ Psalm 144:1-2.",
            "Even though I walk through the valley of the shadow of death, I will fear no evil, for You are with me: Your rod and Your staff comfort me ~ Psalm 23:4.",
            "Away From Me Satan, Worship the Lord your God and serve him only ~ Matthew 4:10-11.",
            "There will be no more death, mourning, crying or pain ~ Revelation 21:4.",
            "I Love You Jesus."
    };

    public TypingSpeedProgram() {
        setTitle("Typing Speed Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and configure components
        userInputField = new JTextField(20);
        userInputField.setEnabled(false);
        userInputField.setFont(userInputField.getFont().deriveFont(20f));

        textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(textArea.getFont().deriveFont(20f));

        resultLabel = new JLabel();
        resultLabel.setFont(resultLabel.getFont().deriveFont(20f));

        startButton = new JButton("Start");
        startButton.setFont(startButton.getFont().deriveFont(20f));

        timerLabel = new JLabel();
        timerLabel.setFont(timerLabel.getFont().deriveFont(20f));

        // Add components to the content pane
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        container.add(new JScrollPane(textArea));
        container.add(userInputField);
        container.add(resultLabel);
        container.add(startButton);
        container.add(timerLabel);

        pack();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startButton.setEnabled(false);
                userInputField.setEnabled(true);
                userInputField.setText("");
                textArea.setEnabled(false);
                resultLabel.setText("");
                timerLabel.setText("");

                // Set the random default text
                Random random = new Random();
                int randomIndex = random.nextInt(defaultTexts.length);
                textArea.setText(defaultTexts[randomIndex]);

                // Start the timer
                startTime = System.currentTimeMillis();
                timer.start();
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

                // Stop the timer
                timer.stop();

                // Reset the interface
                userInputField.setEnabled(false);
                textArea.setEnabled(true);
                startButton.setEnabled(true);
                userInputField.setText("");
            }
        });

        // Create a timer to display elapsed time
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                timerLabel.setText("Time: " + elapsedTime + "s");
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