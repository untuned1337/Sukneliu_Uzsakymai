package myClasses;

public class Klientas {
    private static int counter = 0;
    private int id;
    private String _vardas;
    private String _pavarde;
    private String _telNr;



    public Klientas(String _vardas, String _pavarde, String _telNr) {
        id = counter++;
        this._vardas = _vardas;
        this._pavarde = _pavarde;
        this._telNr = _telNr;
    }

    public int getId() {
        return id;
    }

    public String get_vardas() {
        return _vardas;
    }

    public String get_pavarde() {
        return _pavarde;
    }

    public String get_telNr() {
        return _telNr;
    }
}
