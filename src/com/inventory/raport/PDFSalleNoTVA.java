/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventory.raport;

import com.inventory.Salles.Utils.Utils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File; 
import java.io.FileOutputStream; 
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.io.FileUtils;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

/**
 *
 * @author deidine
 */
public class PDFSalleNoTVA {

    public static void main(String args[]) throws IOException {
        PdfSalle pdf = new PdfSalle();
        pdf.imgType();
        pdf.readTxtFile(0);
    }

    public String readTxtFile(int number) throws IOException {
        String line = null;
        File file = new File("resources/info/info.txt");
        String line2 = FileUtils.readLines(file).get(number);

        return line2;
    }

    public String imgType() {
        String file = null;
        BufferedReader br;

        if (new File("resources/info/logo.png").length() == 0) {
            file = "resources/info/logo.jpg";

        } else {
            file = "resources/info/logo.png";

        }

        return file;
    }

    public static void inBaoCao(File file, String date, String operationType, DefaultTableModel table, String customerInfo, String recu, String back, String total) throws DocumentException, IOException, ParseException, URISyntaxException, InterruptedException {
        PdfSalle pdf = new PdfSalle();

        Document document = new Document(PageSize.A4_LANDSCAPE);
        BaseFont bf1 = BaseFont.createFont("vuArial.ttf", BaseFont.IDENTITY_H, true);
        FileOutputStream outFile = new FileOutputStream(file);
        PdfWriter.getInstance(document, outFile);

        document.open();
        com.itextpdf.text.Font fontTenBaoCao = new com.itextpdf.text.Font(bf1, 18, com.itextpdf.text.Font.BOLD, BaseColor.BLUE);
        com.itextpdf.text.Font fontTenCuaHang = new com.itextpdf.text.Font(bf1, 14, com.itextpdf.text.Font.BOLD, BaseColor.RED);
        com.itextpdf.text.Font fontChung = new com.itextpdf.text.Font(bf1, 13, com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        com.itextpdf.text.Font fontChungDam = new com.itextpdf.text.Font(bf1, 13, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
        com.itextpdf.text.Font fontChungNghieng = new com.itextpdf.text.Font(bf1, 13, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);
        Image logo;

        logo = Image.getInstance(new ImageIcon(pdf.imgType()).getImage().getScaledInstance(90, 30, java.awt.Image.SCALE_REPLICATE), Color.white);
        logo.setAlignment(Element.ALIGN_CENTER);

        document.add(logo);
        Paragraph companyName = new Paragraph(pdf.readTxtFile(0), fontTenCuaHang);
        companyName.setAlignment(Element.ALIGN_CENTER);

        document.add(companyName);

        Paragraph dat = new Paragraph("DATE : " + date, fontChungNghieng);
        dat.setAlignment(Element.ALIGN_RIGHT);
        document.add(dat);

        document.add(new Paragraph(
                pdf.readTxtFile(6)
                + "\n" + pdf.readTxtFile(3),
                fontChung));

        Paragraph nif = new Paragraph("NIF : " + pdf.readTxtFile(4), fontChungNghieng);
        dat.setAlignment(Element.ALIGN_RIGHT);
        document.add(nif);

        document.add(new Paragraph("------------------------------------------------------------------------------------------------------------------------", fontChungDam));
        Paragraph ph1 = new Paragraph("FACTURE : " + operationType, fontTenBaoCao);
        ph1.setSpacingAfter(10f);
        ph1.setAlignment(Element.ALIGN_CENTER);
        document.add(ph1);

        document.add(new Paragraph("CLIENT :" + customerInfo));
        document.add(new Paragraph(" "));
        ArrayList<String> name = new ArrayList<>();

        name.add("DESIGNATION");
        name.add("PU");
        name.add("QTé");
        name.add("P.TOTAL");

        document.add(getTable((DefaultTableModel) table, name, total));
        document.add(new Paragraph(" "));
        document.add(new Paragraph());
        List unorderedList = new List(List.UNORDERED);
//        unorderedList.add(new ListItem("MONTENT PAYEE : " + txtTotal.getText(), fontTenCuaHang));
        unorderedList.add(new ListItem("MONTENT RECU: " + recu));
        unorderedList.add(new ListItem("MONTENT RETOURNE: " + back));
        document.add(unorderedList);
        document.add(new Paragraph("Le Gérant : ___________________________", fontChungDam));

        document.close();

//    assertEquals(expectedText, text.toString());
        int a = JOptionPane.showConfirmDialog(null, "tu veux imprimer le facture?", "Select", JOptionPane.YES_NO_OPTION);
        JOptionPane.setRootFrame(null);
        if (a == JOptionPane.YES_OPTION) {
            Utils.printFromWindowsPrinter();
        }
    }

    public static PdfPTable getTable(DefaultTableModel listSalles, ArrayList<String> name, String total) throws DocumentException, IOException {

        BaseFont bf1 = BaseFont.createFont("vuArial.ttf", BaseFont.IDENTITY_H, true);
   com.itextpdf.text.Font fontChungDam = new com.itextpdf.text.Font(bf1, 13, com.itextpdf.text.Font.BOLD, BaseColor.BLACK);

        com.itextpdf.text.Font fontChung = new com.itextpdf.text.Font(bf1, 12, com.itextpdf.text.Font.NORMAL, BaseColor.BLACK);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new float[]{60f, 20f, 20f, 50f});
        table.addCell(new PdfPCell(new Paragraph(name.get(0), fontChungDam)));
        table.addCell(new PdfPCell(new Paragraph(name.get(1), fontChungDam)));
        table.addCell(new PdfPCell(new Paragraph(name.get(2), fontChungDam)));
        table.addCell(new PdfPCell(new Paragraph(name.get(3), fontChungDam)));

//PdfPCell cel3= new PdfPCell();
//        cel3.setBorder(2);
//        cel3.addElement(new Paragraph(  ""));

        for (int i = 0; i < listSalles.getRowCount(); i++) {

            table.addCell(new PdfPCell(new Paragraph(listSalles.getValueAt(i, 4).toString(), fontChung)));
            table.addCell(new PdfPCell(new Paragraph(listSalles.getValueAt(i, 2).toString(), fontChung)));
            table.addCell(new PdfPCell(new Paragraph(listSalles.getValueAt(i, 3).toString(), fontChung)));
            Double sellPrice = Double.valueOf(listSalles.getValueAt(i, 2).toString())
                    * Double.valueOf(listSalles.getValueAt(i, 3).toString());
            Double totalRevenue = sellPrice;
            String totalRevenueStr = String.valueOf(totalRevenue);
            table.addCell(new PdfPCell(new Paragraph(totalRevenueStr, fontChung)));

        }
        PdfPCell cel1 = new PdfPCell();
        cel1.setBorder(2);
        PdfPCell cel2 = new PdfPCell();
        cel2.setBorder(1);
        table.addCell(new Paragraph("TOTAL HC"));
        table.addCell(new Paragraph());
        table.addCell(new Paragraph("MRU"));

        Paragraph ph2 = new Paragraph(total);
        ph2.setAlignment(Element.ALIGN_RIGHT);
//        ph2.setSpacingAfter(10f);
        PdfPCell cel = new PdfPCell(ph2);

        table.addCell(cel);

        return table;
    }

    public void ViewerCtrl(String filePath) {

        SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JFrame applicationFrame = new JFrame();
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //applicationFrame.getContentPane().add(viewerComponentPanel);
        applicationFrame.add(viewerComponentPanel);

        controller.openDocument(filePath);

        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }
}
