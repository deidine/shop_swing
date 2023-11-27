//package Combo ;
//
//import java.awt.Component;
//import java.awt.FlowLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.ListCellRenderer;
//import javax.swing.UIManager;
//import javax.swing.border.EmptyBorder;
//
///**
// * @version 1.0 12/26/98
// */
//public class DisabledComboBoxExample extends JFrame {
//
//  public DisabledComboBoxExample() {
//    super("Disabled Item ComboBox Example");
//
//    Object[] items = { new ComboItem("A"), new ComboItem("B"),
//        new ComboItem("1", false), new ComboItem("2", false),
//        new ComboItem("abc"), new ComboItem("def") };
//
//    JComboBox combo = new JComboBox(items);
//    combo.setRenderer(new ComboRenderer());
//    combo.addActionListener(new ComboListener(combo));
//
//    getContentPane().setLayout(new FlowLayout());
//    getContentPane().add(combo);
//    setSize(300, 100);
//    setVisible(true);
//  }
//
//  public static void main(String args[]) {
//    try {
//        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//    } catch (Exception evt) {}
//  
//    DisabledComboBoxExample frame = new DisabledComboBoxExample();
//    frame.addWindowListener(new WindowAdapter() {
//      public void windowClosing(WindowEvent e) {
//        System.exit(0);
//      }
//    });
//  }
//
//  class ComboRenderer extends JLabel implements ListCellRenderer {
//
//    public ComboRenderer() {
//      setOpaque(true);
//      setBorder(new EmptyBorder(1, 1, 1, 1));
//    }
//
//    public Component getListCellRendererComponent(JList list, Object value,
//        int index, boolean isSelected, boolean cellHasFocus) {
//      if (isSelected) {
//        setBackground(list.getSelectionBackground());
//        setForeground(list.getSelectionForeground());
//      } else {
//        setBackground(list.getBackground());
//        setForeground(list.getForeground());
//      }
//      if (!((CanEnable) value).isEnabled()) {
//        setBackground(list.getBackground());
//        setForeground(UIManager.getColor("Label.disabledForeground"));
//      }
//      setFont(list.getFont());
//      setText((value == null) ? "" : value.toString());
//      return this;
//    }
//  }
//
//  class ComboListener implements ActionListener {
//    JComboBox combo;
//
//    Object currentItem;
//
//    ComboListener(JComboBox combo) {
//      this.combo = combo;
//      combo.setSelectedIndex(0);
//      currentItem = combo.getSelectedItem();
//    }
//
//    public void actionPerformed(ActionEvent e) {
//      Object tempItem = combo.getSelectedItem();
//      if (!((CanEnable) tempItem).isEnabled()) {
//        combo.setSelectedItem(currentItem);
//      } else {
//        currentItem = tempItem;
//      }
//    }
//  }
//
//  class ComboItem implements CanEnable {
//    Object obj;
//
//    boolean isEnable;
//
//    ComboItem(Object obj, boolean isEnable) {
//      this.obj = obj;
//      this.isEnable = isEnable;
//    }
//
//    ComboItem(Object obj) {
//      this(obj, true);
//    }
//
//    public boolean isEnabled() {
//      return isEnable;
//    }
//
//    public void setEnabled(boolean isEnable) {
//      this.isEnable = isEnable;
//    }
//
//    public String toString() {
//      return obj.toString();
//    }
//  }
//}
//
//interface CanEnable {
//
//  public void setEnabled(boolean isEnable);
//
//  public boolean isEnabled();
//
//}
