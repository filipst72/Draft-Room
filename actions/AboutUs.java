package raf.draft.dsw.controller.actions;

import raf.draft.dsw.controller.actions.AbstractRoomAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUs extends AbstractRoomAction {
    public AboutUs(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/information.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }
    String message = "Studenti koji rade na projektu:\n" +
            "1. Bosko Bradic RN 62/2023 \n" +
            "2. Filip Stojanovic RN 72/2023 \n";

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, message, "About Us",  JOptionPane.INFORMATION_MESSAGE, loadIcon("/images/information.png"));

    }
}
