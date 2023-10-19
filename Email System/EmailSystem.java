import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmailSystem extends JFrame {

    private JTextField recipientTextField;
    private JTextField subjectTextField;
    private JTextArea contentTextArea;

    public EmailSystem() {
        setTitle("Email System");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create and set layout manager
        setLayout(new BorderLayout());

        // Create and add components
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel recipientLabel = new JLabel("Recipient:");
        recipientTextField = new JTextField();

        JLabel subjectLabel = new JLabel("Subject:");
        subjectTextField = new JTextField();

        panel.add(recipientLabel);
        panel.add(recipientTextField);
        panel.add(subjectLabel);
        panel.add(subjectTextField);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel contentLabel = new JLabel("Content:");
        contentTextArea = new JTextArea();

        panel.add(contentLabel, BorderLayout.NORTH);
        panel.add(contentTextArea, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton sendButton = new JButton("Send");

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String recipient = recipientTextField.getText();
                String subject = subjectTextField.getText();
                String content = contentTextArea.getText();

                // Implement email sending logic here

                JOptionPane.showMessageDialog(EmailSystem.this, "Email sent successfully!");
            }
        });

        panel.add(sendButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmailSystem().setVisible(true);
            }
        });
    }
}