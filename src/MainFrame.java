import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private JTable menuTable;
    private DefaultTableModel tableModel;
    private JButton orderButton, addMenuButton, showBillButton, deleteOrderButton;
    private JTextField customerNameField, menuNameField, menuPriceField;
    private JComboBox<String> menuComboBox;

    private ArrayList<MenuItem> menuList;
    private ArrayList<Order> orderList;

    public MainFrame() {
        setTitle("Program Pemesanan Makanan");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize menu and order lists
        menuList = new ArrayList<>();
        orderList = new ArrayList<>();
        loadMenuData();

        // Table to display menu
        menuTable = new JTable();
        tableModel = new DefaultTableModel();
        menuTable.setModel(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Nama Makanan");
        tableModel.addColumn("Harga");

        for (MenuItem menuItem : menuList) {
            tableModel.addRow(new Object[]{menuItem.getId(), menuItem.getName(), menuItem.getPrice()});
        }

        // Initialize UI components
        orderButton = new JButton("Pesan");
        addMenuButton = new JButton("Tambah Menu");
        showBillButton = new JButton("Tampilkan Tagihan");
        deleteOrderButton = new JButton("Hapus Pesanan");
        customerNameField = new JTextField(20);
        menuNameField = new JTextField(15);
        menuPriceField = new JTextField(10);
        menuComboBox = new JComboBox<>();

        // Add data to ComboBox
        for (MenuItem menuItem : menuList) {
            menuComboBox.addItem(menuItem.getName());
        }

        // Layout setup with GridBagLayout for better alignment
        setLayout(new BorderLayout());

        // Top panel (Input section)
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBackground(new Color(72, 122, 189)); // Attractive Blue
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("Nama Pembeli:"), gbc);

        gbc.gridx = 1;
        topPanel.add(customerNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        topPanel.add(new JLabel("Pilih Menu:"), gbc);

        gbc.gridx = 1;
        topPanel.add(menuComboBox, gbc);

        add(topPanel, BorderLayout.NORTH);

        // Center panel (Menu Table)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(new Color(243, 243, 243)); // Light Gray
        centerPanel.add(new JScrollPane(menuTable), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel (Order and menu actions)
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(new Color(72, 122, 189)); // Same Blue as top

        bottomPanel.add(orderButton);
        bottomPanel.add(new JLabel("Nama Menu:"));
        bottomPanel.add(menuNameField);
        bottomPanel.add(new JLabel("Harga:"));
        bottomPanel.add(menuPriceField);
        bottomPanel.add(addMenuButton);
        bottomPanel.add(showBillButton);
        bottomPanel.add(deleteOrderButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleOrder();
            }
        });

        addMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddMenu();
            }
        });

        showBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showBill();
            }
        });

        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteOrder();
            }
        });
    }

    // Load menu data (Simulasi API)
    private void loadMenuData() {
        try {
            menuList.add(new MenuItem(1, "Nasi Goreng", 20000));
            menuList.add(new MenuItem(2, "Mie Goreng", 15000));
            menuList.add(new MenuItem(3, "Ayam Bakar", 25000));
            menuList.add(new MenuItem(4, "Sate", 22000));
            menuList.add(new MenuItem(5, "Nasi Uduk", 18000));
            menuList.add(new MenuItem(6, "Rendang", 30000));
            menuList.add(new MenuItem(7, "Kwetiau", 22000));
            menuList.add(new MenuItem(8, "Pizza", 50000));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data menu.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Handle ordering
    private void handleOrder() {
        String customerName = customerNameField.getText();
        String selectedMenu = (String) menuComboBox.getSelectedItem();

        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pembeli harus diisi.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (MenuItem menuItem : menuList) {
            if (menuItem.getName().equals(selectedMenu)) {
                try {
                    Order order = new Order(customerName, menuItem);
                    orderList.add(order);
                    JOptionPane.showMessageDialog(this, "Pesanan berhasil untuk " + customerName + " dengan menu " + selectedMenu, "Success", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat memproses pesanan.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // Handle adding a new menu item
    private void handleAddMenu() {
        try {
            String menuName = menuNameField.getText();
            int menuPrice = Integer.parseInt(menuPriceField.getText());

            if (menuName.isEmpty() || menuPrice <= 0) {
                JOptionPane.showMessageDialog(this, "Nama menu atau harga tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            MenuItem newMenu = new MenuItem(menuList.size() + 1, menuName, menuPrice);
            menuList.add(newMenu);
            tableModel.addRow(new Object[]{newMenu.getId(), newMenu.getName(), newMenu.getPrice()});
            menuComboBox.addItem(newMenu.getName());
            JOptionPane.showMessageDialog(this, "Menu berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harga harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Show bill with improved appearance
    private void showBill() {
        if (orderList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada pesanan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder bill = new StringBuilder("<html><h3>Tagihan Pemesanan:</h3>");
        int totalAmount = 0;

        for (Order order : orderList) {
            bill.append("<hr>")
                .append("<b>").append(order.getCustomerName()).append("</b><br>")
                .append(order.getMenuItem().getName()).append(" - Rp. ").append(order.getMenuItem().getPrice()).append("<br>");
            totalAmount += order.getMenuItem().getPrice();
        }

        bill.append("<hr><b>Total:</b> Rp. ").append(totalAmount)
            .append("</html>");

        JOptionPane.showMessageDialog(this, bill.toString(), "Struck Tagihan", JOptionPane.INFORMATION_MESSAGE);
    }

    // Delete an order
    private void deleteOrder() {
        String customerName = customerNameField.getText();

        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nama pembeli harus diisi untuk menghapus pesanan.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (Order order : orderList) {
            if (order.getCustomerName().equals(customerName)) {
                orderList.remove(order);
                JOptionPane.showMessageDialog(this, "Pesanan untuk " + customerName + " telah dihapus.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Tidak ada pesanan ditemukan untuk " + customerName, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}

// Model Menu Makanan
class MenuItem {
    private int id;
    private String name;
    private int price;

    public MenuItem(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

// Model Pemesanan
class Order {
    private String customerName;
    private MenuItem menuItem;

    public Order(String customerName, MenuItem menuItem) {
        this.customerName = customerName;
        this.menuItem = menuItem;
    }

    public String getCustomerName() {
        return customerName;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
