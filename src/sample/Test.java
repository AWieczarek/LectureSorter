package sample;

public class Test {
    private int ocena;
    private String imie;
    private String nazwisko;

    public Test(int ocena, String imie, String nazwisko) {
        this.ocena = ocena;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}
