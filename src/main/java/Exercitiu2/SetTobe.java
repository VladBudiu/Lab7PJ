package Exercitiu2;

import Exercitiu2.Enums.TipTobe;

import java.util.Objects;

public class SetTobe extends InstrumentMuzical{
    private TipTobe tipTobe_;
    private int nrTobe_;
    private int nrCinele_;

    public SetTobe() {
    }

    public SetTobe(String producator_, int pret, TipTobe tipTobe_, int nrTobe_, int nrCinele_) {
        super(producator_, pret);
        this.tipTobe_ = tipTobe_;
        this.nrTobe_ = nrTobe_;
        this.nrCinele_ = nrCinele_;
    }

    public TipTobe getTipTobe_() {
        return tipTobe_;
    }

    public void setTipTobe_(TipTobe tipTobe_) {
        this.tipTobe_ = tipTobe_;
    }

    public int getNrTobe_() {
        return nrTobe_;
    }

    public void setNrTobe_(int nrTobe_) {
        this.nrTobe_ = nrTobe_;
    }

    public int getNrCinele_() {
        return nrCinele_;
    }

    public void setNrCinele_(int nrCinele_) {
        this.nrCinele_ = nrCinele_;
    }

    @Override
    public String toString() {
        return super.toString()+ " "+this.getClass().toString()+" " +
                "tipTobe_=" + tipTobe_ +
                ", nrTobe_=" + nrTobe_ +
                ", nrCinele_=" + nrCinele_ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            System.out.println("Nu e bun ca sunt la fel");
            return true;}
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SetTobe setTobe = (SetTobe) o;
        boolean isEqual=(super.equals(o)&& nrTobe_ == setTobe.nrTobe_ && nrCinele_ == setTobe.nrCinele_ && tipTobe_ == setTobe.tipTobe_);
        if(!isEqual)
            System.out.println("DUPLICATA");
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipTobe_, nrTobe_, nrCinele_);
    }
}
