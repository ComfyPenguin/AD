package archivosjson.starwars;

import java.io.Serializable;

public class Character implements Serializable {
    private String name;
    private int mass;
    private String url;
    private int films;
    private int vehicles;

    public Character(String name, int mass, String url, int films, int vehicles) {
        this.name = name;
        this.mass = mass;
        this.url = url;
        this.films = films;
        this.vehicles = vehicles;
    }

    public String getName() {
        return name;
    }

    public int getMass() {
        return mass;
    }

    public String getUrl() {
        return url;
    }

    public int getFilms() {
        return films;
    }

    public int getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Character{");
        sb.append("name='").append(name).append('\'');
        sb.append(", mass=").append(mass);
        sb.append(", url='").append(url).append('\'');
        sb.append(", films=").append(films);
        sb.append(", vehicles=").append(vehicles);
        sb.append('}');
        return sb.toString();
    }
}
