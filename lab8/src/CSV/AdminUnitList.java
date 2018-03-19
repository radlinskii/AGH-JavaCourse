package CSV;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class AdminUnitList {
    public List<AdminUnit> getUnits() {
        return units;
    }

    private List<AdminUnit> units = new ArrayList<>();
    private Map<AdminUnit,Long> parentIdMap = new HashMap<>();
    private Map<Long, AdminUnit> selfIdMap = new HashMap<>();

    private Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();

    public Map<Long, List<AdminUnit>> getParentid2child() {
        return parentid2child;
    }

    void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename);
        while (reader.next()) {
            String name;
            double area;
            int admin_level;
            double population;
            double density;
            long parentID;
            long ID;

            double y1 = reader.getDouble("y1");
            double y2 = reader.getDouble("y2");
            double y3 = reader.getDouble("y3");
            double y4 = reader.getDouble("y4");
            double x1 = reader.getDouble("x1");
            double x2 = reader.getDouble("x2");
            double x3 = reader.getDouble("x3");
            double x4 = reader.getDouble("x4");

            area = reader.getDouble("area");
            name = reader.get("name");
            admin_level = reader.getInt("admin_level");
            population = reader.getDouble("population");
            density = reader.getDouble("density");
            parentID = reader.getLong("parent");
            ID = reader.getLong("id");



            AdminUnit node = new AdminUnit(name, area, admin_level, population, density);
            parentIdMap.put(parentID != -1 ? node : null, parentID);


            node.box.addPoint(x1,y1);
            node.box.addPoint(x2,y2);
            node.box.addPoint(x3,y3);
            node.box.addPoint(x4,y4);


            selfIdMap.put(ID, node);
            units.add(node);
        }
        for(AdminUnit node : units){
            AdminUnit parent = selfIdMap.get(parentIdMap.get(node));
            node.setParent(parent);

            if(parent != null){
                parent.children.add(node);
            }

        }
        for(AdminUnit node : units) {
            node.fixMissingValues();
        }

    }

    void list(PrintStream out){
        for (AdminUnit unit : units) {
            out.println(unit.toString());
        }
    }

    void list(PrintStream out,int offset, int limit ){
        int index = 0;
        for (AdminUnit unit : units) {
            if (index>=offset && limit>0){
                limit--;
                out.println(unit.toString());
            }
            index++;
        }
    }


    AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        // przeiteruj po zawartości units
        // jeżeli nazwa jednostki pasuje do wzorca dodaj do ret

        for (AdminUnit unit : units) {
            String name = unit.getName();
            if(regex){
                if(name.matches(pattern)){
                    ret.units.add(unit);
                }
            } else  {
                if(name.contains(pattern)){
                    ret.units.add(unit);
                }
            }
        }
        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) throws CantCenterEmptyBoundingBox {
        AdminUnitList neighbors = new AdminUnitList();
        for(AdminUnit adminUnit : this.units) {
            if(adminUnit.getAdminLevel() == unit.getAdminLevel()){
                if(adminUnit.getAdminLevel() >= 8 ){
                    if(maxdistance >= adminUnit.getBox().distanceTo(unit.getBox())){
                        neighbors.units.add(adminUnit);
                    }
                } else {
                    if(unit.getBox().intersects(adminUnit.getBox())){
                        neighbors.units.add(adminUnit);
                    }
                }
            }
        }
        return neighbors;
    }

}
