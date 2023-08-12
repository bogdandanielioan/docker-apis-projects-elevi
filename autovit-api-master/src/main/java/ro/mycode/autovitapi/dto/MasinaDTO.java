package ro.mycode.autovitapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MasinaDTO implements Serializable {

    private String marca="";
    private String model="";
    private int an=0;
    private String culoare="";

}
