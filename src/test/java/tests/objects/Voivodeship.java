package tests.objects;

public class Voivodeship {
    String id;
    String name;

    public Voivodeship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean equalsOnList(Object o) {
        if (this == o) return true;
        if (!(o instanceof Voivodeship)) return false;
        Voivodeship voivodeship = (Voivodeship) o;

        if (name != null ? !name.equals(voivodeship.name) : voivodeship.name != null) return false;

        return true;
    }
}
