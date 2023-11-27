package com.inventory.print;

import javax.swing.JDialog; 
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class ViewerCtrl  {
	
    public ViewerCtrl(String filePath) {
    	SwingController controller = new SwingController();

        SwingViewBuilder factory = new SwingViewBuilder(controller);

        JPanel viewerComponentPanel = factory.buildViewerPanel();

        controller.getDocumentViewController().setAnnotationCallback(
                new org.icepdf.ri.common.MyAnnotationCallback(
                        controller.getDocumentViewController()));

        JDialog applicationFrame = new JDialog();
        
        applicationFrame.add(viewerComponentPanel);
        
        applicationFrame.setModal(true);
        
        applicationFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        controller.openDocument(filePath);

        applicationFrame.pack();
        applicationFrame.setVisible(true);
    }
//    public static void main(String args[])
//    {
//    	new ViewerCtrl("deidine.pdf");
//    }
    
}