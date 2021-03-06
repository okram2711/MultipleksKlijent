package projektovanje.bin.zaposleni;

import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;

public class Skladistar extends Zaposleni {

    public Integer getIdSkladistara() {
        return this.getIdZaposlenog();
    }

    public void setIdSkladistara(Integer idSkladistara) {
        this.setIdZaposlenog(idSkladistara);
    }

    public Skladistar(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog, Integer idSkladistara) {
        super(idZaposlenog, plata, ime, prezime, JMBG, nalog);
    }

    public Skladistar() {
    }

    public Skladistar(Integer idSkladistara) {
        this.setIdZaposlenog(idSkladistara);
    }
}
