package projektovanje.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.oprema.IOprema;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.transakcije.UlaznaFaktura;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.connect.Konekcija;
import projektovanje.dto.DTOPlata;
import projektovanje.dto.DTOUlaznaFaktura;

public class RacunovodjaController {

    public RacunovodjaController() {

    }

    public boolean dodajPlatu(Integer id, Double doprinosZaPenziono, Double doprinosZaZdravstveno, Double doprinosZaDjecijuZastitu, Double doprinosZaZaposljavanje, Double stopaPoreza, Double stopaZaPenziono, Double stopaZaZdravstveno, Double stopaZaDjecijuZastitu, Double stopaZaZaposljavanje, Double netoTekuciRad, Double netoMinuliRad, Double bruto, Double porezNaPlatu, Date datumOd, Date datumDo) {
        Konekcija konekcija = new Konekcija();
        Plata plata = new Plata(id, doprinosZaPenziono, doprinosZaZdravstveno, doprinosZaDjecijuZastitu, doprinosZaZaposljavanje, stopaPoreza, stopaZaPenziono, stopaZaZdravstveno, stopaZaDjecijuZastitu, stopaZaZaposljavanje, netoTekuciRad, netoMinuliRad, bruto, porezNaPlatu, datumOd, datumDo);
        DTOPlata dtoPlata = new DTOPlata(plata);
        try {
            konekcija.os.writeObject(new String("ADD_PAYMENT"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoPlata);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean azurirajPlatu(Integer id, Double doprinosZaPenziono, Double doprinosZaZdravstveno, Double doprinosZaDjecijuZastitu, Double doprinosZaZaposljavanje, Double stopaPoreza, Double stopaZaPenziono, Double stopaZaZdravstveno, Double stopaZaDjecijuZastitu, Double stopaZaZaposljavanje, Double netoTekuciRad, Double netoMinuliRad, Double bruto, Double porezNaPlatu, Date datumOd, Date datumDo) {
        Konekcija konekcija = new Konekcija();
        Plata plata = new Plata(id, doprinosZaPenziono, doprinosZaZdravstveno, doprinosZaDjecijuZastitu, doprinosZaZaposljavanje, stopaPoreza, stopaZaPenziono, stopaZaZdravstveno, stopaZaDjecijuZastitu, stopaZaZaposljavanje, netoTekuciRad, netoMinuliRad, bruto, porezNaPlatu, datumOd, datumDo);
        DTOPlata dtoPlata = new DTOPlata(plata);
        try {
            konekcija.os.writeObject(new String("UPDATE_PAYMENT"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoPlata);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<DTOPlata> pregledajListuPlata() {
        Konekcija konekcija = new Konekcija();
        ArrayList<DTOPlata> listaPlata = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_PAYMENT"));
            listaPlata = (ArrayList<DTOPlata>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPlata;
    }

    public boolean dodajFakturu(Integer idFakute, Zaposleni zaposleni, String brojRacina, String vrstaTransakcije, Double kolicina, String jedinicaMjere, Double cijena, String kupac, Date datum, List<? extends IOprema> kupljenaRoba) {
        Konekcija konekcija = new Konekcija();
        UlaznaFaktura faktura = new UlaznaFaktura(idFakute, zaposleni, brojRacina, vrstaTransakcije, kolicina, jedinicaMjere, cijena, kupac, datum, kupljenaRoba);
        DTOUlaznaFaktura dtoFaktura = new DTOUlaznaFaktura(faktura);
        try {
            konekcija.os.writeObject(new String("ADD_INVOICE"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoFaktura);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean azurirajFakturu(Integer idFakute, Zaposleni zaposleni, String brojRacina, String vrstaTransakcije, Double kolicina, String jedinicaMjere, Double cijena, String kupac, Date datum, List<? extends IOprema> kupljenaRoba) {
        Konekcija konekcija = new Konekcija();
        UlaznaFaktura faktura = new UlaznaFaktura(idFakute, zaposleni, brojRacina, vrstaTransakcije, kolicina, jedinicaMjere, cijena, kupac, datum, kupljenaRoba);
        DTOUlaznaFaktura dtoFaktura = new DTOUlaznaFaktura(faktura);
        try {
            konekcija.os.writeObject(new String("UPDATE_INVOICE"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoFaktura);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<DTOUlaznaFaktura> pregledajListuFaktura() {
        Konekcija konekcija = new Konekcija();
        ArrayList<DTOUlaznaFaktura> listaFaktura = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_INVOICES"));
            listaFaktura = (ArrayList<DTOUlaznaFaktura>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RacunovodjaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFaktura;
    }
    
    

}
