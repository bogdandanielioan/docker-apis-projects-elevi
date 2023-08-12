package ro.mycode.autovitapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
@Entity(name="Masina")
@Table(name="masini")
public class Masina implements Comparable<Masina> {
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Id
    private Long id ;

    @Size(min=3,message ="min marca length should be 3")
    private String marca;
    @Size(min=1,message ="min marca length should be 1")
    private String model;
    @Min(value=1886,message = "min year that can be")
    private int an;
    @Size(min=3,message ="min marca length should be 3")
    private String culoare;

    public Masina(String marca, String model, int an, String culoare) {
        this.marca = marca;
        this.model = model;
        this.an = an;
        this.culoare = culoare;
    }

    @Override
    public String toString(){
        String text="Id : "+this.id+"\n";
        text+="Marca : "+this.marca+"\n";
        text+="Model : "+this.model+"\n";
        text+="An : "+this.an+"\n";
        text+="Culoare : "+this.culoare+"\n";
        return text;
    }

    @Override
    public boolean equals(Object o){
        Masina t = (Masina) o;
         return t.getMarca().compareTo(this.getMarca())==0;
    }


    @Override
    public int compareTo(Masina o) {
        if(this.getAn()>o.getAn()){
            return 1;
        } else if (this.getAn()<o.getAn()) {
            return -1;
        } else {
            return 0;
        }
    }
}
