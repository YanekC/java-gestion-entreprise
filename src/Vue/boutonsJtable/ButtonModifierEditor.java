package Vue.boutonsJtable;

import Model.Entreprise;
import Model.Personnel;
import Vue.Menu2;
import Vue.Missions.AjouterMissionJFrame;
import Vue.Personnel.AjouterModifierPersonnelJFrame;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Creer les buttons speciaux qui sont dans les jtables
 * @author Yanek
 */
public class ButtonModifierEditor extends DefaultCellEditor {

    protected JButton button;
    private ButtonListener bListener = new ButtonListener();
    private ImageIcon icone;
    private String version;
    private String versionTable;
    private JTable table;
    private Menu2 menu;

    //Constructeur avec une CheckBox
    /**
     * Construit l'editeur du bouton
     * @param checkBox une checkbox 
     * @param icone une icone si besoin
     * @param version 'modifier' ou 'supprimer' en fonction de ce qu'on veut que le bouton fasse
     * @param table la table sur laquelle est le bouton
     * @param versionTable 'mission' ou 'personnel' en fonction de la table sur laquelle le bouton est
     * @param menu la frame appelante
     */
    public ButtonModifierEditor(JCheckBox checkBox, ImageIcon icone, String version, JTable table, String versionTable, Menu2 menu) {
        //Par défaut, ce type d'objet travaille avec un JCheckBox
        super(checkBox);
        //On crée à nouveau un bouton
        button = new JButton();
        button.setOpaque(true);
        //On lui attribue un listener
        button.addActionListener(bListener);

        this.menu = menu;
        this.table = table;
        this.icone = icone;
        this.version = version;
        this.versionTable = versionTable;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //On précise le numéro de ligne à notre listener
        bListener.setRow(row);
        //Idem pour le numéro de colonne
        bListener.setColumn(column);
        //On passe aussi le tableau en paramètre pour des actions potentielles
        bListener.setTable(table);

        //On réaffecte le libellé au bouton
        if (version.equals("modifier")) {
            button.setIcon(icone);
            button.setBackground(Color.LIGHT_GRAY);
        } else if (version.equals("supprimer")) {
            button.setBackground(new Color(189, 30, 45));
            button.setForeground(Color.white);
            button.setText("X");
        }
        //On renvoie le bouton
        return button;
    }

    //Notre listener pour le bouton
    class ButtonListener implements ActionListener {

        private int column, row;
        private JTable table;
        private int nbre = 0;

        public void setColumn(int col) {
            this.column = col;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setTable(JTable table) {
            this.table = table;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            if (version.equals("modifier")) {
                if (versionTable.equals("mission")) {
                     //MODIFICATION MISSION
                    /*----- Modifier une personne sélectionné -----*/

                    //Get the id
                    int id = menu.getColZeroValueSelectedMission();
                    //Load Frame with selected ID
                    AjouterMissionJFrame apf = new AjouterMissionJFrame();
                    apf.setVisible(true);
                    /* -- Envoie de l'id pour remplir la frame, envois de la ligne pour actualiser --------*/
                    apf.remplirFormMission(id, table, table.getSelectedRow(), 0, apf);
                } else if (versionTable.equals("personnel")) {
                     //MODIFICATION PERSONNEl
                    int id = menu.getColZeroValueSelected();

                    AjouterModifierPersonnelJFrame apf = new AjouterModifierPersonnelJFrame();
                    apf.setVisible(true);
                    /* -- Envoie de l'id pour remplir la frame, envois de la ligne pour actualiser --------*/
                    apf.remplirFormPersonnel(id, table, row, 0, menu);
                }
            } else if (version.equals("supprimer")) {
                if (versionTable.equals("mission")) {
                     //SUPPRESSION MISSION
                      String ObjButtons[] = {"Oui","Non"};
                        int PromptResult = JOptionPane.showOptionDialog(null, 
                                           "La mission sera définitivement supprimé, continuer ?", "Supprimer la mission", 
                                           JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                                           ObjButtons,ObjButtons[1]);
                        if(PromptResult==0){
                            int id = menu.getColZeroValueSelectedMission();
                            Entreprise.removeMission(id);
                            int rowToDel = table.convertRowIndexToModel(table.getSelectedRow());
                            ((DefaultTableModel) table.getModel()).removeRow(rowToDel);
                        }
                } else if (versionTable.equals("personnel")) {
                    //SUPPRESION PERSONNEl
                    String ObjButtons[] = {"Oui","Non"};
                    int PromptResult = JOptionPane.showOptionDialog(null, 
                                       "Le personnel sera définitivement supprimé, continuer ?", "Supprimer le personnel", 
                                       JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                                       ObjButtons,ObjButtons[1]);
                    if(PromptResult==0)
                    {
                        int id = menu.getColZeroValueSelected();
                        Personnel p = Entreprise.findPersonnelById(id);
                        Entreprise.removePersonnel(p, id);
                        int rowToDel = table.convertRowIndexToModel(table.getSelectedRow());
                        ((DefaultTableModel) table.getModel()).removeRow(rowToDel);
                    }
                }

            }

        }
    }
}
