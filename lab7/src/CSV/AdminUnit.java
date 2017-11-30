package CSV;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox box = new BoundingBox();

    AdminUnit(String name, double area, int admin_level, double population, double density){
        this.name = name;
        this.area = area;
        this.adminLevel = admin_level;
        this.population = population;
        this.density = density;
    }

    @Override
    public String toString() {
        return name + " " + Double.toString(area) + " "
                + Double.toString(adminLevel) +  " "
                + Double.toString(population) + " "
                + Double.toString(density);
    }
}
