package Duplicate;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Duplicate extends Applet implements ActionListener {
    Button[] bList = new Button[3];
    Label find;

    public void init () {
        setLayout(null);

        add(bList[0] = new Button("Open 1st file"));
        add(bList[1] = new Button("Open 2nd file"));
        add(bList[2] = new Button("Compare"));

        find = new Label("");
        add(find);

        bList[0].setBounds(30, 30, 100, 30);
        bList[1].setBounds(150, 30, 100, 30);
        bList[2].setBounds(90, 70, 100, 30);
        find.setBounds(30, 110, 200, 50);


        for (int i = 0; i < 3; i++)
            bList[i].addActionListener(this);
    }

    File file1 = null;
    File file2 = null;

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bList[0]) {
            Frame f = new ChooseFileFrame("1st");
            f.setVisible(false);
            f.setSize(100, 100);

            FileDialog fd = new FileDialog(f, "Choose 1st file");
            fd.setVisible(true);
            file1 = new File(fd.getDirectory() + fd.getFile());
        }
        if (e.getSource() == bList[1]) {
            Frame f = new ChooseFileFrame("2nd");
            f.setVisible(false);
            f.setSize(100, 100);

            FileDialog fd = new FileDialog(f, "Choose 2nd file");
            fd.setVisible(true);
            file2 = new File(fd.getDirectory() + fd.getFile());
        }
        if (e.getSource() == bList[2]) {
            try {
                find.setText("Выполняеться...");
                ShinglingText text1 = new ShinglingText(new FileInputStream(file1));
                ShinglingText text2 = new ShinglingText(new FileInputStream(file2));
                find.setText("Тексты похожи на: " + text1.compare(text2) + "%");
            } catch (FileNotFoundException ea) {
                find.setText("Файл не найден");
            } catch (NullPointerException ta) {
                find.setText("Файл не указан");
            }
        }
    }
}

class ChooseFileFrame extends Frame {
    ChooseFileFrame(String title) {
        super(title);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}