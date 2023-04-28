import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;


public class RecursiveLister extends JFrame
{
    JPanel mainPnl, midPnl, quitPnl;
    JButton quitBtn;
    JTextArea recTA;
    JScrollPane recPane;



public RecursiveLister()
{
    createGUI();

    setTitle("Recursive Lister");
    setSize(700, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}

private void createGUI() {
    mainPnl = new JPanel();
    midPnl = new JPanel();
    quitPnl = new JPanel();


    mainPnl.setLayout(new BorderLayout());

    mainPnl.add(midPnl, BorderLayout.CENTER);
    mainPnl.add(quitPnl, BorderLayout.SOUTH);

    add(mainPnl);

    createMidPnl();
    createQuitPnl();

    runRecursive();
}

private void createMidPnl()
{
    recTA = new JTextArea(25, 50);
    recPane = new JScrollPane(recTA);

    midPnl.setBorder(new TitledBorder(new EtchedBorder(), "Recursive List Screen"));
    midPnl.add(recPane);
}

private void createQuitPnl()
{
    quitBtn = new JButton("Quit");
    quitBtn.setPreferredSize(new Dimension(150, 50));

    quitBtn.addActionListener((ActionEvent ae) ->
    {
        if(JOptionPane.showConfirmDialog(null, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION) == 0)
        {
            System.exit(0);
        }
    });
    quitPnl.add(quitBtn);
}


private void runRecursive() {
    JOptionPane.showMessageDialog(null, "Please input the directory to be listed out");

    JFileChooser chooser = new JFileChooser();
    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    File selectedFile;

        File parentDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(parentDirectory);

    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
    {
        selectedFile = chooser.getSelectedFile();
        runfile(selectedFile);
    }

}

private void runfile(File directory)
{
    File directories[] = directory.listFiles();

    for (File f : directory.listFiles())
    {
        if (!f.isDirectory())
        {
            recTA.append(String.format("%-20s%n", f));
        }
        else
        {
            runfile(f.getAbsoluteFile());
        }

    }
}

}

