public class DVD extends LibraryItem {
    private int duration; // dalam menit
    
    public DVD(String title, int itemId, int duration) {
        super(title, itemId);
        this.duration = duration;
    }
    
    @Override
    public String getDescription() {
        return "DVD: " + title + ", durasi " + duration + " menit, ID: " + itemId;
    }
    
    @Override
    public String borrowItem(int days) {
        if (days > 7) {
            throw new IllegalArgumentException("DVD tidak dapat dipinjam lebih dari 7 hari");
        }
        if (isBorrowed) {
            throw new IllegalArgumentException("DVD sudah dipinjam dan belum dikembalikan");
        }
        this.isBorrowed = true;
        return "Item " + title + " berhasil dipinjam selama " + days + " hari";
    }
    
    @Override
    public double calculateFine(int daysLate) {
        return daysLate * 25000.0;
    }
    
    public int getDuration() { 
        return duration; 
    }
}