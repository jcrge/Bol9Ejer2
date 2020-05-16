package menu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class Menu extends JFrame {
    private static int MARGIN_LEFT = 10;
    private static int MARGIN_TOP = 10;

    private KeyboardPanel pnlKeyboard;
    private JTextField txfInput;

    public Menu() {
        super("Boletín 9, Ejercicio 2");
        this.setLayout(null);

        String[][] keyboardKeys = new String[][] {
            { "1", "2", "3" },
            { "4", "5", "6" },
            { "7", "8", "9" },
            { "#", "0", "*" }};
        pnlKeyboard = new KeyboardPanel(keyboardKeys, 50, 50, 5, 5);

        txfInput = new JTextField();
        txfInput.setSize(
            pnlKeyboard.getIdealWidth(),
            (int)txfInput.getPreferredSize().getHeight());
        txfInput.setLocation(MARGIN_LEFT, MARGIN_TOP);
        txfInput.setEditable(false);
        add(txfInput);

        pnlKeyboard.setSize(
            pnlKeyboard.getIdealWidth(),
            pnlKeyboard.getIdealHeight());
        pnlKeyboard.setLocation(MARGIN_LEFT, MARGIN_TOP + txfInput.getHeight() + 10);
        pnlKeyboard.addKeyboardInputListener(new KeyPressReceiver());
        add(pnlKeyboard);

        addKeyListener(new KeyboardPanelKeyListener());
        setFocusable(true);
    }

    private class KeyPressReceiver implements KeyboardInputListener {
        @Override
        public void keyboardInputReceived(String key) {
            handleKey(key.toLowerCase());
        }
    }

    private class KeyboardPanelKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            for (String key: pnlKeyboard.getAvailableKeys()) {
                if (key.equalsIgnoreCase("" + e.getKeyChar())) {
                    handleKey(key.toLowerCase());
                }
            }
        }
    }

    private void handleKey(String key) {
        txfInput.setText(txfInput.getText() + key);
    }
}