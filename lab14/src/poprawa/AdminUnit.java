package poprawa;

import java.util.ArrayList;
import java.util.List;

public class AdminUnit {
    private String name;

    public List<AdminUnit> getChildren() {
        return children;
    }

    private int adminLevel;

    public double getPopulation() {
        return population;
    }

    private double population;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public double getArea() {
        return area;
    }

    private double area;

    public double getDensity() {
        return density;
    }

    private double density;

    public void setParent(AdminUnit parent) {
        this.parent = parent;
    }

    private AdminUnit parent;

    public BoundingBox getBox() {
        return box;
    }



    BoundingBox box = new BoundingBox();
    List<AdminUnit> children = new ArrayList<>();
    List<AdminUnit> neighbours = new ArrayList<>();
    AdminUnit(String name, double area, int admin_level, double population, double density, long id) {
        this.name = name;
        this.area = area;
        this.adminLevel = admin_level;
        this.population = population;
        this.density = density;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + Double.toString(area) + " "
                + Double.toString(adminLevel) + " "
                + Double.toString(population) + " "
                + Double.toString(density) + box.toString() + "\n";
    }

    String getName() {
        return name;
    }

    public AdminUnit getParent() {
        return parent;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    void fixMissingValues() {
        if (this.getDensity() == -1) {
            if (this.getParent() != null) {
                if(this.parent.getDensity()==-1) {
                    this.parent.fixMissingValues();
                }
                    this.setDensity(this.parent.getDensity());
            } else {
                this.density = 0;
            }
        }

        if (this.getPopulation() == -1) {
            this.population = this.getArea()*this.getDensity();
        }
    }


    public int getAdminLevel() {
        return adminLevel;
    }
}
