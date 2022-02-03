
package WEB.MODEL;

import METIER.Semestre;
import java.util.ArrayList;
import java.util.List;


public class SemestreModel {
    
    List<Semestre> listSemestre = new ArrayList<Semestre>();

    public List<Semestre> getListSemestre() {
        return listSemestre;
    }

    public void setListSemestre(List<Semestre> listSemestre) {
        this.listSemestre = listSemestre;
    }
    
    
    
}
