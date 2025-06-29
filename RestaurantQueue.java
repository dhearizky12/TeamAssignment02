import java.util.Scanner;

/**
 * Program manajemen antrian pelanggan restoran menggunakan Single Linked List
 * Tugas Kelompok 2 - Struktur Data dan Analisis Algoritma
 * Mengimplementasikan konsep OOP dan struktur data sesuai materi kuliah
 */
public class RestaurantQueue {
    // Node class merepresentasikan satu pelanggan
    private static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    // Class utama queue
    private Node front, rear;
    private int size;

    public RestaurantQueue() {
        this.front = this.rear = null;
        this.size = 0;
    }

    /**
     * Menambahkan pelanggan ke akhir antrian
     * @param name nama pelanggan
     */
    public void push(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Nama pelanggan tidak boleh kosong.");
            return;
        }
        Node newNode = new Node(name.trim());
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Pelanggan \"" + name + "\" berhasil ditambahkan ke antrian.");
    }

    /**
     * Melayani pelanggan pertama dalam antrian (menghapus dari depan)
     */
    public void pop() {
        if (front == null) {
            System.out.println("Antrian kosong. Tidak ada pelanggan yang bisa dilayani.");
            return;
        }
        System.out.println("Pelanggan \"" + front.name + "\" telah dilayani dan dihapus dari antrian.");
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
    }

    /**
     * Menampilkan seluruh pelanggan dalam antrian saat ini
     */
    public void displayQueue() {
        if (front == null) {
            System.out.println("Antrian kosong.");
            return;
        }
        System.out.println("\nDaftar pelanggan dalam antrian:");
        Node current = front;
        int no = 1;
        while (current != null) {
            System.out.println(no + ". " + current.name);
            current = current.next;
            no++;
        }
        System.out.println("Total pelanggan dalam antrian: " + size + "\n");
    }

    /**
     * Program utama dengan menu interaktif
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantQueue queue = new RestaurantQueue();
        int choice;

        do {
            System.out.println("=== Sistem Antrian Pelanggan Restoran ===");
            System.out.println("1. Tambah Pelanggan ke Antrian (push)");
            System.out.println("2. Layani Pelanggan (pop)");
            System.out.println("3. Tampilkan Antrian Saat Ini");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            
            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka 1-4: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Clear newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String name = scanner.nextLine();
                    queue.push(name);
                    break;
                case 2:
                    queue.pop();
                    break;
                case 3:
                    queue.displayQueue();
                    break;
                case 4:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1-4.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
