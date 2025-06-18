import java.util.*;

public class Member {
    private String name;
    private int memberId;
    private List<LibraryItem> borrowedItems;
    
    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedItems = new ArrayList<>();
    }
    
    public String borrow(LibraryItem item, int days) {
        if (item.isBorrowed()) {
            throw new IllegalStateException("Item tidak tersedia (sudah dipinjam)");
        }
        
        String result = item.borrowItem(days);
        borrowedItems.add(item);
        return result;
    }
    
    public String returnItem(LibraryItem item, int daysLate) {
        if (!borrowedItems.contains(item)) {
            throw new IllegalStateException("Item tidak ada dalam daftar peminjaman member");
        }
        

        borrowedItems.remove(item);
        double fine = item.calculateFine(daysLate);
        
        return "Item " + item.getTitle() + " berhasil dikembalikan dengan denda: Rp " + 
               String.format("%,.0f", fine);
    }
    
    public void getBorrowedItems() {
        if (borrowedItems.isEmpty()) {
            System.out.println("Tidak ada item yang dipinjam");
        } else {
            System.out.println("Daftar item yang dipinjam:");
            for (LibraryItem item : borrowedItems) {
                System.out.println("- " + item.getDescription());
            }
        }
    }
    
    // Getters
    public String getName() { 
        return name; 
    }
    
    public int getMemberId() { 
        return memberId; 
    }
    
    public List<LibraryItem> getBorrowedItemsList() { 
        return borrowedItems; 
    }
}