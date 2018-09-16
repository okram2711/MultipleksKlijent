package projektovanje.dto;

import projektovanje.bin.oprema.Oprema;

import java.io.Serializable;

public class DTOOprema implements Serializable, IDTO {
    Oprema oprema;

    public Oprema getOprema() {
        return oprema;
    }

    public void setOprema(Oprema oprema) {
        this.oprema = oprema;
    }

    public DTOOprema(Oprema oprema) {
        this.oprema = oprema;
    }

    public DTOOprema() {
    }
}
