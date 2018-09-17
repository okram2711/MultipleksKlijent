package projektovanje.login;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.connect.KonekcijaNET;

public class Login {
    
    private static final KonekcijaNET konekcija=KonekcijaNET.getInstance();

    public Login() {

    }

    public String login(String username, String password) {
        String odgovor="";
        try {
            konekcija.os.writeObject(new String("LOGIN#" + username + "#" + password));
            odgovor = (String) konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        return odgovor;
    }

    public String register(String NAME, String SURNAME, String PASSWORD, String EMAIL, String POSITION) {
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
