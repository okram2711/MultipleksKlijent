package projektovanje.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.karta.Karta;
import projektovanje.bin.projekcija.Projekcija;
import projektovanje.connect.KonekcijaNET;
import projektovanje.dto.DTOKarta;
import projektovanje.dto.DTOProjekcija;
import projektovanje.dto.DTORepertoar;

public class ProdavacKarataController {
    
    private static KonekcijaNET konekcija=KonekcijaNET.getInstance();

    public ProdavacKarataController() {

    }

    public static boolean prodajKartu(Integer idKarte, Date datumIzdavanja, Double cijena, Projekcija projekcija) {
        Karta karta = new Karta(idKarte, datumIzdavanja, cijena, projekcija);
        DTOKarta dtoKarta = new DTOKarta(karta);
        try {
            konekcija.os.writeObject("SELL_TICKET");
            if (((String) konekcija.is.readObject()).equals("WICHONE")){
            konekcija.os.writeObject(dtoKarta);
            if (((String)konekcija.is.readObject()).equals("OK")){
                return true;
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(ProdavacKarataController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdavacKarataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean rezervisiKartu(Integer idKarte, Date datumIzdavanja, Double cijena, Projekcija projekcija) {
        Karta karta = new Karta(idKarte, datumIzdavanja, cijena, projekcija);
        DTOKarta dtoKarta = new DTOKarta(karta);
        try {
            konekcija.os.writeObject("RESERVE_TICKET");
            if (((String) konekcija.is.readObject()).equals("WICHONE")){
            konekcija.os.writeObject(dtoKarta);
            if (((String)konekcija.is.readObject()).equals("OK")){
                return true;
            }
            } 
        } catch (IOException ex) {
            Logger.getLogger(ProdavacKarataController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProdavacKarataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void ponistiRezervaciju(Integer idKarte, Date datumIzdavanja, Double cijena, Projekcija projekcija) {
        Karta karta = new Karta(idKarte, datumIzdavanja, cijena, projekcija);
        DTOKarta dtoKarta = new DTOKarta(karta);
        try {
            konekcija.os.writeObject("CANCEL_RESERVATION");
            konekcija.os.writeObject(dtoKarta);
        } catch (IOException ex) {
            Logger.getLogger(ProdavacKarataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<DTOProjekcija> pregledProjekcija(){
        ArrayList<DTOProjekcija> projekcije=new ArrayList<>();
        try{
            konekcija.os.writeObject("LIST_PROJECTIONS");
            projekcije=(ArrayList<DTOProjekcija>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projekcije;
    }
    
    public List<DTORepertoar> pregledRepertoara(){
        ArrayList<DTORepertoar> repertoar=new ArrayList<>();
        try{
            konekcija.os.writeObject("LIST_REPERTOIRE");
            repertoar=(ArrayList<DTORepertoar>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(MenadzerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MenadzerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return repertoar;
    }

}
