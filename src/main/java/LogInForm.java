import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogInForm extends JFrame {
    private JTextField textField2;
    private JTextField textField1;
    private JButton logInButton;
    private JButton registerButton;
    private JPanel contentPane;

    public LogInForm() {
        setContentPane(contentPane);
        setTitle("Log In");
        setSize(600, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Serializator serializator = new Serializator();
                Administrator administrator = null;
                try {
                    administrator = serializator.deserializeAdmin();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                if (textField1.getText().compareTo(administrator.getUsername()) == 0 && textField2.getText().compareTo(administrator.getPassword()) == 0) {
                    dispose();
                    new AdministratorForm();
                } else {
                    try {
                        for (Client c : serializator.deserializareClient())
                            if (textField1.getText().equals(c.getName()) && textField2.getText().equals(c.getParola())) {
                                dispose();
                                new ClientForm(textField1.getText());

                            }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } /*catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }*/
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Serializator sl = new Serializator();
                int id = 0;
                try {
                    for (Client c : sl.deserializareClient()) {
                        id = c.getId();
                    }

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Client cl = new Client(textField1.getText(), textField2.getText(), id + 1);

                try {
                    sl.serializareClient(cl);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }


}
