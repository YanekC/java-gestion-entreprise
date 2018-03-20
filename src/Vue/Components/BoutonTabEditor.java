/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.Components;

import Vue.Personnel.AjouterModifierPersonnelJFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author guilhem
 */
public class BoutonTabEditor extends DefaultCellEditor{
    protected JButton btn;
    private String lbl;
    private Boolean clicked;
    private int id;
   
   
   public BoutonTabEditor(JTextField txt, int id) {
       super(txt);
       
       btn=new JButton();
       btn.setOpaque(true);
       
       btn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               fireEditingStopped();
           }
       });
       
       this.id = id;
   }
   
   @Override
   public Component getTableCellEditorComponent(JTable table, Object obj,boolean selected, int row, int col) {
       lbl=(obj==null) ? "":"modifier";
       btn.setText(lbl);
       clicked=true;
       
       return btn;
   }
   
   @Override
   public Object getCellEditorValue() {
       AjouterModifierPersonnelJFrame apf = new AjouterModifierPersonnelJFrame();
       apf.setVisible(true);
       apf.remplirFormPersonnel(this.id);
       
       return "quelque chose";
   }
   
   @Override
   public boolean stopCellEditing() {
       clicked=false;
       
       return super.stopCellEditing();
   }
   
   @Override
   protected void fireEditingStopped() {
       super.fireEditingStopped();
   }

}
