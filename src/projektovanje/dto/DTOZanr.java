package projektovanje.dto;

import projektovanje.bin.film.Zanr;

import java.io.Serializable;

public class DTOZanr implements Serializable, IDTO {
    Zanr zanr;

    public DTOZanr() {
    }

    public DTOZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }
}
