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
    private JButton btnReset;

    private JMenuBar mnuMainBar;
    private JMenu mnuFile;
    private JMenuItem mnuSave, mnuShow;
    private JMenu mnuPhone;
    private JMenuItem mnuReset, mnuQuit;
    private JMenu mnuMore;
    private JMenuItem mnuAbout;

    public Menu() {
        super("Boletín 9, Ejercicio 2");
        this.setLayout(null);

        createMenu();
        createGUI();
    }

    public void createMenu() {
        mnuSave = new JMenuItem("Guardar");
        mnuSave.setMnemonic('G');
        mnuSave.addActionListener(new SaveInputListener());

        mnuShow = new JMenuItem("Ver números guardados");
        mnuShow.setMnemonic('V');
        mnuShow.addActionListener(new ShowSavedStringsListener());

        mnuFile = new JMenu("Archivo");
        mnuFile.setMnemonic('A');
        mnuFile.add(mnuSave);
        mnuFile.add(mnuShow);

        mnuReset = new JMenuItem("Resetear");
        mnuReset.setMnemonic('R');
        mnuReset.addActionListener(new FormResetter());

        mnuQuit = new JMenuItem("Salir");
        mnuQuit.setMnemonic('S');
        mnuQuit.addActionListener(new QuitListener());

        mnuPhone = new JMenu("Móvil");
        mnuPhone.setMnemonic('M');
        mnuPhone.add(mnuReset);
        mnuPhone.addSeparator();
        mnuPhone.add(mnuQuit);

        mnuAbout = new JMenuItem("Acerca de...");
        mnuAbout.setMnemonic('A');
        mnuAbout.addActionListener(new AboutListener());

        mnuMore = new JMenu("Otros");
        mnuMore.setMnemonic('O');
        mnuMore.add(mnuAbout);

        mnuMainBar = new JMenuBar();
        mnuMainBar.add(mnuFile);
        mnuMainBar.add(mnuPhone);
        mnuMainBar.add(mnuMore);
        this.setJMenuBar(mnuMainBar);
    }

    public void createGUI() {
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

        btnReset = new JButton("Resetear");
        btnReset.setLocation(MARGIN_LEFT, pnlKeyboard.getY() + pnlKeyboard.getHeight() + 10);
        btnReset.setSize(pnlKeyboard.getWidth(), (int)btnReset.getPreferredSize().getHeight());
        btnReset.addActionListener(new FormResetter());
        add(btnReset);

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

    private class FormResetter implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            pnlKeyboard.resetState();
            txfInput.setText("");
        }
    }

    private class ShowSavedStringsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        }
    }

    private class QuitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            Menu.this.dispose();
        }
    }

    private class AboutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JOptionPane.showMessageDialog(
                null,
                "Este programa es una solución al ejercicio 2 del boletín 9.\n\n"
                  + "Autor: Jorge\nVersión: 1.0",
                "Información",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class SaveInputListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
        }
    }

    private void handleKey(String key) {
        txfInput.setText(txfInput.getText() + key);
    }
}