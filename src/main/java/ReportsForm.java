import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportsForm extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton report4Button;
    private JButton report1Button;
    private JButton report2Button;
    private JButton report3Button;
    private JPanel contantePainR;
    private JButton backButton;
    DeliveryService d = new DeliveryService();
    public ReportsForm() {

        setContentPane(contantePainR);
        setTitle("Administrator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 600);

        report1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int starHour = Integer.parseInt(textField1.getText());
                int endHour = Integer.parseInt(textField2.getText());

                d.raport1(starHour,endHour);
            }
        });

        report2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int number = Integer.parseInt(textField3.getText());
                d.raport2(number);
            }
        });

        report3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numar = Integer.parseInt(textField3.getText());
                int valoareMaxima = Integer.parseInt(textField4.getText());
                d.raport3(numar,valoareMaxima);
            }
        });

        report4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int day = Integer.parseInt(textField5.getText());
                d.raport4(day);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdministratorForm();
            }
        });

    }

}
