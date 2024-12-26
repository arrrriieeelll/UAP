import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI {
    public static void main(String[] args) {
        // Membuat frame utama
        JFrame frame = new JFrame("Kalkulator GUI");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel untuk layar kalkulator
        JPanel screenPanel = new JPanel();
        JTextField screen = new JTextField(20);
        screen.setFont(new Font("Arial", Font.BOLD, 24));
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setEditable(false);
        screenPanel.add(screen);
        frame.add(screenPanel, BorderLayout.NORTH);

        // Panel untuk tombol-tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            buttonPanel.add(button);

            // Tambahkan aksi untuk tombol
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String command = e.getActionCommand();
                    if (command.equals("=")) {
                        try {
                            String result = String.valueOf(eval(screen.getText()));
                            screen.setText(result);
                        } catch (Exception ex) {
                            screen.setText("Error");
                        }
                    } else {
                        screen.setText(screen.getText() + command);
                    }
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        // Menambahkan tombol clear (hapus semua)
        JButton clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial", Font.BOLD, 18));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.setText("");
            }
        });
        frame.add(clearButton, BorderLayout.SOUTH);

        // Menampilkan frame
        frame.setVisible(true);
    }

    // Fungsi untuk evaluasi ekspresi matematika
    public static double eval(String expression) {
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

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) x += parseTerm(); // penjumlahan
                    else if (eat('-')) x -= parseTerm(); // pengurangan
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('*')) x *= parseFactor(); // perkalian
                    else if (eat('/')) x /= parseFactor(); // pembagian
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                return x;
            }
        }.parse();
    }
}
