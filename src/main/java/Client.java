public class Client extends Serializator {

    private String name;
    private String parola;
    private int id;

    public Client(){}

    public Client(String name, String parola, int id) {
        this.name = name;
        this.parola = parola;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + "," + parola + "," + id + "\n";
    }

}
