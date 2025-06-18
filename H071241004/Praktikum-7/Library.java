import java.util.*;

public class Library {
    private List<LibraryItem> items;
    private List<Member> members;
    private LibraryLogger logger;
    
    public Library() {
        this.items = new ArrayList<>();
        this.members = new ArrayList<>();
        this.logger = new LibraryLogger();
    }
    
    public String addItem(LibraryItem item) {
        items.add(item);
        String result = item.getTitle() + " berhasil ditambahkan";
        logger.logActivity("Item " + item.getTitle() + " ditambahkan ke perpustakaan");
        return result;
    }
    
    public LibraryItem findItemById(int itemId) {
        for (LibraryItem item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        throw new NoSuchElementException("Item dengan ID " + itemId + " tidak ditemukan");
    }
    
    public String getLibraryStatus() {
        StringBuilder status = new StringBuilder();
        status.append("=== STATUS PERPUSTAKAAN ===\n");
        
        if (items.isEmpty()) {
            status.append("Perpustakaan kosong - belum ada item yang ditambahkan.\n");
            return status.toString();
        }
        
        // Header tabel
        status.append(String.format("%-5s | %-30s | %-15s | %-10s\n", "ID", "Judul", "Jenis", "Status"));
        status.append("------|--------------------------------|-----------------|------------\n");
        
        // Data items
        for (LibraryItem item : items) {
            String itemType = (item instanceof Book) ? "Buku" : "DVD";
            String itemStatus = item.isBorrowed() ? "Dipinjam" : "Tersedia";
            status.append(String.format("%-5d | %-30s | %-15s | %-10s\n", 
                item.getItemId(),
                item.getTitle().length() > 30 ? item.getTitle().substring(0, 27) + "..." : item.getTitle(),
                itemType,
                itemStatus));
        }
        
        status.append("\nTotal item: ").append(items.size());
        long availableItems = items.stream().filter(item -> !item.isBorrowed()).count();
        status.append(" | Tersedia: ").append(availableItems);
        status.append(" | Dipinjam: ").append(items.size() - availableItems);
        
        return status.toString();
    }
    
    public String getAllLogs() {
        return logger.getLogs();
    }
    
    public void addMember(Member member) {
        members.add(member);
        logger.logActivity("Member " + member.getName() + " ditambahkan");
    }
    
    public Member findMemberById(int memberId) {
        for (Member member : members) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        throw new NoSuchElementException("Member dengan ID " + memberId + " tidak ditemukan");
    }
    
    public String borrowItem(int memberId, int itemId, int days) {
        try {
            Member member = findMemberById(memberId);
            LibraryItem item = findItemById(itemId);
            
            String result = member.borrow(item, days);
            String itemType = (item instanceof Book) ? "Buku" : "DVD";
            logger.logActivity(itemType + " " + item.getTitle() + " dipinjam oleh " + member.getName());
            return result;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    public String returnItem(int memberId, int itemId, int daysLate) {
        try {
            Member member = findMemberById(memberId);
            LibraryItem item = findItemById(itemId);
            
            String result = member.returnItem(item, daysLate);
            String itemType = (item instanceof Book) ? "Buku" : "DVD";
            logger.logActivity(itemType + " " + item.getTitle() + " dikembalikan oleh " + member.getName());
            return result;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
    
    // Getters
    public List<LibraryItem> getItems() { 
        return items; 
    }
    
    public List<Member> getMembers() { 
        return members; 
    }
    
    public LibraryLogger getLogger() { 
        return logger; 
    }
}