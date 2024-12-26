Tujuan Program
Program ini bertujuan untuk memberikan pengalaman pemesanan makanan yang cepat, rapi, dan mudah dipahami. Program dilengkapi dengan antarmuka yang menarik dan fitur yang lengkap, seperti pengelolaan menu, pemesanan makanan, dan menampilkan tagihan.

Fitur Utama
Menampilkan Daftar Menu

Daftar menu makanan ditampilkan dalam tabel menggunakan komponen JTable agar pengguna dapat melihat menu dengan rapi.
Data menu diisi secara otomatis saat program dijalankan.
Pemesanan Makanan

Pengguna dapat memilih makanan dari JComboBox, yang menampilkan daftar nama menu.
Pengguna perlu mengisi nama pelanggan sebelum memesan. Jika nama tidak diisi, program akan menampilkan pesan kesalahan (exception handling).
Penambahan Menu

Pengguna dapat menambahkan menu baru dengan mengisi nama menu dan harga. Data akan langsung ditambahkan ke tabel dan JComboBox.
Jika input harga bukan angka atau input tidak valid, program akan menangani kesalahan dengan menampilkan pesan error.
Penghapusan Pesanan

Pengguna dapat menghapus pesanan berdasarkan nama pelanggan. Jika pesanan tidak ditemukan, program akan memberi pesan bahwa pesanan tidak ada.
Tampilan Tagihan (Bill)

Program menampilkan tagihan dalam format yang bersih dan menarik di area JTextArea.
Tagihan mencakup nama pelanggan, daftar pesanan, dan total harga.
Desain Antarmuka yang Menarik

Warna biru pada bagian atas dan bawah program membuat tampilan lebih modern dan profesional.
Area tagihan memiliki latar belakang putih dan border untuk membedakannya dari bagian lain.
Font yang jelas dan tata letak yang teratur membuat program lebih mudah digunakan.
Komponen Utama
JTable:

Digunakan untuk menampilkan daftar menu dalam format tabel. Data diatur dengan menggunakan model tabel DefaultTableModel.
JComboBox:

Mempermudah pemilihan menu dengan menampilkan daftar makanan yang tersedia dalam bentuk dropdown.
JTextArea:

Digunakan untuk menampilkan tagihan dalam format teks yang rapi.
JButton:

Digunakan untuk berbagai fungsi, seperti melakukan pemesanan, menambah menu baru, menampilkan tagihan, dan menghapus pesanan.
Exception Handling:

Menangani input yang tidak valid, seperti nama pelanggan kosong atau harga bukan angka. Program memberikan pesan error yang jelas untuk membantu pengguna.
Desain dan Warna
Warna Biru Cerah:

Bagian atas dan bawah program diberi warna biru cerah (Color(72, 122, 189)) untuk menarik perhatian dan memberikan kesan profesional.
Panel Tagihan:

Panel untuk menampilkan tagihan diberi latar belakang putih dengan border agar tampak terpisah dari bagian lain.
Font Modern:

Font("Arial", Font.PLAIN, 14) digunakan pada tagihan agar mudah dibaca. Font yang digunakan juga seragam di seluruh program untuk menjaga konsistensi.
Tata Letak Teratur:

Kombinasi GridBagLayout dan FlowLayout digunakan agar elemen-elemen GUI tertata dengan baik dan mudah dipahami pengguna.
Struktur Data
Kelas MenuItem:

Representasi menu makanan, dengan atribut:
id: ID unik menu.
name: Nama menu.
price: Harga menu.
Kelas Order:

Representasi pesanan, dengan atribut:
customerName: Nama pelanggan.
menuItem: Objek menu yang dipesan.
ArrayList:

menuList: Menyimpan daftar menu yang tersedia.
orderList: Menyimpan daftar pesanan.
Penjelasan Fitur Tambahan
CRUD (Create, Read, Update, Delete):

Create: Penambahan menu baru menggunakan input pengguna.
Read: Menampilkan menu di tabel dan pesanan dalam tagihan.
Update: Memperbarui JComboBox saat menu baru ditambahkan.
Delete: Menghapus pesanan berdasarkan nama pelanggan.
Improved Bill Format:

Tagihan ditampilkan dengan format yang menarik, termasuk garis pemisah dan format HTML untuk teks tebal.
Langkah Penggunaan Program
Jalankan program.
Pilih nama menu dari dropdown (JComboBox).
Isi nama pelanggan di kotak teks "Nama Pembeli".
Klik tombol "Pesan" untuk memesan makanan.
Untuk menambah menu, isi "Nama Menu" dan "Harga" lalu klik "Tambah Menu".
Klik "Tampilkan Tagihan" untuk melihat daftar pesanan dan total harga.
Untuk menghapus pesanan, masukkan nama pelanggan di "Nama Pembeli" dan klik "Hapus Pesanan".