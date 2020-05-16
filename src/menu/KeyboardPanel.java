package menu;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class KeyboardPanel extends JPanel {
    private JButton[][] btnKeys;
    private ArrayList<KeyboardInputListener> keyboardInputListeners;
    private int idealWidth;
    private int idealHeight;

    public KeyboardPanel(String[][] keys, int buttonW, int buttonH, int hSep, int vSep) {
        super();
        this.setLayout(null);

        if (buttonW <= 0 || buttonH <= 0 || hSep < 0 || vSep < 0 || keys.length == 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < keys.length; i++) {
            if (keys[i].length == 0) {
                throw new IllegalArgumentException();
            }
        }

        btnKeys = new JButton[keys.length][];

        int panelW = getButtonLineLength(getLongestRowLength(keys), buttonW, hSep);

        idealWidth = panelW;
        idealHeight = getButtonLineLength(keys.length, buttonH, vSep);

        for (int mapY = 0; mapY < keys.length; mapY++) {
            int baseX = (panelW - getButtonLineLength(keys[mapY].length, buttonW, hSep))/2;
            btnKeys[mapY] = new JButton[keys[mapY].length];

            int buttonY = (buttonH + vSep)*mapY;
            for (int mapX = 0; mapX < keys[mapY].length; mapX++) {
                int buttonX = baseX + (buttonW + hSep)*mapX;

                JButton currButton = new JButton(keys[mapY][mapX]);
                currButton.setLocation(buttonX, buttonY);
                currButton.setSize(buttonW, buttonH);
                currButton.addActionListener(new ButtonActionListener());
                add(currButton);

                ButtonMouseListener listener = new ButtonMouseListener(
                    currButton,
                    new Color(0, 255, 255),
                    new Color(0, 255, 0));
                currButton.addMouseListener(listener);
                currButton.addMouseMotionListener(listener);

                btnKeys[mapY][mapX] = currButton;
            }
        }

        keyboardInputListeners = new ArrayList<>();
    }

    public int getIdealWidth() {
        return idealWidth;
    }

    public int getIdealHeight() {
        return idealHeight;
    }

    public void addKeyboardInputListener(KeyboardInputListener listener) {
        if (listener == null) {
            throw new IllegalArgumentException();
        }

        keyboardInputListeners.add(listener);
    }

    private class ButtonMouseListener extends MouseAdapter {
        private JButton button;
        private Color hoveredColor;
        private Color clickedColor;
        private Color notClickedColor;
        private boolean isClicked;

        public ButtonMouseListener(JButton button, Color hoveredColor, Color clickedColor) {
            this.button = button;
            this.hoveredColor = hoveredColor;
            this.clickedColor = clickedColor;
            this.notClickedColor = button.getBackground();
            this.isClicked = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            button.setBackground(hoveredColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button.setBackground(isClicked ? clickedColor : notClickedColor);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            isClicked = true;
            button.setBackground(clickedColor);
        }
    }

    private class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton)event.getSource();
            String key = source.getText();

            for (KeyboardInputListener listener: keyboardInputListeners) {
                listener.keyboardInputReceived(key);
            }
        }
    }

    private static int getButtonLineLength(int keyNum, int buttonSize, int buttonSep) {
        return keyNum*buttonSize + buttonSep*(keyNum - 1);
    }

    private static int getLongestRowLength(String[][] keys) {
        int res = keys[0].length;
        for (int i = 1; i < keys.length; i++) {
            if (keys[i].length > res) {
                res = keys[i].length;
            }
        }

        return res;
    }
}