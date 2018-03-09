import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor {
    private String text;
    public TextEditor(){
        Interface();
    }

    private void Interface(){
        JPanel panel = new JPanel();
        final JTextArea textArea = new JTextArea(21,21);
        textArea.setFont(new Font("Serif", Font.ITALIC, 20));
        textArea.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setPreferredSize(new Dimension(300, 300));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JMenuBar menuBar = new JMenuBar();
        JMenu newMenu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem open = new JMenuItem("open");
        newMenu.add(save);
        newMenu.add(open);
        menuBar.add(newMenu);


        JFrame frame = new JFrame();
        frame.setJMenuBar(menuBar);
        frame.setResizable(false);

        panel.add(scroll);
        frame.add(panel);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 310;
        int sizeHeight = 300;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        frame.setBounds(locationX, locationY, sizeWidth, sizeHeight);
        frame.setVisible(true);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter writer = new FileWriter(fileopen.getSelectedFile(), false);
                        BufferedWriter bufferWriter = new BufferedWriter(writer);
                        bufferWriter.write(textArea.getText());
                        text = "";
                        text = textArea.getText();
                        bufferWriter.close();
                    } catch(Exception ex) {ex.printStackTrace();}
                }



            }
        });

        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "Открыть файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    try {
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                        BufferedReader fin = new BufferedReader(new FileReader(fileopen.getSelectedFile()));
                        String line;
                        text = "";
                        while ((line = fin.readLine()) != null) textArea.append(line); text += line ;

                    } catch(Exception ex) {ex.printStackTrace();}
                }
            }
        });
    }

    public String getText() {
        return text;
    }

}
