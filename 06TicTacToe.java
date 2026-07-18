import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private boolean xTurn = true;
    private JLabel status;
    private JButton restart;

    public TicTacToeGUI() {
        setTitle("Java GUI Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        status = new JLabel("Player X's Turn", SwingConstants.CENTER);
        status.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel board = new JPanel();
        board.setLayout(new GridLayout(3, 3));

        // Create buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[row][col].addActionListener(this);
                board.add(buttons[row][col]);
            }
        }

        restart = new JButton("Restart Game");
        restart.addActionListener(e -> resetGame());

        add(status, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(restart, BorderLayout.SOUTH);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        JButton clicked = (JButton)e.getSource();

        if (!clicked.getText().equals("")) {
            return;
        }

        if (xTurn) {
            clicked.setText("X");
            status.setText("Player O's Turn");
        } else {
            clicked.setText("O");
            status.setText("Player X's Turn");
        }

        xTurn = !xTurn;

        checkWinner();
    }


    private void checkWinner() {

        String winner = "";

        // Rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText())) {

                winner = buttons[i][0].getText();
            }
        }


        // Columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[0][i].getText().equals("") &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText())) {

                winner = buttons[0][i].getText();
            }
        }


        // Diagonals
        if (!buttons[0][0].getText().equals("") &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText())) {

            winner = buttons[0][0].getText();
        }


        if (!buttons[0][2].getText().equals("") &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText())) {

            winner = buttons[0][2].getText();
        }


        if (!winner.equals("")) {
            JOptionPane.showMessageDialog(
                this,
                "Player " + winner + " Wins!"
            );

            disableButtons();
        }
    }


    private void disableButtons() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setEnabled(false);
            }
        }
    }


    private void resetGame() {

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setText("");
                buttons[r][c].setEnabled(true);
            }
        }

        xTurn = true;
        status.setText("Player X's Turn");
    }


    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
