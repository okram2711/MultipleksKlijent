package projektovanje.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projektovanje.bin.oprema.Oprema;
import projektovanje.bin.zaposleni.Zaposleni;
import projektovanje.connect.Konekcija;
import projektovanje.dto.DTOOprema;


public class KinooperaterController {
    
    public KinooperaterController(){
        
    }
    
    public boolean dodajOpremu(Integer idOpreme, Integer brojInventara, String naziv, Boolean ispravnost, Zaposleni zaposleni){
        Konekcija konekcija=new Konekcija();
        Oprema oprema=new Oprema(idOpreme,brojInventara,naziv,ispravnost,zaposleni);
        DTOOprema dtoOprema=new DTOOprema(oprema);
        try{
            konekcija.os.writeObject(new String("ADD_EQUIPMENT"));
            if(((String)konekcija.is.readObject()).equals("WHICHONE")){
                konekcija.os.writeObject(dtoOprema);
                if(((String)konekcija.is.readObject()).equals("OK")){
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean azurirajOpremu(Integer idOpreme, Integer brojInventara, String naziv, Boolean ispravnost, Zaposleni zaposleni){
        Konekcija konekcija=new Konekcija();
        Oprema oprema=new Oprema(idOpreme,brojInventara,naziv,ispravnost,zaposleni);
        DTOOprema dtoOprema=new DTOOprema(oprema);
        try{
            konekcija.os.writeObject(new String("UPDATE_EQUIPMENT"));
            if(((String)konekcija.is.readObject()).equals("WHICHONE")){
                konekcija.os.writeObject(dtoOprema);
                if(((String)konekcija.is.readObject()).equals("OK")){
                    return true;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public List<DTOOprema> pregledajOpremu(){
        Konekcija konekcija=new Konekcija();
        ArrayList<DTOOprema> oprema=new ArrayList<>();
        try{
            konekcija.os.writeObject(new String("LIST_EQUIPMENT"));
            oprema=(ArrayList<DTOOprema>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oprema;
    }
    
    public List<DTOOprema> pretraziOpremu(String name){
        Konekcija konekcija=new Konekcija();
        ArrayList<DTOOprema> oprema=new ArrayList<>();
        try{
            konekcija.os.writeObject(new String("GET_EQUIPMENT#"+name));
            oprema=(ArrayList<DTOOprema>)konekcija.is.readObject();
        } catch (IOException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KinooperaterController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return oprema;
    }
}
