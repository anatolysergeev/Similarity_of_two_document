package Duplicate;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame appletFrame = new JFrame("Similarity of two documents");
        appletFrame.setLayout(new GridLayout(1, 0));
        appletFrame.setSize(300, 230);
        appletFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appletFrame.setVisible(true);
        Applet duplicate = new DuplicateGUI();
        appletFrame.add(duplicate);
        duplicate.init();
        duplicate.start();
    }
}
