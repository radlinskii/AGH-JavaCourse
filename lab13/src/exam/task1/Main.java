package exam.task1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVReader reader = new CSVReader("info_pracownicy.csv", ";", true);
        int index = 0;
        int licznikKobiet = 0;
        while (reader.next()) {
            String imie = reader.get("Imie");
            if(imie.endsWith("a")){
                licznikKobiet++;
            }
            String nazwisko = reader.get("Nazwisko ");
            String stanowisko = reader.get("Stanowisko");
            if(stanowisko.contains("pecjalist")){
                System.out.println("Specjalista : " + imie + " " + nazwisko);
            } else if(!stanowisko.contains("tażyst") && !stanowisko.contains("systent") && !stanowisko.contains("ekretarka")) {
                System.out.println("nie specjalista ani stazysta ani asystent ani sekretarka : " + imie + " " + nazwisko + " " + stanowisko);
            }
        }
        System.out.println("naliczylo " + licznikKobiet + " kobiet");
    }
}
