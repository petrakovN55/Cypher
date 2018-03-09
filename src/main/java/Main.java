import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Main {
    public static String text = "";
    public static void main(String args[]) {
//        TextEditor t = new TextEditor();
        Interface();
    }

    private static void Interface() {
        JPanel panel = new JPanel(new GridLayout(2,2));
        JPanel cPanel = new JPanel(new GridLayout(2,2));
        JPanel lPanel = new JPanel(new GridLayout(2,5));
        JPanel ePanel = new JPanel(new GridLayout(2,1));
        JPanel qPanel = new JPanel(new GridLayout(1,2));
        JButton openButton = setButton("open");
        JButton saveButton = setButton("save");
        JButton encrypt = setButton("Зашифровать");
        JButton decipher = setButton("Расшифровать");
        final JTextField openField = setTextField();
        final JTextField saveField = setTextField();

        JLabel aLabel = new JLabel("a");
        JLabel bLabel = new JLabel("b");
        JLabel mLabel = new JLabel("m");
        JLabel y_0Label = new JLabel("y_0");
        JLabel tLabel = new JLabel("t");
        JTextField aField = setTextField();
        JTextField bField = setTextField();
        JTextField mField = setTextField();
        JTextField y_0Field = setTextField();
        JTextField tField = setTextField();
        lPanel.add(aLabel);
        lPanel.add(bLabel);
        lPanel.add(mLabel);
        lPanel.add(y_0Label);
        lPanel.add(tLabel);

        lPanel.add(aField);
        lPanel.add(bField);
        lPanel.add(mField);
        lPanel.add(y_0Field);
        lPanel.add(tField);

        final JButton textEditor = new JButton("Текстовый редактор");
        JProgressBar progressBar = new JProgressBar();
//        progressBar.setStringPainted(true);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);

        qPanel.add(progressBar);
        qPanel.add(textEditor);

        CheckboxGroup cbg = new CheckboxGroup();

        cPanel.add(new Checkbox("Асинхронное шифрование", cbg, true));
        cPanel.add(new Checkbox("Синхронное шифрование", cbg, false));
        cPanel.add(encrypt);
        cPanel.add(decipher);
        panel.add(openButton);
        panel.add(openField);
        panel.add(saveButton);
        panel.add(saveField);

        ePanel.add(cPanel);
        ePanel.add(lPanel);

        JFrame frame = setFrame();
        frame.add(panel, BorderLayout.NORTH);
//        frame.add(cPanel,BorderLayout.CENTER);
//        frame.add(lPanel,BorderLayout.SOUTH);
        frame.add(ePanel,BorderLayout.CENTER);
        frame.add(qPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

        textEditor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TextEditor t = new TextEditor();
            }
        });


        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    openField.setText(fileopen.getSelectedFile().getPath());
                   try {
                       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                       BufferedReader fin = new BufferedReader(new FileReader(fileopen.getSelectedFile()));
                       String line;
                       text = "";
                       while ((line = fin.readLine()) != null) text += line;

                   }catch(Exception ex) {ex.printStackTrace();}
                }
                System.out.println(text);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    saveField.setText(fileopen.getSelectedFile().getPath());
                    try {
                        FileWriter writer = new FileWriter(fileopen.getSelectedFile(), false);
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write(text);
                        bufferWriter.close();
                    } catch(Exception ex) {ex.printStackTrace();}
                }



            }
        });

    }

    private static JFrame setFrame() {
        JFrame frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth =410;
        int sizeHeight = 250;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        frame.setResizable(false);

        return frame;
    }
    private static JTextField setTextField() {
        JTextField field = new JTextField();

        return field;
    }

    private static JButton setButton(String name) {
        JButton button = new JButton(name);
        return button;
    }
}
