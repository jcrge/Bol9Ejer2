package app;

import javax.swing.*;
import menu.*;

public class App {
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(190, 350);
        menu.setVisible(true);
    }
}