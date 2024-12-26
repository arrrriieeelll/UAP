import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class passwordGui {
    public static void main(String[] args) {
        JFrame frame = new JFrame("password gui");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Masukkan Kata Sandi:");
        frame.add(label);

        JPasswordField passwordField = new JPasswordField(20);
        frame.add(passwordField);

        JButton button = new JButton("Validasi Password");
        frame.add(button);

        JLabel resultLabel = new JLabel("Password akan divalidasi di sini.");
        frame.add(resultLabel);

        // Menambahkan ActionListener untuk tombol
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            
            char[] password = passwordField.getPassword();
            String passwordText = new String(password);
            
            // Memeriksa apakah password memenuhi syarat
            if (passwordText.length() < 7) {
            JOptionPane.showMessageDialog(frame, "Password tidak valid.Minimal 7 karakter.");
            } else if (!passwordText.matches(".*[A-Z].*")) {
            JOptionPane.showMessageDialog(frame, "Password tidak valid.Harus mengandung huruf besar.");
            } else if (!passwordText.matches(".*[a-z].*")) {
            JOptionPane.showMessageDialog(frame, "Password tidak valid. Harus mengandung huruf kecil.");
            } else if (!passwordText.matches(".*[0-9].*")){
            JOptionPane.showMessageDialog(frame, "Password tidak valid.Harus mengandung angka.");
            } else {
            JOptionPane.showMessageDialog(frame, "Password Valid");
            }
        }
    });
            frame.setVisible(true);
    }
}