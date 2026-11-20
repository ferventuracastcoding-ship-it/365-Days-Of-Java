import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BankingGUI extends JFrame {

    private double balance = 1000.00;

    private JLabel balanceLabel;
    private JTextArea historyArea;

    private ArrayList<String> history = new ArrayList<>();

    public BankingGUI() {

        setTitle("Banking Application");
        setSize(600,450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        balanceLabel = new JLabel(
                "Balance: $" + String.format("%.2f", balance),
                SwingConstants.CENTER);

        balanceLabel.setFont(new Font("Arial", Font.BOLD, 24));

        historyArea = new JTextArea();
        historyArea.setEditable(false);

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton historyButton = new JButton("Refresh History");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(historyButton);

        add(balanceLabel, BorderLayout.NORTH);
        add(new JScrollPane(historyArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        depositButton.addActionListener(e -> deposit());

        withdrawButton.addActionListener(e -> withdraw());

        historyButton.addActionListener(e -> updateHistory());

        history.add("Account opened with $1000.00");

        updateHistory();

        setVisible(true);
    }

    private void deposit() {

        String input = JOptionPane.showInputDialog(
                this,
                "Deposit Amount:");

        if (input == null)
            return;

        try {

            double amount = Double.parseDouble(input);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Amount must be greater than zero.");
                return;
            }

            balance += amount;

            history.add("Deposit: +$" +
                    String.format("%.2f", amount));

            updateBalance();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Invalid amount.");

        }

    }

    private void withdraw() {

        String input = JOptionPane.showInputDialog(
                this,
                "Withdraw Amount:");

        if (input == null)
            return;

        try {

            double amount = Double.parseDouble(input);

            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Amount must be greater than zero.");
                return;
            }

            if (amount > balance) {

                JOptionPane.showMessageDialog(this,
                        "Insufficient funds.");

                return;
            }

            balance -= amount;

            history.add("Withdrawal: -$" +
                    String.format("%.2f", amount));

            updateBalance();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Invalid amount.");

        }

    }

    private void updateBalance() {

        balanceLabel.setText(
                "Balance: $" +
                String.format("%.2f", balance));

        updateHistory();

    }

    private void updateHistory() {

        historyArea.setText("");

        for (String transaction : history) {

            historyArea.append(transaction + "\n");

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(BankingGUI::new);

    }
}
