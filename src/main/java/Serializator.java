import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serializator implements Serializable {

    public void serializeAdmin(Administrator admin) throws IOException {

        FileOutputStream fileOut = new FileOutputStream("D:\\Facultate anul 2\\Sem2\\TP\\PT2022_30223_MARACINE_STEFANIA_ASSIGNMENT_4\\AdminInfo.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(admin);
        out.close();
        fileOut.close();

    }

    public Administrator deserializeAdmin() throws IOException, ClassNotFoundException {

        Administrator admin = null;
        FileInputStream fileIn = new FileInputStream("D:\\Facultate anul 2\\Sem2\\TP\\PT2022_30223_MARACINE_STEFANIA_ASSIGNMENT_4\\AdminInfo.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        admin = (Administrator) in.readObject();
        in.close();
        fileIn.close();
        return admin;
    }

    public void serializareClient(Client client) throws IOException {

        FileWriter fw = new FileWriter("DateLogareClienti.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(client.toString());
        bw.close();

    }


    /*public void serializareClient(Client client) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream("DateLogareClienti.txt");
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file


        objOutputStream.writeObject(client);
        objOutputStream.reset();
        objOutputStream.close();
    }*/

    public List<Client> deserializareClient() throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader("DateLogareClienti.txt"));
        String currentLine = null;
        List<Client> clienti = new ArrayList<>();
        while ((currentLine = bf.readLine()) != null && !currentLine.equals("")) {
            String[] date = currentLine.split(",");
            Client cl = new Client(date[0], date[1], Integer.parseInt(date[2]));
            clienti.add(cl);
        }
        return clienti;
    }


   /* public List<Client> deserializareClient() throws ClassNotFoundException, IOException {
        List<Client> clienti = new ArrayList<>();
        //Create new FileInputStream object to read file
        FileInputStream fis = new FileInputStream("DateLogareClienti.txt");
        //Create new ObjectInputStream object to read object from file
        ObjectInputStream obj = new ObjectInputStream(fis);
        try {
            while (fis.available() != -1) {
                //Read object from file
                Client acc = (Client) obj.readObject();
                clienti.add(acc);
            }
        } catch (EOFException ex) {
            //ex.printStackTrace();
        }
        return clienti;
    }*/


    public void writeObject(List<MenuItem> list) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream("MenuItems.txt");
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file
        for (MenuItem obj : list) {

            objOutputStream.writeObject(obj);
            objOutputStream.reset();
        }
        objOutputStream.close();
    }

    public List<MenuItem> readObject() throws ClassNotFoundException, IOException {
        List<MenuItem> list = new ArrayList();
        //Create new FileInputStream object to read file
        FileInputStream fis = new FileInputStream("MenuItems.txt");
        //Create new ObjectInputStream object to read object from file
        ObjectInputStream obj = new ObjectInputStream(fis);
        try {
            while (fis.available() != -1) {
                //Read object from file
                MenuItem acc = (MenuItem) obj.readObject();
                list.add(acc);
            }
        } catch (EOFException ex) {
            //ex.printStackTrace();
        }
        return list;
    }

    public void serializareMap(Map<Order,List<MenuItem>> map) throws IOException {
        FileOutputStream fos =
                new FileOutputStream("Map.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.close();
        fos.close();
    }

    public Map<Order,List<MenuItem>> deserializareMap() throws ClassNotFoundException, IOException {
        Map<Order,List<MenuItem>> map = new HashMap<>();
        //Create new FileInputStream object to read file
        FileInputStream fis = new FileInputStream("Map.txt");
        //Create new ObjectInputStream object to read object from file
        ObjectInputStream obj = new ObjectInputStream(fis);
        try {
            while (fis.available() != -1) {
                //Read object from file
                Map.Entry<Order,List<MenuItem>> entry = (Map.Entry<Order,List<MenuItem>>) obj.readObject();
                map.put(entry.getKey(),entry.getValue());
            }
        } catch (EOFException ex) {
            //ex.printStackTrace();
        }
        return map;
    }

}
