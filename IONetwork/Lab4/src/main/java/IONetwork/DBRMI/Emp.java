package IONetwork.DBRMI;

import java.io.Serializable;

public class Emp implements Serializable {
    public int id;
    public String name;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
