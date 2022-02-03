
package WEB.MODEL;

import METIER.Grade;
import java.util.ArrayList;
import java.util.List;

public class GradeModel {
    
     List<Grade> listGrd = new ArrayList<Grade>();

    public List<Grade> getListGrd() {
        return listGrd;
    }

    public void setListGrd(List<Grade> listGrd) {
        this.listGrd = listGrd;
    }
    
}
