import java.sql.DatabaseMetaData;
import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class UserInterface {

    public static void tampilkanMenu() {
        System.out.println();
        System.out.println("+=================");
        System.out.println("|   Pilih menu:  |");
        System.out.println("+----------------+");
        System.out.println("|   [C] : Create |");
        System.out.println("|   [R) : Read   |");
        System.out.println("|   [U] : Upload |");
        System.out.println("|   [D] : delete |");
        System.out.println("|   [X] : Exit   |");
        System.out.println("==================");
    }

    public static void main(String[] args) {
        database db = new database();
        System.out.println(" APLIKASI SIMPLE CRUD TEXT DATABASE");
        while(true){
            tampilkanMenu();
            Scanner sc = new Scanner(System.in);
            System.out.print(" Pilih : ");
            String pilihan = sc.nextLine();
            pilihan = pilihan.toUpperCase();

            switch (pilihan) {
                case  "C":
                    System.out.println("INFO : Anda memilih menu Create");
                    System.out.println("-------------------------------------------");
                    System.out.println("INPUT DATA BARU");
                    System.out.println("NIM          : ");
                    String nim = sc.nextLine();
                    System.out.print("NAMA MAHASISWA : ");
                    String nama = sc.nextLine();
                    System.out.print("ALAMAT       : ");
                    String alamat = sc.nextLine();
                    System.out.print("SEMESTER     : ");
                    int semester = sc.nextInt();
                    System.out.print("SKS          : ");
                    int sks = sc.nextInt();
                    System.out.print("IPK          : ");
                    double ipk = sc.nextDouble();
                    sc.nextLine();
                    System.out.println("--------------------------------------------");
                    boolean status = db.insert(nim,nama,alamat,semester,sks,ipk);
                    if(status==true){
                        System.out.println("DATA BARU BERHASIL DITAMBAHKAN");
                    }else{
                        System.out.println("NIM "+nim+" sudah ada di database");
                        System.out.println("GAGAL MENAMBAHKAN DATA BARU");
                    }
                    break;
                case "R":
                    System.out.println("INFO : Anda memilih menu Read");
                    db.view();
                    break;
                case  "U":
                    System.out.println("INFO : Anda memilih menu Update");
                    db.view();
                    System.out.println("Input key (NIM mahasiswa yang akan di updat): ");
                    String key = sc.nextLine();
                    int index = db.search(key);
                    if (index >= 0) {
                        System.out.println("Anda akan meng update data " + db.getData().get(index));
                        System.out.println("INPUT DATA BARU");
                        System.out.println("NIM          : ");
                        nim = sc.nextLine();
                        System.out.print("NAMA MAHASISWA : ");
                        nama = sc.nextLine();
                        System.out.print("ALAMAT         : ");
                        alamat = sc.nextLine();
                        System.out.print("SEMESTER       : ");
                        semester = sc.nextInt();
                        System.out.print("SKS            : ");
                        sks = sc.nextInt();
                        System.out.print("IPK            : ");
                        ipk = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("--------------------------------------------");
                        status = db.update(index, nim, nama, alamat,semester, sks, ipk);
                        if(status==true){
                            System.out.println("DATAVBERHASIL DIPERBARUI");
                        }
                        System.out.println("---------------------------------------------");
                    }else {
                        System.err.println(" Mahasisiwa dengan NIM " + key + " tidak ada di database");
                    }
;                   break;
                case  "D":
                    System.out.println("INFO : Anda memilih menu Delete");
                    db.view();
                    System.out.println("Input key(NIM mahasiswa  yang akan dihapus): ");
                    key = sc.nextLine();
                    index = db.search(key);
                    if (index >= 0) {
                        System.out.println("Apakah anda yakin akan menghapus data "+db.getData().get(index)+"? Y/N");
                        System.out.print("Pilih    : ");
                        pilihan = sc.nextLine();
                        if (pilihan.equalsIgnoreCase("Y")){
                            status = db.delete(index);
                            if(status==true){
                                System.out.println("DATA BERHASIL DI HAPUS");
                            }else {
                                System.err.println("GAGAL MENGHAOUS DATA");
                            }
                        }
                    }else{
                        System.err.println("mahasiswa dengan NIM:  " + key + " tidak ada di database");
                    }
                    break;
                case  "X":
                    System.out.println("INFO : Anda memilih menu Exit");
                    System.out.println(" APAKAH ANDA YAKIN AKAN KELUAR DARI APLIKASI? Y/N");
                    System.out.println("Pilih : ");
                    pilihan = sc.nextLine();
                    if (pilihan.equalsIgnoreCase("Y")) {
                        System.exit(0);
                    }
                    break;


            }
        }
    }
}
