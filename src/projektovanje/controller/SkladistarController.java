package projektovanje.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.oprema.Artikal;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.connect.KonekcijaNET;
import projektovanje.dto.DTOArtikal;

public class SkladistarController {
    
    private KonekcijaNET konekcija=KonekcijaNET.getInstance();
    
    public SkladistarController(){
        
    }
    
    public boolean dodajArtikal(Integer idArtikla, String naziv, Integer kolicinaNaStanju, Double jedinicnaCijena,  String tip, String barKod, Zaposleni zaposleni){
        Artikal artikal=new Artikal(idArtikla,naziv,kolicinaNaStanju,jedinicnaCijena,tip,barKod,zaposleni);
        DTOArtikal dtoArtikal=new DTOArtikal(artikal);
        try{
            konekcija.os.writeObject(new String("ADD_PRODUCT"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")){
                konekcija.os.writeObject(dtoArtikal);
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
    
    public boolean azurirajArtikal(Integer idArtikla, String naziv, Integer kolicinaNaStanju, Double jedinicnaCijena,  String tip, String barKod, Zaposleni zaposleni){
        Artikal artikal=new Artikal(idArtikla,naziv,kolicinaNaStanju,jedinicnaCijena,tip,barKod,zaposleni);
        DTOArtikal dtoArtikal=new DTOArtikal(artikal);
        try{
            konekcija.os.writeObject(new String("UPDATE_PRODUCT"));
            if (((String) konekcija.is.readObject()).equals("WICHONE")){
                konekcija.os.writeObject(dtoArtikal);
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
    
    public List<DTOArtikal> prikaziStanje(){
        ArrayList<DTOArtikal> artikli=new ArrayList<>();
        try{
            konekcija.os.writeObject(new String("LIST_PRODUCTS"));
            artikli=(ArrayList<DTOArtikal>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SkladistarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return artikli;
    }
    
}
