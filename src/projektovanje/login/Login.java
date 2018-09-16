package projektovanje.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.connect.Konekcija;
import projektovanje.dto.DTOAdministrator;
import projektovanje.dto.DTOKinooperater;
import projektovanje.dto.DTOKlijent;
import projektovanje.dto.DTOMenadzer;
import projektovanje.dto.DTOProdavacHraneIPica;
import projektovanje.dto.DTOProdavacKarata;
import projektovanje.dto.DTORacuovodja;
import projektovanje.dto.DTOSkladistar;
import projektovanje.dto.IDTO;

public class Login {

    public Login() {

    }

    public boolean login(String username, String password) {
        Konekcija konekcija = new Konekcija();
        IDTO tipKorisnika;
        try {
            konekcija.os.writeObject(new String("LOGIN#" + username + "#" + password));
            String odgovor = (String) konekcija.is.readObject();
            if(odgovor.startsWith("OK")){
                String tip=odgovor.split("#")[1];
                if(tip.equals("ADMINISTRATOR")){
                    DTOAdministrator user=(DTOAdministrator)konekcija.is.readObject();
                }else if(tip.equals("KINOOPERATER")){
                    DTOKinooperater user=(DTOKinooperater)konekcija.is.readObject();
                }else if(tip.equals("MENADZER")){
                    DTOMenadzer user=(DTOMenadzer)konekcija.is.readObject();
                }else if(tip.equals("PRODAVACHRANEIPICA")){
                    DTOProdavacHraneIPica user=(DTOProdavacHraneIPica)konekcija.is.readObject();
                }else if(tip.equals("PRODAVACKARATA")){
                    DTOProdavacKarata user=(DTOProdavacKarata)konekcija.is.readObject();
                }else if(tip.equals("SKLADISTAR")){
                    DTOSkladistar user=(DTOSkladistar)konekcija.is.readObject();
                }else if(tip.equals("RACUNOVODJA")){
                    DTORacuovodja user=(DTORacuovodja)konekcija.is.readObject();
                }else if(tip.equals("KLIJENT")){
                    DTOKlijent user=(DTOKlijent)konekcija.is.readObject();
                }
                return true;
            }else if(odgovor.startsWith("NOK")){
                String razlog=odgovor.split("#")[1];
                tipKorisnika=(IDTO) konekcija.is.readObject();//null
                return false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String register(String NAME, String SURNAME, String PASSWORD, String EMAIL, String POSITION) {
        Konekcija konekcija = new Konekcija();
        try {
            konekcija.os.writeObject(new String("REGISTER#" + NAME + "#" + SURNAME + "#" + PASSWORD + "#" + EMAIL + "#" + POSITION));
            String odgovor = (String) konekcija.is.readObject();
            if (odgovor.equals("OK")) {
                return "OK";
            } else {
                return odgovor;
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "NOK";
    }
}
