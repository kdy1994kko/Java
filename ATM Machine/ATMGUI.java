import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI extends JFrame implements ActionListener {
    private JTextField amountField;
    private JLabel messageLabel;

    private double balance;

    public ATMGUI() {
        setTitle("ATM Machine");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        balance = 1000.0; // Initial balance

        // Creating components
        JLabel titleLabel = new JLabel("Welcome to the ATM Machine");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2));

        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(this);
        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(this);

        centerPanel.add(amountLabel);
        centerPanel.add(amountField);
        centerPanel.add(withdrawButton);
        centerPanel.add(depositButton);

        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        add(centerPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 400));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Withdraw")) {
            double amount;

            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                showMessage("Invalid amount entered!");
                return;
            }

            if (amount <= 0) {
                showMessage("Amount must be greater than zero!");
                return;
            }

            if (amount > balance) {
                showMessage("Insufficient balance!");
                return;
            }

            balance -= amount;
            showMessage("Withdrawal successful! New balance: $" + balance);

        } else if (e.getActionCommand().equals("Deposit")) {
            double amount;

            try {
                amount = Double.parseDouble(amountField.getText());
            } catch (NumberFormatException ex) {
                showMessage("Invalid amount entered!");
                return;
            }

            if (amount <= 0) {
                showMessage("Amount must be greater than zero!");
                return;
            }

            balance += amount;
            showMessage("Deposit successful! New balance: $" + balance);
        }
    }

    private void showMessage(String message) {
        messageLabel.setText(message);
        amountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMGUI::new);
    }
}