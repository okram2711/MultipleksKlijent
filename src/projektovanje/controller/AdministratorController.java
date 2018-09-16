package projektovanje.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.connect.Konekcija;

import projektovanje.dto.DTOZaposleni;

public class AdministratorController {

    public AdministratorController() {

    }

    public String dodajZaposlenog(String ime, String prezime, String password, String email, String pozicija) {
        Konekcija konekcija = new Konekcija();
        String odgovor = "NOK";
        try {
            konekcija.os.writeObject(("ADD_EMPLOYEE#" + ime + "#" + prezime + "#" + password + "#" + email + "#" + pozicija));
            odgovor = (String) konekcija.is.readObject();
            if (odgovor.equals("OK")) {
                return odgovor;
            }
        } catch (IOException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;

    }

    public List<DTOZaposleni> prikaziZaposlene() {
        Konekcija konekcija = new Konekcija();
        ArrayList<DTOZaposleni> zaposleni = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_EMPLOYEES"));
            zaposleni = (ArrayList<DTOZaposleni>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return zaposleni;
    }

    public boolean azurirajZaposlenog(Integer idZaposlenog, Plata plata, String ime, String prezime, String JMBG, Nalog nalog) {
        Zaposleni zaposleni = new Zaposleni(idZaposlenog, plata, ime, prezime, JMBG, nalog);
        Konekcija konekcija = new Konekcija();
        DTOZaposleni dtoZaposleni = new DTOZaposleni(zaposleni);
        try {
            konekcija.os.writeObject(new String("UPDATE_EMPLOYEE"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")) {
                konekcija.os.writeObject(dtoZaposleni);
                if (((String) konekcija.is.readObject()).equals("OK")) {
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String brisanjeZaposlenog(String username) {
        Konekcija konekcija = new Konekcija();
        String odgovor = "NOK";
        try {
            konekcija.os.writeObject(("DELETE_EMPLOYEE#" + username));
            odgovor = (String) konekcija.is.readObject();
            if (odgovor.equals("OK")) {
                return odgovor;
            }
        } catch (IOException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;

    }

}
