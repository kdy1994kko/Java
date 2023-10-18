import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    private JFrame frame;
    private JPanel panel;
    private JTextField numField1, numField2, resultField;
    private JComboBox<String> operationComboBox;
    private JButton calculateButton;

    public CalculatorGUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        // change font size //
        Font font = new Font("Arial", Font.PLAIN, 20);

        numField1 = new JTextField();
        numField1.setFont(font);

        numField2 = new JTextField();
        numField2.setFont(font);

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(font);

        operationComboBox = new JComboBox<>();
        operationComboBox.setFont(font);
        operationComboBox.addItem("Addition");
        operationComboBox.addItem("Subtraction");
        operationComboBox.addItem("Multiplication");
        operationComboBox.addItem("Division");

        calculateButton = new JButton("Calculate");
        calculateButton.setFont(font);
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        panel.add(new JLabel("First Number:"));
        panel.add(new JLabel("Second Number:"));
        panel.add(new JLabel("Operation:"));
        panel.add(numField1);
        panel.add(numField2);
        panel.add(operationComboBox);
        panel.add(new JLabel("Result:"));
        panel.add(resultField);

        panel.add(calculateButton);

        frame.getContentPane().add(panel);
        // change screen size //
        frame.setPreferredSize(new Dimension(400, 400));
        frame.pack();
        frame.setVisible(true);
    }

    private void calculate() {
        double num1 = Double.parseDouble(numField1.getText());
        double num2 = Double.parseDouble(numField2.getText());
        int operationIndex = operationComboBox.getSelectedIndex();

        double result = 0.0;

        switch (operationIndex) {
            case 0:
                result = num1 + num2;
                break;
            case 1:
                result = num1 - num2;
                break;
            case 2:
                result = num1 * num2;
                break;
            case 3:
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(frame, "Error: Division by zero is not allowed.",
                            "Invalid Operation", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                break;
        }

        resultField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorGUI();
            }
        });
    }
}