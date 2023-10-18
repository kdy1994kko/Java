// make a scientific calculator in java gui //
// make the equal button functional if you can
// make the equal button functional if you can
// make all buttons functional if you can
// make the sin, cos, tan, √, x^2, % buttons functional if you can
 

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScientificCalculator extends JFrame implements ActionListener {

    private JTextField display;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 5));

        String[] buttonLabels = {
                "sin", "cos", "tan", "√", "x^2", "7", "8", "9", "/", "C",
                "4", "5", "6", "*", "AC", "1", "2", "3", "-", "Enter",
                "0", ".", "%", "+", "="
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        setLayout(new BorderLayout());
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("C")) {
            display.setText("");
        } else if (command.equals("AC")) {
            display.setText("");
        } else if (command.equals("Enter")) {
            String expression = display.getText();
            try {
                double result = evaluateExpression(expression);
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Invalid expression");
            }
        } else if (command.equals("=")) {
            String expression = display.getText();
            try {
                double result = evaluateExpression(expression);
                display.setText(expression + " = " + String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Invalid expression");
            }
        } else {
            display.setText(display.getText() + command);
        }
    }

    private double evaluateExpression(String expression) throws Exception {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() throws Exception {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) {
                    throw new Exception("Unexpected character: " + (char) ch);
                }
                return x;
            }

            double parseExpression() throws Exception {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() throws Exception {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) {
                        x *= parseFactor();
                    } else if (eat('/')) {
                        x /= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() throws Exception {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = expression.substring(startPos, this.pos);
                    x = parseFactor();

                    switch (func) {
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        case "√":
                            x = Math.sqrt(x);
                            break;
                        case "x^2":
                            x = Math.pow(x, 2);
                            break;
                        default:
                            throw new Exception("Unknown function: " + func);
                    }
                } else {
                    throw new Exception("Unexpected character: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ScientificCalculator calculator = new ScientificCalculator();
                calculator.setVisible(true);
            }
        });
    }
}