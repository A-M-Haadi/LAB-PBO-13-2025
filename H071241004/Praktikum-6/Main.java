import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("==== MOBIL ====");
        Mobil mobil = new Mobil("Toyota", "Avanza");
        mobil.setJumlahPintu(4);
        mobil.setKapasitasMesin(1500);

        mobil.mulai();
        mobil.setKecepatan(60);
        System.out.println("Kecepatan mobil: " + mobil.getKecepatan() + " km/h");
        mobil.hitungPajak();
        mobil.periksaKondisi();
        mobil.lakukanServis();
        System.out.println("Servis berikutnya: " + sdf.format(mobil.getWaktuServisBerikutnya()));
        mobil.hitungBiayaServis();
        mobil.berhenti();
        System.out.println();

        System.out.println("==== MOTOR ====");
        Motor motor = new Motor("Honda", "CBR");
        motor.setJenisMotor("Sport");
        motor.setKapasitasTangki(12);
        motor.setTipeSuspensi("Upside-down");

        motor.mulai();
        motor.setKecepatan(80);
        System.out.println("Kecepatan motor: " + motor.getKecepatan() + " km/h");
        motor.hitungPajak();
        motor.periksaKondisi();
        motor.lakukanServis();
        System.out.println("Servis berikutnya: " + sdf.format(motor.getWaktuServisBerikutnya()));
        motor.hitungBiayaServis();
        motor.berhenti();
        System.out.println();

        System.out.println("==== SEPEDA ====");
        Sepeda sepeda = new Sepeda("Polygon", "Monarch");
        sepeda.setJenisSepeda("Gunung");
        sepeda.setJumlahGear(21);
        sepeda.setUkuranRoda(27);

        sepeda.mulai();
        sepeda.setKecepatan(25);
        System.out.println("Kecepatan sepeda: " + sepeda.getKecepatan() + " km/h");
        sepeda.hitungPajak();
        sepeda.periksaKondisi();
        sepeda.lakukanServis();
        System.out.println("Servis berikutnya: " + sdf.format(sepeda.getWaktuServisBerikutnya()));
        sepeda.hitungBiayaServis();
        sepeda.berhenti();
        System.out.println();

        System.out.println("==== SEPEDA LISTRIK ====");
        SepedaListrik sepedaListrik = new SepedaListrik("Xiaomi", "Himo C26");
        sepedaListrik.setKapasitasBaterai(480);
        sepedaListrik.setDayaMotor(250); 
        sepedaListrik.isiBaterai();

        sepedaListrik.mulai();
        sepedaListrik.setKecepatan(32);
        System.out.println("Kecepatan sepeda listrik: " + sepedaListrik.getKecepatan() + " km/h");
        sepedaListrik.hitungPajak();
        sepedaListrik.periksaKondisi();
        sepedaListrik.lakukanServis();
        System.out.println("Servis berikutnya: " + sdf.format(sepedaListrik.getWaktuServisBerikutnya()));
        sepedaListrik.hitungBiayaServis();
        sepedaListrik.berhenti();
        System.out.println();
    }
}