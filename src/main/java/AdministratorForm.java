import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class AdministratorForm extends JFrame {
    private JTable table1;
    private JTextField textField1;
    private JTextField textField7;
    private JTextField textField6;
    private JTextField textField2;
    private JTextField textField5;
    private JTextField textField3;
    private JTextField textField4;
    private JButton addNewItemButton;
    private JButton modifyItemButton;
    private JButton deleteItemButton;
    private JButton createNewProductButton;
    private JButton backButton;
    private JButton exitButton;
    private JButton showItemsButton;
    private JPanel contentPane1;
    private JButton importButton;
    private JButton addToListButton;
    private JButton reportsButton;
    List<MenuItem> listaProduse = new ArrayList<>();
    List<BaseProduct> lista = new ArrayList<>();
    Serializator s =new Serializator();
    DeliveryService d=new DeliveryService();
    public AdministratorForm() {

        setContentPane(contentPane1);
        setTitle("Administrator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 600);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listaProduse = d.importProducts();
            }
        });


        showItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    table1.setModel(d.tableModel());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        addNewItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BaseProduct bs = new BaseProduct(textField1.getText(), Double.parseDouble(textField2.getText()), Integer.parseInt(textField3.getText()),
                        Integer.parseInt(textField4.getText()), Integer.parseInt(textField5.getText()), Integer.parseInt(textField6.getText()),
                        Integer.parseInt(textField7.getText()));
                try {
                    d.addItem(bs);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        deleteItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table1.getSelectedRow();
                String value = table1.getModel().getValueAt(row, column).toString();
                try {
                    d.deleteItem(value);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        modifyItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table1.getSelectedRow();
                String value = table1.getModel().getValueAt(row, column).toString();
                try {
                    d.modifyItem(value,textField1.getText(),textField2.getText(),textField3.getText(),textField4.getText(),textField5.getText(),textField6.getText(),textField7.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addToListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                try {
                    listaProduse =  s.readObject();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                BaseProduct bs = (BaseProduct) listaProduse.get(row);
                lista.add(bs);
            }
        });

        createNewProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    d.createItem(lista,textField1.getText(),textField2.getText(),textField3.getText(),textField4.getText(),textField5.getText(),textField6.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LogInForm();
            }
        });

        reportsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ReportsForm();
            }
        });

    }

}
