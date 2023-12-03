package com.inventory.Salles.Utils;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.inventory.UI.ProgressBar;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deidine
 */
public class Utils {

    public static void main(String arg[]) throws AWTException, URISyntaxException, IOException, InterruptedException {
//Restoredbfromsql("22 11 643 09-11-2023backup.sql");
//        printFromWindowsPrinter();
        Backupdbtosql();
    }

    public static void Restoredbfromsql(String s) {
        try {

            CodeSource codeSource = Utils.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            String dbName = "deidine";
            String dbUser = "root";
            String dbPass = null;
            System.out.println("deidine");

            String restorePath = jarDir + "\\backup" + "\\" + s;

            String[] executeCmd = new String[]{"C:\\xampp\\mysql\\bin\\mysql.exe", dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};

            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            System.out.println(processComplete);

            if (processComplete == 0) {
                JOptionPane.showMessageDialog(null, "Successfully restored from SQL : " + s);
            } else {
                JOptionPane.showMessageDialog(null, "Error at restoring");
            }

        } catch (URISyntaxException | IOException | InterruptedException | HeadlessException ex) {
            JOptionPane.showMessageDialog(null, "Error at Restoredbfromsql" + ex.getMessage());
        }

    }

    public static int Backupdbtosql() throws AWTException {
        int processComplete = 1;
        try {

            CodeSource codeSource = Utils.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();

            String dbName = "localflexible";
            String dbUser = "root";
            String dbPass = null;
            System.out.println(jarDir);

            String folderPath = jarDir + "\\backup";

            /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

            /*NOTE: Creating Path Constraints for backup saving*/
 /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            String savePath = "\"" + jarDir + "\\backup"
                    + "\\" + "backup.sql\"";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = "mysqldump.exe -u "
                    + dbUser + " --database " + dbName + " -r " + savePath;

            Process runtimeProcess = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", executeCmd
            });
            Robot r = new Robot();
            r.keyPress(KeyEvent.VK_ENTER);
            r.keyRelease(KeyEvent.VK_ENTER);
            processComplete = runtimeProcess.waitFor();
            System.out.println(processComplete);
//            final ProgressBar pbr = ProgressBar.getInstance();
//            pbr.setVisible(true);
//            pbr.initProgressBar(i);

            if (processComplete == 0) {

                System.out.println("Backup ddddd");

            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
        return processComplete;
    }

    static String getCureentTime() {

        DateFormat dateFormat = new SimpleDateFormat("HH MM SS dd-MM-yyyy");
        Date dates = new Date();

        return dateFormat.format(dates);

    }

    public static void printFromWindowsPrinter() throws URISyntaxException, IOException, InterruptedException {
        CodeSource codeSource = Utils.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        String jarDir = jarFile.getParentFile().getPath();
        String jarDirTest = jarFile.getParentFile().getParentFile().getPath();

        System.out.println(jarDir + "\\deidine.pdf");
        System.out.println(jarFile.getParentFile().getParentFile().getPath());

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
//        Process runtimeProcess = Runtime.getRuntime().exec("print.bat \""+jarDirTest+"\\deidine.pdf\"" );
//this if you want to add arg to the file that you want to run it and add the path to the pdf
//Process runtimeProcess = Runtime.getRuntime().exec("print.bat \""+jarDir+"\\deidine.pdf\"" );
        Process runtimeProcess = Runtime.getRuntime().exec("print.bat");

        int processComplete = runtimeProcess.waitFor();
        System.out.println(processComplete);

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
        if (processComplete == 0) {
            System.out.println("printed ok");

//            JOptionPane.showMessageDialog(null, "L'operation est Terminer");
        } else {
            JOptionPane.showMessageDialog(null, "Error at restoring");
        }

    }
}
