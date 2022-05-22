import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClientForm extends JFrame {
    private JPanel contentPane2;
    private JTable table1;
    private JButton searchItemButton;
    private JTextField textField1;
    private JButton viewItemsButton;
    private JButton exitButton;
    private JButton createOrderButton;
    private JButton backButton;
    private JButton addToOrderButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    Serializator s = new Serializator();
    List<MenuItem> listaProduse = new ArrayList<>();
    List<MenuItem> listaComanda = new ArrayList<>();
    DeliveryService d = new DeliveryService();
    int ID = 0;

    public ClientForm(String numeClient) {
        setContentPane(contentPane2);
        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 600);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });

        viewItemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table1.setModel( d.tableModel());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

            }
        });

        searchItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table1.setModel(d.searchItem(textField1.getText(),textField2.getText(),textField3.getText(),textField4.getText(),textField5.getText(),textField6.getText(),textField7.getText()));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        addToOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int column = 0;
                int row = table1.getSelectedRow();
                try {
                    listaProduse=s.readObject();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                String value = table1.getModel().getValueAt(row, column).toString();
                for (int i = 0; i < listaProduse.size(); i++) {
                    if (value.equals(listaProduse.get(i).getTitle())) {
                        listaComanda.add(listaProduse.get(i));
                    }
                }
            }
        });

        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ID++;
                try {
                    d.createOrder(listaComanda,ID,numeClient);
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

    }
}
