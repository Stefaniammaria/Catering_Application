import java.io.IOException;
import java.util.Date;

public class Test {

    public static void main(String[] args) throws IOException {
        /*Date data = new Date();
        System.out.println(data);*/

        LogInForm lg = new LogInForm();
        lg.setVisible(true);

        Administrator administrator = new Administrator("Admin","Admin123");
        Serializator serializator = new Serializator();
        serializator.serializeAdmin(administrator);

    }

}
