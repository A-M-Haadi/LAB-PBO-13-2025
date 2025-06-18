import java.util.*;

// CLI Class untuk interface pengguna
public class LibraryCLI {
    private Library library;
    private Scanner scanner;
    
    public LibraryCLI() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
    }
    
    public void start() {
        System.out.println("=== SISTEM MANAJEMEN PERPUSTAKAAN ===");
        
        while (true) {
            showMenu();
            int choice = getIntInput("Pilih menu: ");
            
            switch (choice) {
                case 1:
                    addItemMenu();
                    break;
                case 2:
                    addMemberMenu();
                    break;
                case 3:
                    borrowItemMenu();
                    break;
                case 4:
                    returnItemMenu();
                    break;
                case 5:
                    showLibraryStatus();
                    break;
                case 6:
                    showMemberBorrowedItems();
                    break;
                case 7:
                    showLogs();
                    break;
                case 8:
                    System.out.println("Terima kasih telah menggunakan sistem perpustakaan!");
                    return;
                default:
                    System.out.println("Opsi tidak valid!");
            }
            
            // scanner.nextLine();
        }
    }
    
    private void showMenu() {
        System.out.println("\n=== MENU UTAMA ===");
        System.out.println("1. Tambah Item (Buku/DVD)");
        System.out.println("2. Tambah Anggota");
        System.out.println("3. Pinjam Item");
        System.out.println("4. Kembalikan Item");
        System.out.println("5. Lihat Status Perpustakaan");
        System.out.println("6. Lihat Item yang Dipinjam Anggota");
        System.out.println("7. Lihat Log Aktivitas");
        System.out.println("8. Keluar");
    }
    
    private void addItemMenu() {
        System.out.println("\n=== TAMBAH ITEM ===");
        System.out.println("1. Tambah Buku");
        System.out.println("2. Tambah DVD");
        
        int choice = getIntInput("Pilih jenis item: ");
        
        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                addDVD();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }
    
    private void addBook() {
        System.out.print("Masukkan judul buku: ");
        String title = scanner.nextLine();
        
        int itemId = getIntInput("Masukkan ID item: ");
        
        System.out.print("Masukkan nama penulis: ");
        String author = scanner.nextLine();
        
        Book book = new Book(title, itemId, author);
        System.out.println(library.addItem(book));
    }
    
    private void addDVD() {
        System.out.print("Masukkan judul DVD: ");
        String title = scanner.nextLine();
        
        int itemId = getIntInput("Masukkan ID item: ");
        int duration = getIntInput("Masukkan durasi (menit): ");
        
        DVD dvd = new DVD(title, itemId, duration);
        System.out.println(library.addItem(dvd));
    }
    
    private void addMemberMenu() {
        System.out.println("\n=== TAMBAH MEMBER ===");
        System.out.print("Masukkan nama member: ");
        String name = scanner.nextLine();
        
        int memberId = getIntInput("Masukkan ID member: ");
        
        Member member = new Member(name, memberId);
        library.addMember(member);
        System.out.println("Member " + name + " berhasil ditambahkan");
    }
    
    private void borrowItemMenu() {
        System.out.println("\n=== PINJAM ITEM ===");
        int memberId = getIntInput("Masukkan ID member: ");
        int itemId = getIntInput("Masukkan ID item: ");
        int days = getIntInput("Masukkan jumlah hari peminjaman: ");
        
        System.out.println(library.borrowItem(memberId, itemId, days));
    }
    
    private void returnItemMenu() {
        System.out.println("\n=== KEMBALIKAN ITEM ===");
        int memberId = getIntInput("Masukkan ID member: ");
        int itemId = getIntInput("Masukkan ID item: ");
        int daysLate = getIntInput("Masukkan jumlah hari keterlambatan: ");
        
        System.out.println(library.returnItem(memberId, itemId, daysLate));
    }
    
    private void showLibraryStatus() {
        System.out.println("\n" + library.getLibraryStatus());
    }
    
    private void showMemberBorrowedItems() {
        System.out.println("\n=== ITEM YANG DIPINJAM MEMBER ===");
        int memberId = getIntInput("Masukkan ID member: ");
        
        try {
            Member member = library.findMemberById(memberId);
            System.out.println("Member: " + member.getName());
            member.getBorrowedItems();
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void showLogs() {
        System.out.println("\n=== LOG AKTIVITAS ===");
        System.out.println(library.getAllLogs());
    }

    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int result = Integer.parseInt(scanner.nextLine());
                return result;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }
    
    public static void main(String[] args) {
        LibraryCLI cli = new LibraryCLI();
        cli.start();
    }
}