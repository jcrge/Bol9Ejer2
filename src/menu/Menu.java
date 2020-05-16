package menu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JFrame {
    private static int MARGIN_LEFT = 10;
    private static int MARGIN_TOP = 10;

    private KeyboardPanel pnlKeyboard;

    public Menu() {
        super("Boletín 9, Ejercicio 2");
        this.setLayout(null);

        String[][] keyboardKeys = new String[][] {
            { "1", "2", "3" },
            { "4", "5", "6" },
            { "7", "8", "9" },
            { "#", "0", "*" }};
        pnlKeyboard = new KeyboardPanel(keyboardKeys, 50, 50, 5, 5);
        pnlKeyboard.setSize(
            pnlKeyboard.getIdealWidth(),
            pnlKeyboard.getIdealHeight());
        pnlKeyboard.setLocation(MARGIN_LEFT, MARGIN_TOP);
        pnlKeyboard.addKeyboardInputListener(new KeyPressReceiver());
        add(pnlKeyboard);
    }

    private class KeyPressReceiver implements KeyboardInputListener {
        @Override
        public void keyboardInputReceived(String key) {
            System.err.printf("Recibido: %s\n", key);
        }
    }
}