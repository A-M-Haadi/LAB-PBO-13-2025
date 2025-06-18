import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SepedaListrik extends Kendaraan implements IBergerak, IServiceable {
    private double kapasitasBaterai;    
    private int dayaMotor;              
    private boolean bateraiTerisi;
    private double kecepatan;

    private boolean mulai;
    private boolean berhenti;

    public SepedaListrik(String merek, String model) {
        super(merek, model);
    }

    public double getKapasitasBaterai() {
        return kapasitasBaterai;
    }

    public void setKapasitasBaterai(double kapasitasBaterai) {
        this.kapasitasBaterai = kapasitasBaterai;
    }

    public int getDayaMotor() {
        return dayaMotor;
    }

    public void setDayaMotor(int dayaMotor) {
        this.dayaMotor = dayaMotor;
    }

    public boolean isBateraiTerisi() {
        return bateraiTerisi;
    }

    public void isiBaterai() {
        bateraiTerisi = true;
        System.out.println("Baterai sepeda listrik telah diisi.");
    }

    @Override
    public double hitungPajak() {
        System.out.println("Sepeda listrik bebas pajak.");
        return 0;
    }

    @Override
    public String getTipeKendaraan() {
        return "Sepeda Listrik";
    }

    @Override
    public boolean mulai() {
        if (!bateraiTerisi) {
            System.out.println("Baterai habis! Harap isi ulang terlebih dahulu.");
            return false;
        }
        mulai = true;
        berhenti = false;
        System.out.println("Sepeda listrik dinyalakan.");
        return true;
    }

    @Override
    public boolean berhenti() {
        berhenti = true;
        mulai = false;
        kecepatan = 0;
        System.out.println("Sepeda listrik berhenti.");
        return true;
    }

    @Override
    public double getKecepatan() {
        return kecepatan;
    }

    @Override
    public void setKecepatan(double kecepatan) {
        if (mulai && kecepatan >= 0) {
            this.kecepatan = kecepatan;
            System.out.println("Kecepatan sepeda listrik disetel ke " + kecepatan + " km/h.");
        } else {
            System.out.println("Sepeda listrik harus dinyalakan terlebih dahulu!");
        }
    }

    @Override
    public boolean periksaKondisi() {
        boolean kondisiBaik = bateraiTerisi && kapasitasBaterai > 0;
        System.out.println("Pemeriksaan sepeda listrik: " + (kondisiBaik ? "Layak jalan" : "Perlu servis"));
        return kondisiBaik;
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis sepeda listrik: cek baterai, cek rem, dan cek motor.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        double biaya = 250000;
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        System.out.println("Biaya servis sepeda listrik: " + rupiah.format(biaya));
        return biaya;
    }
}