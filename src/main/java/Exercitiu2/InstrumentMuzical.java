package Exercitiu2;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class InstrumentMuzical {
    private  String producator_;
    private int pret;

    public InstrumentMuzical() {
    }

    public InstrumentMuzical(String producator_, int pret) {
        this.producator_ = producator_;
        this.pret = pret;
    }

    public String getProducator_() {
        return producator_;
    }

    public void setProducator_(String producator_) {
        this.producator_ = producator_;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "InstrumentMuzical{" +
                "producator_='" + producator_ + '\'' +
                ", pret=" + pret +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {
            System.out.println("Nu e bun ca sunt la fel");
            return true;}
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentMuzical that = (InstrumentMuzical) o;
         return pret == that.pret && Objects.equals(producator_, that.producator_);

    }

    @Override
    public int hashCode() {
        return Objects.hash(producator_, pret);
    }
}
