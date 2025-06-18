import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class Mobil extends Kendaraan implements IBergerak, IServiceable {
    private int jumlahPintu;
    private double kapasitasMesin;
    private boolean mulai;
    private boolean berhenti;
    private double kecepatan;

    public Mobil(String merek, String model) {
        super(merek, model);
    }

    public int getJumlahPintu() {
        return jumlahPintu;
    }

    public void setJumlahPintu(int jumlahPintu) {
        this.jumlahPintu = jumlahPintu;
    }

    public double getKapasitasMesin() {
        return kapasitasMesin;
    }

    public void setKapasitasMesin(double kapasitasMesin) {
        this.kapasitasMesin = kapasitasMesin;
    }

    @Override
    public double hitungPajak() {
        double pajak = kapasitasMesin * 200000;
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        System.out.println("Pajak kendaraan: " + rupiah.format(pajak));
        return pajak;
    }

    @Override
    public String getTipeKendaraan() {
        return "Mobil";
    }

    @Override
    public boolean mulai() {
        if (!mulai) {
            mulai = true;
            berhenti = false;
            System.out.println("Mobil dinyalakan.");
        }
        return mulai;
    }

    @Override
    public boolean berhenti() {
        if (!berhenti) {
            berhenti = true;
            mulai = false;
            kecepatan = 0;
            System.out.println("Mobil dimatikan.");
        }
        return berhenti;
    }

    @Override
    public double getKecepatan() {
        return kecepatan;
    }

    @Override
    public void setKecepatan(double kecepatan) {
        if (mulai && !berhenti && kecepatan >= 0) {
            this.kecepatan = kecepatan;
            System.out.println("Kecepatan mobil disetel ke " + kecepatan + " km/h.");
        } else {
            System.out.println("Mobil harus dinyalakan dulu!");
        }
    }

    @Override
    public boolean periksaKondisi() {
        boolean kondisiBaik = jumlahPintu > 0 && kapasitasMesin > 0;
        System.out.println("Pemeriksaan mobil: " + (kondisiBaik ? "Layak jalan" : "Perlu servis"));
        return kondisiBaik;
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis mobil: ganti oli, cek rem, cek ban.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        double biaya = 500000 + (jumlahPintu * 100000);
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        System.out.println("Biaya servis: " + rupiah.format(biaya));
        return biaya;
    }
}