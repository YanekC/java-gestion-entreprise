/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.util.HashMap;
import java.util.Map;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;

/**
 *
 * @author Yanek
 */
public class MonRowFilter extends RowFilter<TableModel, Integer>{
    
    private HashMap<Integer, String> listeFiltre;
            
    public MonRowFilter(HashMap<Integer, String> lst){
        listeFiltre = lst;
    }

    public boolean include(RowFilter.Entry<? extends TableModel, ? extends Integer> entry) {
        boolean ret = true;
        
        for(Map.Entry<Integer, String> f : listeFiltre.entrySet()){
            if(!((String)entry.getValue(f.getKey())).toLowerCase().contains(f.getValue().toLowerCase())){
                ret = false;
            }
        }
        
        return ret;
    }

}
