import javax.swing.*; //menyediakan kelas kelas untuk membuat tombol, label, dll
import javax.swing.table.DefaultTableModel; // model tabel
import java.awt.*; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // menambahkan aksi pada tombol

public class TicketBookingGUI {

    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Pemesanan Tiket Wisata");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel booking
        JPanel bookingPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // wadah untuk menampung boking

        // Componen booking
        JLabel nameLabel = new JLabel("Nama Pemesan:");
        JTextField nameField = new JTextField(); //menerima input dari pengguna

        JLabel destinationLabel = new JLabel("Destinasi:");
        String[] destinations = {"Bali", "Yogyakarta", "Lombok", "Jakarta"};
        JComboBox<String> destinationBox = new JComboBox<>(destinations); // daftar pilihan

        JLabel classLabel = new JLabel("Kelas Tiket:");
        String[] ticketClasses = {"Ekonomi", "Bisnis", "First Class"};
        JComboBox<String> classBox = new JComboBox<>(ticketClasses);

        JLabel ticketCountLabel = new JLabel("Jumlah Tiket:");
        SpinnerModel ticketModel = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner ticketSpinner = new JSpinner(ticketModel);

        JButton addButton = new JButton("Tambah ke Bill");
        JButton deleteButton = new JButton("Hapus Pesanan");
        JButton viewButton = new JButton("Lihat Tiket");

        // Add componen booking
        bookingPanel.add(nameLabel);
        bookingPanel.add(nameField);
        bookingPanel.add(destinationLabel);
        bookingPanel.add(destinationBox);
        bookingPanel.add(classLabel);
        bookingPanel.add(classBox);
        bookingPanel.add(ticketCountLabel);
        bookingPanel.add(ticketSpinner);
        bookingPanel.add(addButton);
        bookingPanel.add(deleteButton);

        // Table bill
        String[] columnNames = {"Nama", "Destinasi", "Kelas", "Jumlah", "Total"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable billTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(billTable);

        // Panel total
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel totalLabel = new JLabel("Total Harga: Rp 0");
        totalPanel.add(viewButton); //melletakkan tombol cetak ke kanan
        totalPanel.add(totalLabel);

        // aksi tombol harga tiket
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String destination = (String) destinationBox.getSelectedItem();
                String ticketClass = (String) classBox.getSelectedItem();
                int ticketCount = (Integer) ticketSpinner.getValue();

                
                int pricePerTicket = 0;
                switch (ticketClass) {
                    case "Ekonomi":
                        pricePerTicket = 500000;
                        break;
                    case "Bisnis":
                        pricePerTicket = 1000000;
                        break;
                    case "First Class":
                        pricePerTicket = 2000000;
                        break;
                }
                int totalPrice = pricePerTicket * ticketCount;

                
                tableModel.addRow(new Object[]{name, destination, ticketClass, ticketCount, "Rp " + totalPrice});

                
                int currentTotal = 0;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    String totalStr = tableModel.getValueAt(i, 4).toString().replace("Rp ", "").replace(",", "");
                    currentTotal += Integer.parseInt(totalStr);
                }
                totalLabel.setText("Total Harga: Rp " + currentTotal);

               
                nameField.setText("");
                destinationBox.setSelectedIndex(0);
                classBox.setSelectedIndex(0);
                ticketSpinner.setValue(1);
            }
        });

        // aksi untuk tombol delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = billTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);

                    
                    int currentTotal = 0;
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String totalStr = tableModel.getValueAt(i, 4).toString().replace("Rp ", "").replace(",", "");
                        currentTotal += Integer.parseInt(totalStr);
                    }
                    totalLabel.setText("Total Harga: Rp " + currentTotal);
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // aksi untuk tombol cetal bill
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = billTable.getSelectedRow();
                if (selectedRow != -1) {
                    String name = tableModel.getValueAt(selectedRow, 0).toString();
                    String destination = tableModel.getValueAt(selectedRow, 1).toString();
                    String ticketClass = tableModel.getValueAt(selectedRow, 2).toString();
                    String ticketCount = tableModel.getValueAt(selectedRow, 3).toString();
                    String totalPrice = tableModel.getValueAt(selectedRow, 4).toString();

                    JOptionPane.showMessageDialog(frame, "Detail Tiket:\n" +
                            "Nama: " + name + "\n" +
                            "Destinasi: " + destination + "\n" +
                            "Kelas: " + ticketClass + "\n" +
                            "Jumlah: " + ticketCount + "\n" +
                            "Total Harga: " + totalPrice, "Detail Tiket", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih baris yang ingin dilihat.", "Peringatan", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        frame.add(bookingPanel, BorderLayout.NORTH);
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(totalPanel, BorderLayout.SOUTH);

        
        frame.setVisible(true);
    }
}
