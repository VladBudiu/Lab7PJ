package Exercitiu2;

import Exercitiu2.Enums.TipChitara;

import java.util.Objects;

public class Chitara extends InstrumentMuzical{
    private TipChitara tipChitara_;
    private int nrCorzi_;

    public Chitara() {
    }

    public Chitara(String producator_, int pret, TipChitara tipChitara_, int nrCorzi_) {
        super(producator_, pret);
        this.tipChitara_ = tipChitara_;
        this.nrCorzi_ = nrCorzi_;
    }

    public TipChitara getTipChitara_() {
        return tipChitara_;
    }

    public void setTipChitara_(TipChitara tipChitara_) {
        this.tipChitara_ = tipChitara_;
    }

    public int getNrCorzi_() {
        return nrCorzi_;
    }

    public void setNrCorzi_(int nrCorzi_) {
        this.nrCorzi_ = nrCorzi_;
    }

    @Override
    public String toString() {
        return super.toString() + this.getClass().toString() +
                "tipChitara_=" + tipChitara_ +
                ", nrCorzi_=" + nrCorzi_ +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Chitara chitara = (Chitara) o;
        boolean isEqual =(super.equals(o)&& nrCorzi_ == chitara.nrCorzi_ && tipChitara_ == chitara.tipChitara_);
        if(!isEqual)
            System.out.println("DUPLICATA");
        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipChitara_, nrCorzi_);
    }
}
