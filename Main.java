package APS2;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Cuti {
    int idPegawai;
    String nama;
    LocalDate mulai;
    LocalDate selesai;

    Cuti(int idPegawai, String nama, String mulai, String selesai) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.mulai = LocalDate.parse(mulai);
        this.selesai = LocalDate.parse(selesai);
    }

    int lama() {
        return (int) (selesai.toEpochDay() - mulai.toEpochDay()) + 1;
    }

    boolean cocok(int tahun, int bulan) {
        for (LocalDate d = mulai; !d.isAfter(selesai); d = d.plusDays(1)) {
            boolean okTahun = tahun <= 0 || d.getYear() == tahun;
            boolean okBulan = bulan <= 0 || d.getMonthValue() == bulan;
            if (okTahun && okBulan) {
                return true;
            }
        }
        return false;
    }
}

class Penerbangan {
    int id;
    String asal;
    String tujuan;
    String tanggal;
    int jam;
    String maskapai;
    int harga;

    Penerbangan(int id, String asal, String tujuan, String tanggal, int jam, String maskapai, int harga) {
        this.id = id;
        this.asal = asal;
        this.tujuan = tujuan;
        this.tanggal = tanggal;
        this.jam = jam;
        this.maskapai = maskapai;
        this.harga = harga;
    }

    void tampil() {
        System.out.printf("[%d] %s -> %s | %s jam %02d:00 | %s | Rp%,d%n",
                id, asal, tujuan, tanggal, jam, maskapai, harga);
    }
}

class Pemesanan {
    String kode;
    int idPenerbangan;
    int idPengguna;
    String status;

    Pemesanan(String kode, int idPenerbangan, int idPengguna, String status) {
        this.kode = kode;
        this.idPenerbangan = idPenerbangan;
        this.idPengguna = idPengguna;
        this.status = status;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cuti> daftarCuti = new ArrayList<>();
    static ArrayList<Penerbangan> daftarPenerbangan = new ArrayList<>();
    static ArrayList<Pemesanan> daftarPemesanan = new ArrayList<>();
    static int urutKode = 0;

    public static void main(String[] args) {
        isiDataAwal();
        int pilihan = -1;
        while (pilihan != 0) {
            tampilMenu();
            pilihan = bacaInt("Pilih menu: ");
            switch (pilihan) {
                case 1: lihatCuti(); break;
                case 2: riwayatCuti(); break;
                case 3: cariPenerbangan(); break;
                case 4: pesanTiket(); break;
                case 5: batalkanTiket(); break;
                case 0: System.out.println("Sampai jumpa."); break;
                default: belumDibuat();
            }
        }
    }

    static void tampilMenu() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Lihat jadwal cuti (HRD)");
        System.out.println("2. Riwayat & sisa cuti pegawai (HRD)");
        System.out.println("3. Cari penerbangan");
        System.out.println("4. Pesan tiket");
        System.out.println("5. Batalkan tiket");
        System.out.println("6-12. (belum dikerjakan)");
        System.out.println("0. Keluar");
    }

    static void isiDataAwal() {
        daftarCuti.add(new Cuti(1, "Budi", "2026-12-20", "2026-12-24"));
        daftarCuti.add(new Cuti(2, "Sari", "2026-12-22", "2026-12-23"));
        daftarPenerbangan.add(new Penerbangan(1, "SUB", "CGK", "2026-12-01", 8, "Garuda", 1200000));
        daftarPenerbangan.add(new Penerbangan(2, "SUB", "DPS", "2026-12-02", 10, "Lion Air", 750000));
    }

    static String bacaTeks(String pesan) {
        System.out.print(pesan);
        return sc.nextLine().trim();
    }

    static int bacaInt(String pesan) {
        try {
            return Integer.parseInt(bacaTeks(pesan));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static void belumDibuat() {
        System.out.println("Fitur belum diimplementasikan.");
    }

    // ---------- US1 ----------
    static void lihatCuti() {
        int tahun = bacaInt("Tahun (kosong = semua): ");
        int bulan = bacaInt("Bulan 1-12 (kosong = semua): ");
        for (Cuti c : daftarCuti) {
            if (c.cocok(tahun, bulan)) {
                System.out.println(c.nama + " | " + c.mulai + " s/d " + c.selesai + " | " + c.lama() + " hari");
            }
        }
    }

    static void riwayatCuti() {
        belumDibuat();
    }

    // ---------- US2, US3 ----------
    static void cariPenerbangan() {
        String tujuan = bacaTeks("Tujuan, pisahkan koma bila lebih dari satu (mis. CGK,DPS): ");
        String[] daftarTujuan = tujuan.isEmpty() ? new String[0] : tujuan.split(",");
        for (Penerbangan f : daftarPenerbangan) {
            boolean cocok = daftarTujuan.length == 0;
            for (String t : daftarTujuan) {
                if (f.tujuan.equalsIgnoreCase(t.trim())) {
                    cocok = true;
                }
            }
            if (cocok) {
                f.tampil();
            }
        }
    }

    static void pesanTiket() {
        int idPenerbangan = bacaInt("ID penerbangan: ");
        int idPengguna = bacaInt("ID pengguna: ");

        urutKode++;
        String kode = "BK" + String.format("%04d", urutKode);
        daftarPemesanan.add(new Pemesanan(kode, idPenerbangan, idPengguna, "booked"));
        System.out.println("Pemesanan berhasil. Kode: " + kode);
    }

    static void batalkanTiket() {

        belumDibuat();
    }
}