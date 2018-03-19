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

            area = reader.getDouble("area");
            name = reader.get("name");
            admin_level = reader.getInt("admin_level");
            population = reader.getDouble("population");
            density = reader.getDouble("density");
            parentID = reader.getLong("parent");
            ID = reader.getLong("id");


            AdminUnit node = new AdminUnit(name, area, admin_level, population, density);
            parentIdMap.put(parentID != -1 ? node : null, parentID);


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



}
