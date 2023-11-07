import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorUI {
    private JFrame frame;
    private JComboBox<String> operationComboBox;
    private JTextField[] inputFields;
    private JButton calculateButton;
    private JLabel resultLabel;

    public CalculatorUI() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        operationComboBox = new JComboBox<>(new String[]{"Addition", "Subtraction", "Multiplication", "Division"});
        inputFields = new JTextField[10];

        for (int i = 0; i < 10; i++) {
            inputFields[i] = new JTextField(10);
            inputPanel.add(new JLabel("Number " + (i + 1) + ":"));
            inputPanel.add(inputFields[i]);
        }

        JPanel controlPanel = new JPanel();
        calculateButton = new JButton("Calculate");
        controlPanel.add(calculateButton);

        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(operationComboBox, BorderLayout.NORTH);
        mainPanel.add(inputPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.add(resultLabel, BorderLayout.SOUTH);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        frame.setVisible(true);
    }

    private void calculate() {
        int choice = operationComboBox.getSelectedIndex() + 1;
        double result = 0;

        for (int i = 0; i < 10; i++) {
            try {
                double number = Double.parseDouble(inputFields[i].getText());

                switch (choice) {
                    case 1:
                        result += number;
                        break;
                    case 2:
                        if (i == 0) {
                            result = number;
                        } else {
                            result -= number;
                        }
                        break;
                    case 3:
                        if (i == 0) {
                            result = number;
                        } else {
                            result *= number;
                        }
                        break;
                    case 4:
                        if (i == 0) {
                            result = number;
                        } else {
                            if (number == 0) {
                                resultLabel.setText("Error: Division by zero.");
                                return;
                            }
                            result /= number;
                        }
                        break;
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("Error: Invalid input");
                return;
            }
        }

        resultLabel.setText("Result: " + result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculatorUI();
        });
    }
}