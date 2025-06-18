import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Sepeda extends Kendaraan implements IBergerak, IServiceable {
    private String jenisSepeda;
    private int jumlahGear;
    private int ukuranRoda;
    private double kecepatan;
    private boolean mulai;
    private boolean berhenti;

    public Sepeda(String merek, String model) {
        super(merek, model);
    }

    public String getJenisSepeda() {
        return jenisSepeda;
    }

    public void setJenisSepeda(String jenis) {
        this.jenisSepeda = jenis;
    }

    public int getJumlahGear() {
        return jumlahGear;
    }

    public void setJumlahGear(int jumlah) {
        this.jumlahGear = jumlah;
    }

    public int getUkuranRoda() {
        return ukuranRoda;
    }

    public void setUkuranRoda(int ukuran) {
        this.ukuranRoda = ukuran;
    }

    @Override
    public double hitungPajak() {
        double pajak = 0;
        System.out.println("Pajak sepeda: " + pajak);
        return pajak;
    }

    @Override
    public String getTipeKendaraan() {
        return "Sepeda";
    }

    @Override
    public boolean mulai() {
        mulai = true;
        berhenti = false;
        System.out.println("Mulai mengayuh sepeda.");
        return mulai;
    }

    @Override
    public boolean berhenti() {
        berhenti = true;
        mulai = false;
        kecepatan = 0;
        System.out.println("Sepeda berhenti.");
        return berhenti;
    }

    @Override
    public double getKecepatan() {
        return kecepatan;
    }

    @Override
    public void setKecepatan(double kecepatan) {
        if (mulai && kecepatan >= 0) {
            this.kecepatan = kecepatan;
            System.out.println("Kecepatan sepeda disetel ke " + kecepatan + " km/h.");
        } else {
            System.out.println("Kamu harus mulai mengayuh dulu.");
        }
    }

    @Override
    public boolean periksaKondisi() {
        boolean kondisiBaik = jumlahGear > 0 && ukuranRoda > 0;
        System.out.println("Pemeriksaan sepeda: " + (kondisiBaik ? "Layak jalan" : "Perlu servis"));
        return kondisiBaik;
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis sepeda: cek rem dan roda.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        double biaya = 100000; // Contoh biaya servis
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        System.out.println("Biaya servis sepeda: " + rupiah.format(biaya));
        return biaya;
    }
}