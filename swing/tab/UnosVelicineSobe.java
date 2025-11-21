package raf.draft.dsw.gui.swing.tab;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
@Setter@Getter
public class UnosVelicineSobe {

    private int x;
    private int y;

    public UnosVelicineSobe() {
        JTextArea textAreaX = new JTextArea(1, 20);
        JTextArea textAreaY = new JTextArea(1, 20);
        textAreaX.setText("Duzina");
        textAreaY.setText("Sirina");


        int result = JOptionPane.showConfirmDialog(
                null,
                new JScrollPane(textAreaX),
                "Unesite dva cela broja u cm",
                JOptionPane.OK_CANCEL_OPTION
        );


        if (result == JOptionPane.OK_OPTION) {
            try {

                String[] inputs = textAreaX.getText().split("\n");
                 this.x = Integer.parseInt(inputs[0].trim());
                 this.y = Integer.parseInt(inputs[1].trim());

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Unesite dva cela broja u cm",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}

