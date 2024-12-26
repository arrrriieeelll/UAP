import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI {
    public static void main(String[] args) {
        // Membuat frame (jendela utama)
        JFrame frame = new JFrame("Program GUI Sederhana");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Menambahkan label
        JLabel label = new JLabel("Masukkan Nama Anda:");
        frame.add(label);

        // Menambahkan teks input
        JTextField textField = new JTextField(20);
        frame.add(textField);

        // Menambahkan tombol
        JButton button = new JButton("Tampilkan Nama");
        frame.add(button);

        // Menambahkan area teks untuk menampilkan output
        JTextArea textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea));

        // Menambahkan aksi pada tombol
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textField.getText();
                if (!name.isEmpty()) {
                    textArea.setText("Halo, " + name + "! Selamat datang di program GUI sederhana.");
                } else {
                    textArea.setText("Harap masukkan nama Anda terlebih dahulu.");
                }
            }
        });

        // Menampilkan frame
        frame.setVisible(true);
    }
}