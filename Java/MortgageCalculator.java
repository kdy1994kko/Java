
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageCalculator {
    private JFrame frame;
    private JTextField loanAmountTextField;
    private JTextField interestRateTextField;
    private JTextField loanTermTextField;
    private JLabel resultLabel;

    public MortgageCalculator() {
        frame = new JFrame("Mortgage Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setSize(1600, 1600); // Set the size of the frame

        // Configure font size
        Font font = new Font("Arial", Font.PLAIN, 14);
 
        // Create labels
        JLabel loanAmountLabel = new JLabel("Loan Amount:");
        JLabel interestRateLabel = new JLabel("Interest Rate (%):");
        JLabel loanTermLabel = new JLabel("Loan Term (years):");

        // Set font for labels
        loanAmountLabel.setFont(font);
        interestRateLabel.setFont(font);
        loanTermLabel.setFont(font);

        // Create text fields
        loanAmountTextField = new JTextField(10);
        interestRateTextField = new JTextField(10);
        loanTermTextField = new JTextField(10);

        // Create button
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setFont(font);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMonthlyPayment();
            }
        });

        // Create result label
        resultLabel = new JLabel();
        resultLabel.setFont(font);

        // Add components to frame
        frame.add(loanAmountLabel);
        frame.add(loanAmountTextField);
        frame.add(interestRateLabel);
        frame.add(interestRateTextField);
        frame.add(loanTermLabel);
        frame.add(loanTermTextField);
        frame.add(calculateButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    private void calculateMonthlyPayment() {
        double loanAmount = Double.parseDouble(loanAmountTextField.getText());
        double interestRate = Double.parseDouble(interestRateTextField.getText());
        int loanTerm = Integer.parseInt(loanTermTextField.getText());

        double monthlyInterestRate = interestRate / 100 / 12;
        int totalPayments = loanTerm * 12;
        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));

        resultLabel.setText("Monthly Payment: " + String.format("%.2f", monthlyPayment));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MortgageCalculator();
            }
        });
    }
}
