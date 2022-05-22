import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DeliveryService implements Serializable {
    private Map<Order, List<MenuItem>> map = new HashMap<>();
    public Serializator s = new Serializator();

    public List<MenuItem> importProducts() {
        List<MenuItem> listaProduse = new ArrayList<>();
        String fileName = "products.csv";
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            List<List<String>> values = lines.map(line -> Arrays.asList(line.split(","))).toList();

            for (int i = 1; i < values.size(); i++) {
                int finalI = i;
                boolean found = listaProduse.stream().anyMatch(produs -> produs.getTitle().equals(values.get(finalI).get(0)));
                if (!found) {
                    BaseProduct bs = new BaseProduct(values.get(i).get(0), Double.parseDouble(values.get(i).get(1)), Integer.parseInt(values.get(i).get(2)),
                            Integer.parseInt(values.get(i).get(3)), Integer.parseInt(values.get(i).get(4)), Integer.parseInt(values.get(i).get(5)),
                            Integer.parseInt(values.get(i).get(6)));
                    listaProduse.add(bs);
                }
            }
            s.writeObject(listaProduse);
        } catch (IOException a) {
            a.printStackTrace();
        }
        return listaProduse;
    }

    public TableModel tableModel() throws IOException, ClassNotFoundException {
        List<MenuItem> listaProduse = new ArrayList<>();
        listaProduse = s.readObject();
        String[] columnName = {"Title", "Rating", "Calories", "Protein", "Fats", "Sodium", "Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        for (int i = 0; i < listaProduse.size(); i++) {
            String[] rand = new String[8];
            rand[0] = listaProduse.get(i).getTitle();
            rand[1] = String.valueOf(listaProduse.get(i).getRating());
            rand[2] = String.valueOf(listaProduse.get(i).getCalories());
            rand[3] = String.valueOf(listaProduse.get(i).getProteins());
            rand[4] = String.valueOf(listaProduse.get(i).getFats());
            rand[5] = String.valueOf(listaProduse.get(i).getSodium());
            rand[6] = String.valueOf(listaProduse.get(i).getPrice());
            model.addRow(rand);
        }
        return model;
    }

    public void addItem(BaseProduct bs) throws IOException, ClassNotFoundException {
        List<MenuItem> listaProduse = new ArrayList<>();
        listaProduse = s.readObject();
        listaProduse.add(bs);
        s.writeObject(listaProduse);
    }

    public void deleteItem(String str) throws IOException, ClassNotFoundException {
        List<MenuItem> listaProduse = new ArrayList<>();
        listaProduse = s.readObject();
        for (int i = 0; i < listaProduse.size(); i++) {
            if (str.equals(listaProduse.get(i).getTitle())) {
                listaProduse.remove(listaProduse.get(i));
                break;
            }
        }
        s.writeObject(listaProduse);
    }

    public void modifyItem(String str, String t1, String t2, String t3, String t4, String t5, String t6, String t7) throws IOException, ClassNotFoundException {
        List<MenuItem> listaProduse = new ArrayList<>();
        listaProduse = s.readObject();
        for (int i = 0; i < listaProduse.size(); i++) {
            if (str.equals(listaProduse.get(i).getTitle())) {
                BaseProduct bs = (BaseProduct) listaProduse.get(i);
                if (!Objects.equals(t1, "")) {
                    bs.setTitle(t1);
                }
                if (!Objects.equals(t2, "")) {
                    bs.setRating(Double.parseDouble(t2));
                }
                if (!Objects.equals(t3, "")) {
                    bs.setCalories(Integer.parseInt(t3));
                }
                if (!Objects.equals(t4, "")) {
                    bs.setProteins(Integer.parseInt(t4));
                }
                if (!Objects.equals(t5, "")) {
                    bs.setFats(Integer.parseInt(t5));
                }
                if (!Objects.equals(t6, "")) {
                    bs.setSodium(Integer.parseInt(t6));
                }
                if (!Objects.equals(t7, "")) {
                    bs.setPrice(Integer.parseInt(t7));
                }
                listaProduse.set(i, bs);
            }
        }
        s.writeObject(listaProduse);
    }

    public void createItem(List<BaseProduct> lista, String t1, String t2, String t3, String t4, String t5, String t6) throws IOException, ClassNotFoundException {
        List<MenuItem> listaProduse = new ArrayList<>();
        listaProduse = s.readObject();
        int pretFinal = 0;
        for (int i = 0; i < lista.size(); i++) {
            pretFinal += lista.get(i).getPrice();
        }
        BaseProduct bs = new BaseProduct();
        bs.setTitle(t1);
        bs.setRating(Double.parseDouble(t2));
        bs.setCalories(Integer.parseInt(t3));
        bs.setProteins(Integer.parseInt(t4));
        bs.setFats(Integer.parseInt(t5));
        bs.setSodium(Integer.parseInt(t6));
        bs.setPrice(pretFinal);
        listaProduse.add(bs);
        lista.clear();
        s.writeObject(listaProduse);
    }

    public TableModel searchItem(String t1, String t2, String t3, String t4, String t5, String t6, String t7) throws IOException, ClassNotFoundException {
        List<MenuItem> listaProduse = new ArrayList<>();
        listaProduse = s.readObject();
        List<MenuItem> lista = new ArrayList<>();

        if (!Objects.equals(t1, "")) {
            for (int i = 0; i < listaProduse.size(); i++) {
                if (listaProduse.get(i).getTitle().contains(t1)) {
                    lista.add(listaProduse.get(i));
                }
            }
        } else {
            lista = listaProduse;
        }
        if (!Objects.equals(t2, "")) {
            List<MenuItem> lista2 = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getRating() == Double.parseDouble(t2)) {
                    lista2.add(lista.get(i));
                }
            }
            lista = lista2;
        }
        if (!Objects.equals(t3, "")) {
            List<MenuItem> lista2 = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCalories() == Integer.parseInt(t3)) {
                    lista2.add(lista.get(i));
                }
            }
            lista = lista2;
        }
        if (!Objects.equals(t4, "")) {
            List<MenuItem> lista2 = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getProteins() == Integer.parseInt(t4)) {
                    lista2.add(lista.get(i));
                }
            }
            lista = lista2;
        }
        if (!Objects.equals(t5, "")) {
            List<MenuItem> lista2 = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getFats() == Integer.parseInt(t5)) {
                    lista2.add(lista.get(i));
                }
            }
            lista = lista2;
        }
        if (!Objects.equals(t6, "")) {
            List<MenuItem> lista2 = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getSodium() == Integer.parseInt(t6)) {
                    lista2.add(lista.get(i));
                }
            }
            lista = lista2;
        }
        if (!Objects.equals(t7, "")) {
            List<MenuItem> lista2 = new ArrayList<>();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getPrice() == Integer.parseInt(t7)) {
                    lista2.add(lista.get(i));
                }
            }
            lista = lista2;
        }
        String[] columnName = {"Title", "Rating", "Calories", "Protein", "Fats", "Sodium", "Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnName);
        for (int i = 0; i < lista.size(); i++) {
            String[] rand = new String[8];
            rand[0] = lista.get(i).getTitle();
            rand[1] = String.valueOf(lista.get(i).getRating());
            rand[2] = String.valueOf(lista.get(i).getCalories());
            rand[3] = String.valueOf(lista.get(i).getProteins());
            rand[4] = String.valueOf(lista.get(i).getFats());
            rand[5] = String.valueOf(lista.get(i).getSodium());
            rand[6] = String.valueOf(lista.get(i).getPrice());
            model.addRow(rand);
        }
        return model;
    }

    public void createOrder(List<MenuItem> listaComanda, int ID, String numeClient) throws IOException, ClassNotFoundException {
        Order order = new Order();
        List<Client> client = s.deserializareClient();
        for (Client c : client
        ) {
            if (c.getName().equals(numeClient)) {
                order.setClientID(c.getId());
            }
        }
        order.setOrderID(ID);
        int valoareComanda = 0;
        for (int i = 0; i < listaComanda.size(); i++) {
            valoareComanda += listaComanda.get(i).getPrice();
        }
        FileWriter myWriter = null;
        try {
            Date date = new Date();
            myWriter = new FileWriter("Bill" + ID + ".txt");
            myWriter.write(String.valueOf(date) + "\n");
            for (int j = 0; j < listaComanda.size(); j++) {
                myWriter.write(listaComanda.get(j).getTitle() + ",");
            }
            myWriter.write("\n" + numeClient);
            myWriter.write("\n" + "Pret total: " + valoareComanda);
            myWriter.close();
            order.setOrderDate(date);
            map.put(order, listaComanda);
            s.serializareMap(map);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        listaComanda.clear();
    }

    public void raport1(int starHour, int endHour) {
        FileWriter fl = null;
        try {
            fl = new FileWriter("Report1.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int contor = 1;
        while (contor != 0) {
            try {
                File file = new File("Bill" + contor + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = br.readLine();
                String ora = st.substring(11, 13);
                //System.out.println(ora);
                int hour = Integer.parseInt(ora);
                //System.out.println(hour);
                if (hour > starHour && hour < endHour) {
                    fl.write("Bill" + contor + " has been placed between " + starHour + " and " + endHour + "\n");
                }
                contor++;
            } catch (FileNotFoundException ex) {
                contor = 0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            fl.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void raport2(int number) {
        FileWriter fl = null;
        try {
            fl = new FileWriter("Report2.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int contor = 1;
        Map<String, Integer> map = new HashMap<>();
        while (contor != 0) {
            try {
                File file = new File("Bill" + contor + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = br.readLine();
                String linieProduse = br.readLine();
                String[] produse = linieProduse.split(",");
                List<String> p = Arrays.stream(produse).toList();
                //next we use lambda expression
                p.stream().forEach((s) -> {
                    if (map.containsKey(s))
                        map.put(s, map.get(s) + 1);
                    else {
                        map.put(s, 1);
                    }
                });
                contor++;
            } catch (FileNotFoundException ex) {
                contor = 0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        FileWriter finalFl = fl;
        map.forEach((k, v) -> {
            if (v > number) {
                try {
                    finalFl.write(k + ", " + v + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        try {
            fl.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void raport3(int numar, int valoareMaxima) {
        FileWriter fl = null;
        try {
            fl = new FileWriter("Report3.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int contor = 1;
        Map<String, List<Integer>> map = new HashMap<>();
        while (contor != 0) {
            try {
                File file = new File("Bill" + contor + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = br.readLine();
                String linieProduse = br.readLine();
                String client = br.readLine();
                String s = br.readLine();
                int pret = Integer.parseInt(s.substring(12));
                List<Integer> lista = new ArrayList<>();
                lista.add(1);
                lista.add(pret);
                if (map.containsKey(client)) {
                    List<Integer> l = new ArrayList<>();
                    l.add(map.get(client).get(0) + 1);
                    l.add(map.get(client).get(1) + lista.get(1));
                    map.put(client, l);
                } else {
                    map.put(client, lista);
                }
                contor++;
            } catch (FileNotFoundException ex) {
                contor = 0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        FileWriter finalFl = fl;
        map.forEach((k, v) -> {
            try {
                if (v.get(0) > numar && v.get(1) > valoareMaxima)
                    finalFl.write(k + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        try {
            fl.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void raport4(int day) {
        FileWriter fl = null;
        try {
            fl = new FileWriter("Report4.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int contor = 1;
        Map<String, Integer> map = new HashMap<>();
        while (contor != 0) {
            try {
                File file = new File("Bill" + contor + ".txt");
                BufferedReader br = new BufferedReader(new FileReader(file));
                String st = br.readLine();
                String s = st.substring(8, 10);
                int zi = Integer.parseInt(s);
                if (zi == day) {
                    String linieProduse = br.readLine();
                    String[] produse = linieProduse.split(",");
                    List<String> p = Arrays.stream(produse).toList();
                    //next we use lambda expression
                    p.stream().forEach((a) -> {
                        if (map.containsKey(a))
                            map.put(a, map.get(a) + 1);
                        else {
                            map.put(a, 1);
                        }
                    });
                }
                contor++;
            } catch (FileNotFoundException ex) {
                contor = 0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        FileWriter finalFl = fl;
        map.forEach((k, v) -> {
            try {
                finalFl.write(k + ", " + v + "\n");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        try {
            fl.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}