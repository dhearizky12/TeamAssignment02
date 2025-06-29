import java.util.Scanner;

/**
 * Program manajemen antrian pelanggan restoran dengan dua mode:
 * - Single Linked List (manual)
 * - Doubly Linked List (menggunakan java.util.LinkedList)
 * Menampilkan waktu eksekusi untuk setiap aksi.
 */
public class RestaurantQueue {

    /**
     * === SINGLE LINKED LIST IMPLEMENTATION ===
     */
    private static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Node frontSingle, rearSingle;
    private int sizeSingle;

    /**
     * === DOUBLY LINKED LIST IMPLEMENTATION (MANUAL) ===
     */
    private static class DNode {
        String name;
        DNode prev, next;

        DNode(String name) {
            this.name = name;
        }
    }

    private DNode frontDouble, rearDouble;
    private int sizeDouble;

    /*** SINGLE LINKED LIST METHODS ***/
    public void pushSingle(String name) {
        long start = System.nanoTime();
        Node newNode = new Node(name);
        if (rearSingle == null) {
            frontSingle = rearSingle = newNode;
        } else {
            rearSingle.next = newNode;
            rearSingle = newNode;
        }
        sizeSingle++;
        long end = System.nanoTime();
        System.out.println("[SLL] Tambah: " + name + " (" + (end - start) / 1_000_000.0 + " ms)");
    }

    public void popSingle() {
        long start = System.nanoTime();
        if (frontSingle == null) {
            System.out.println("[SLL] Antrian kosong.");
            return;
        }
        System.out.println("[SLL] Layani: " + frontSingle.name);
        frontSingle = frontSingle.next;
        if (frontSingle == null) rearSingle = null;
        sizeSingle--;
        long end = System.nanoTime();
        System.out.println("[SLL] Waktu pop: " + (end - start) / 1_000_000.0 + " ms");
    }

    public void displaySingle() {
        System.out.println("\n[SLL] Antrian Pelanggan:");
        Node current = frontSingle;
        int i = 1;
        while (current != null) {
            System.out.println(i++ + ". " + current.name);
            current = current.next;
        }
        System.out.println("Total: " + sizeSingle + " pelanggan\n");
    }

    /*** DOUBLY LINKED LIST METHODS ***/
    public void pushDouble(String name) {
        long start = System.nanoTime();
        DNode newNode = new DNode(name);
        if (rearDouble == null) {
            frontDouble = rearDouble = newNode;
        } else {
            rearDouble.next = newNode;
            newNode.prev = rearDouble;
            rearDouble = newNode;
        }
        sizeDouble++;
        long end = System.nanoTime();
        System.out.println("[DLL] Tambah: " + name + " (" + (end - start) / 1_000_000.0 + " ms)");
    }

    public void popDouble() {
        long start = System.nanoTime();
        if (frontDouble == null) {
            System.out.println("[DLL] Antrian kosong.");
            return;
        }
        System.out.println("[DLL] Layani: " + frontDouble.name);
        frontDouble = frontDouble.next;
        if (frontDouble == null) rearDouble = null;
        else frontDouble.prev = null;
        sizeDouble--;
        long end = System.nanoTime();
        System.out.println("[DLL] Waktu pop: " + (end - start) / 1_000_000.0 + " ms");
    }

    public void displayDouble() {
        System.out.println("\n[DLL] Antrian Pelanggan:");
        DNode current = frontDouble;
        int i = 1;
        while (current != null) {
            System.out.println(i++ + ". " + current.name);
            current = current.next;
        }
        System.out.println("Total: " + sizeDouble + " pelanggan\n");
    }

    /*** MAIN MENU ***/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantQueue queue = new RestaurantQueue();

        int mode = 0;
        do {
            System.out.println("Pilih Mode:");
            System.out.println("1. Single Linked List");
            System.out.println("2. Doubly Linked List");
            System.out.print("Masukkan pilihan (1/2): ");
            mode = scanner.nextInt();
        } while (mode != 1 && mode != 2);

        int choice;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tambah pelanggan (push)");
            System.out.println("2. Layani pelanggan (pop)");
            System.out.println("3. Tampilkan antrian");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka 1-4: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String name = scanner.nextLine();
                    if (mode == 1) queue.pushSingle(name);
                    else queue.pushDouble(name);
                    break;
                case 2:
                    if (mode == 1) queue.popSingle();
                    else queue.popDouble();
                    break;
                case 3:
                    if (mode == 1) queue.displaySingle();
                    else queue.displayDouble();
                    break;
                case 4:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
