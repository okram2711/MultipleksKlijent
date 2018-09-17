package projektovanje.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.nalog.Nalog;
import projektovanje.bin.plata.Plata;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.connect.KonekcijaNET;

import projektovanje.dto.DTOZaposleni;
import projektovanje.dto.IDTO;

public class AdministratorController {
    
    private static final KonekcijaNET konekcija=KonekcijaNET.getInstance();

    public AdministratorController() {

    }

    public static String dodajZaposlenog(String ime, String prezime, String jmbg,
            String username, String password, String pozicija) {
        String odgovor = "NOK";
        try {
            konekcija.os.writeObject(("ADD_EMPLOYEE#" + ime + "#" + prezime + "#"
                    + jmbg + "#" + username + "#" + password+"#")+pozicija);
            odgovor = (String) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;
    }

   public static List<List<? extends IDTO>> prikaziZaposlene() {
        List<List<? extends IDTO>> listaZaposlenih = new ArrayList<>();
        try {
            konekcija.os.writeObject(new String("LIST_EMPLOYEES"));
            listaZaposlenih = (ArrayList<List<?extends IDTO>>) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaZaposlenih;
    }

    public static boolean azurirajZaposlenog(DTOZaposleni dtoZaposleni) {
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

    public static String brisanjeZaposlenog(String username) {
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
