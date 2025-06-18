import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Motor extends Kendaraan implements IBergerak, IServiceable {
    private String jenisMotor;
    private double kapasitasTangki;
    private String tipeSuspensi;
    private double kecepatan;
    private boolean mulai;
    private boolean berhenti;

    public Motor(String merek, String model) {
        super(merek, model);
    }

    public String getJenisMotor() {
        return jenisMotor;
    }

    public void setJenisMotor(String jenis) {
        this.jenisMotor = jenis;
    }

    public double getKapasitasTangki() {
        return kapasitasTangki;
    }

    public void setKapasitasTangki(double kapasitas) {
        this.kapasitasTangki = kapasitas;
    }

    public String getTipeSuspensi() {
        return tipeSuspensi;
    }

    public void setTipeSuspensi(String tipe) {
        this.tipeSuspensi = tipe;
    }

    @Override
    public double hitungPajak() {
        double pajak = kapasitasTangki * 100000;
        System.out.println("Pajak motor: " + pajak);
        return pajak;
    }

    @Override
    public String getTipeKendaraan() {
        return "Motor";
    }

    @Override
    public boolean mulai() {
        if (!mulai) {
            mulai = true;
            berhenti = false;
            System.out.println("Motor dinyalakan.");
        }
        return mulai;
    }

    @Override
    public boolean berhenti() {
        if (!berhenti) {
            berhenti = true;
            mulai = false;
            kecepatan = 0;
            System.out.println("Motor dimatikan.");
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
            System.out.println("Kecepatan motor disetel ke " + kecepatan + " km/h.");
        } else {
            System.out.println("Motor harus dinyalakan dulu!");
        }
    }

    @Override
    public boolean periksaKondisi() {
        boolean kondisiBaik = kapasitasTangki > 0;
        System.out.println("Pemeriksaan motor: " + (kondisiBaik ? "Layak jalan" : "Perlu servis"));
        return kondisiBaik;
    }

    @Override
    public void lakukanServis() {
        System.out.println("Servis motor: ganti oli, cek rem.");
    }

    @Override
    public Date getWaktuServisBerikutnya() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 6);
        return cal.getTime();
    }

    @Override
    public double hitungBiayaServis() {
        double biaya = 300000;
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("in", "ID"));
        System.out.println("Biaya servis motor: " + rupiah.format(biaya));
        return biaya;
    }
}