import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CoffeeShopPOS {
    public static void main(String[] args) {
        // Frame utama
        JFrame frame = new JFrame("Kasir Coffee Shop");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header
        JLabel headerLabel = new JLabel("Coffee Shop POS", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        headerLabel.setForeground(new Color(80, 40, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        frame.add(headerLabel, BorderLayout.NORTH);

        // Panel untuk input
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Pesanan"));
        inputPanel.setLayout(new GridLayout(3, 2, 10, 10));
        headerLabel.setForeground(new Color(80, 40, 20));

        JLabel nameLabel = new JLabel("Nama Menu:");
        JTextField nameField = new JTextField();
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);

        JLabel priceLabel = new JLabel("Harga:");
        JTextField priceField = new JTextField();
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);

        JLabel quantityLabel = new JLabel("Jumlah:");
        JTextField quantityField = new JTextField();
        inputPanel.add(quantityLabel);
        inputPanel.add(quantityField);

        frame.add(inputPanel, BorderLayout.WEST);

        // Tabel untuk daftar pesanan
        String[] columnNames = {"Nama Menu", "Harga", "Jumlah", "Total"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setRowHeight(30);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Daftar Pesanan"));

        frame.add(tableScrollPane, BorderLayout.CENTER);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));

        JButton addButton = new JButton("Tambah");
        addButton.setBackground(new Color(100, 200, 100));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Arial", Font.BOLD, 16));

        JButton printButton = new JButton("Cetak Bill");
        printButton.setBackground(new Color(100, 150, 200));
        printButton.setForeground(Color.WHITE);
        printButton.setFont(new Font("Arial", Font.BOLD, 16));

        buttonPanel.add(addButton);
        buttonPanel.add(printButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String priceText = priceField.getText();
                String quantityText = quantityField.getText();

                try {
                    double price = Double.parseDouble(priceText);
                    int quantity = Integer.parseInt(quantityText);
                    double total = price * quantity;

                    tableModel.addRow(new Object[]{name, price, quantity, total});

                    // Clear fields
                    nameField.setText("");
                    priceField.setText("");
                    quantityField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Harap masukkan harga dan jumlah yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextArea billArea = new JTextArea();
                billArea.setFont(new Font("Courier New", Font.PLAIN, 14));

                billArea.append("===================================\n");
                billArea.append("          Coffee Shop Bill          \n");
                billArea.append("===================================\n");

                double grandTotal = 0;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String name = tableModel.getValueAt(i, 0).toString();
                    String price = tableModel.getValueAt(i, 1).toString();
                    String quantity = tableModel.getValueAt(i, 2).toString();
                    String total = tableModel.getValueAt(i, 3).toString();

                    billArea.append(name + "\t" + quantity + " x " + price + "\t= " + total + "\n");
                    grandTotal += Double.parseDouble(total);
                }

                billArea.append("===================================\n");
                billArea.append("Total Akhir: " + grandTotal + "\n");
                billArea.append("===================================\n");
                billArea.append("Terima kasih telah berbelanja!\n");

                JOptionPane.showMessageDialog(frame, new JScrollPane(billArea), "Cetak Bill", JOptionPane.INFORMATION_MESSAGE);
            }
        });

       
        frame.setVisible(true);
    }
}