import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    private JTextField inputField;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator = ' ';
    private boolean isOperatorPressed = false;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputField.setEditable(false);
        add(inputField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // If a digit is pressed
        if (command.matches("[0-9]")) {
            if (isOperatorPressed) {
                inputField.setText("");
                isOperatorPressed = false;
            }
            inputField.setText(inputField.getText() + command);
        }
        // Clear button
        else if (command.equals("C")) {
            inputField.setText("");
            num1 = num2 = result = 0;
            operator = ' ';
        }
        // Equals button
        else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(inputField.getText());

                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            inputField.setText("Error: Divide by 0");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        inputField.setText("Invalid Op");
                        return;
                }

                inputField.setText(String.valueOf(result));
                operator = ' ';
            } catch (NumberFormatException ex) {
                inputField.setText("Invalid input");
            }
        }
        // Operator buttons
        else if ("+-*/".contains(command)) {
            try {
                num1 = Double.parseDouble(inputField.getText());
                operator = command.charAt(0);
                isOperatorPressed = true;
            } catch (NumberFormatException ex) {
                inputField.setText("Enter a number first");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleCalculator calc = new SimpleCalculator();
            calc.setVisible(true);
        });
    }
}
