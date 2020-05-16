package menu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Menu extends JFrame {
    private static int MARGIN_LEFT = 10;
    private static int MARGIN_TOP = 10;

    private KeyboardPanel keyboardPanel;
    public Menu() {
        super("Boletín 9, Ejercicio 2");
        this.setLayout(null);

        String[][] keyboardKeys = new String[][] {
            { "1", "2", "3" },
            { "4", "5", "6" },
            { "7", "8", "9" },
            { "#", "0", "*" }};
        keyboardPanel = new KeyboardPanel(keyboardKeys, 50, 50, 5, 5);
        keyboardPanel.setSize(
            keyboardPanel.getIdealWidth(),
            keyboardPanel.getIdealHeight());
        keyboardPanel.setLocation(MARGIN_LEFT, MARGIN_TOP);
        keyboardPanel.addKeyboardInputListener(new KeyPressReceiver());
        add(keyboardPanel);
    }

    private class KeyPressReceiver implements KeyboardInputListener {
        @Override
        public void keyboardInputReceived(String key) {
            System.err.printf("Recibido: %s\n", key);
        }
    }
}