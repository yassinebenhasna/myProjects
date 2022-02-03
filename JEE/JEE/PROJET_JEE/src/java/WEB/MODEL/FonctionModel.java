
package WEB.MODEL;

import METIER.Fonction;
import java.util.ArrayList;
import java.util.List;


public class FonctionModel {
    
    List<Fonction> listFct = new ArrayList<Fonction>();

    public List<Fonction> getListFct() {
        return listFct;
    }

    public void setListFct(List<Fonction> listFct) {
        this.listFct = listFct;
    }
    
}
