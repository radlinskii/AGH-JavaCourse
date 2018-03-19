package poprawa;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class AdminUnitList {
    public List<AdminUnit> getUnits() {
        return units;
    }

    private List<AdminUnit> units = new ArrayList<>();
    private Map<AdminUnit, Long> parentIdMap = new HashMap<>();
    private Map<Long, AdminUnit> selfIdMap = new HashMap<>();

    private Map<Long, List<AdminUnit>> parentid2child = new HashMap<>();

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


            AdminUnit node = new AdminUnit(name, area, admin_level, population, density, ID);
            parentIdMap.put(parentID != -1 ? node : null, parentID);


            node.box.addPoint(x1, y1);
            node.box.addPoint(x2, y2);
            node.box.addPoint(x3, y3);
            node.box.addPoint(x4, y4);


            selfIdMap.put(ID, node);
            units.add(node);
        }
        for (AdminUnit node : units) {
            AdminUnit parent = selfIdMap.get(parentIdMap.get(node));
            node.setParent(parent);

            if (parent != null) {
                parent.children.add(node);
            }

        }
        for (AdminUnit node : units) {
            node.fixMissingValues();
        }

    }

    void list(PrintStream out) {
        for (AdminUnit unit : units) {
            out.println(unit.toString());
        }
    }

    void list(PrintStream out, int offset, int limit) {
        int index = 0;
        for (AdminUnit unit : units) {
            if (index >= offset && limit > 0) {
                limit--;
                out.println(unit.toString());
            }
            index++;
        }
    }

    AdminUnitList selectByName(String pattern, boolean regex) {
        AdminUnitList ret = new AdminUnitList();

        for (AdminUnit unit : units) {
            String name = unit.getName();
            if (regex) {
                if (name.matches(pattern)) {
                    ret.units.add(unit);
                }
            } else {
                if (name.contains(pattern)) {
                    ret.units.add(unit);
                }
            }
        }
        return ret;
    }

    AdminUnitList getNeighbors(AdminUnit unit, double maxdistance) throws CantCenterEmptyBoundingBox {
        AdminUnitList neighbors = new AdminUnitList();
        for (AdminUnit adminUnit : this.units) {
            if (adminUnit.getAdminLevel() == unit.getAdminLevel()) {
                if (adminUnit.getAdminLevel() >= 8) {
                    if (maxdistance >= adminUnit.getBox().distanceTo(unit.getBox())) {
                        neighbors.units.add(adminUnit);
                    }
                } else {
                    if (unit.getBox().intersects(adminUnit.getBox())) {
                        neighbors.units.add(adminUnit);
                    }
                }
            }
        }
        return neighbors;
    }

    void getNeighbors(AdminUnit unit, AdminUnit target, double maxDistance) throws CantCenterEmptyBoundingBox {
        if (unit.getParent() == null)
            return;
        getNeighbors(unit.getParent(), target, maxDistance);
        for (AdminUnit sibling : unit.getParent().getChildren()) {
            System.out.println(sibling.getName());
            if ((sibling.getBox().intersects(target.getBox()) || (sibling.getBox().distanceTo(target.getBox()) < maxDistance)) && sibling.getAdminLevel() == target.getAdminLevel()) {
                target.neighbours.add(sibling);
            }
        }
    }


    AdminUnitList sortInPlaceByName() {
        class SortByName implements Comparator<AdminUnit> {

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        }
        units.sort(new SortByName());
        return this;
    }

    AdminUnitList sortInPlaceByArea() {
        units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.getArea(), o2.getArea());
            }
        });
        return this;
    }

    AdminUnitList sortInPlaceByPopulation() {
        units.sort((o1, o2) -> Double.compare(o1.getPopulation(), o2.getPopulation()));
        return this;
    }

    AdminUnitList sortInplace(Comparator<AdminUnit> cmp) {
        units.sort(cmp);
        return this;
    }

    AdminUnitList sort(Comparator<AdminUnit> cmp) {
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.units = new ArrayList<AdminUnit>(this.getUnits());
        adminUnitList.sortInplace(cmp);
        return adminUnitList;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred) {
        AdminUnitList adminUnitList = new AdminUnitList();
        adminUnitList.units = this.getUnits().stream().filter(pred).collect(Collectors.toList());
        return adminUnitList;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int limit) {
        AdminUnitList newList = new AdminUnitList();
        newList.units = new ArrayList<AdminUnit>(this.units);
        newList.units = units.stream().filter(pred).limit(limit).collect(Collectors.toList());
        return newList;
    }

    AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit) {
        AdminUnitList newList = new AdminUnitList();
        newList.units = new ArrayList<AdminUnit>(this.units);
        newList.units = units.stream().filter(pred).skip(offset).limit(limit).collect(Collectors.toList());
        return newList;
    }

    void writeHTML() throws CantCenterEmptyBoundingBox {

        for (AdminUnit unit : units) {

            Document cv = new Document(unit.getName() + String.valueOf(unit.getId()));
            cv.addSection("Podstawowe informacje")
                    .addParagraph(String.valueOf(unit.getArea()))
                    .addParagraph(String.valueOf(unit.getPopulation()))
                    .addParagraph(String.valueOf(unit.getDensity()));

            Section section = new Section("Linki:");
            section.addParagraph("<a href=\"" + String.valueOf(unit.getParent().getId()) + ".html\">" +
                    unit.getParent().getName() + " <- rodzic </a>")
            ;

            section.addParagraph("Dzieci:");

            ParagraphWithList paragraphWithList = new ParagraphWithList();
            for (AdminUnit child : unit.getChildren()) {

                paragraphWithList.addListItem("<a href=\"" + String.valueOf(child.getId()) + ".html\">" +
                        child.getName() + " <- dziecko </a>");

            }

            section.addParagraph(paragraphWithList);

            section.addParagraph("Sasiedzi:");

            ParagraphWithList paragraphWithList2 = new ParagraphWithList();

            for (AdminUnit neighbour : getNeighbors(unit, 5.0).getUnits()) {

                paragraphWithList2.addListItem("<a href=\"" + String.valueOf(neighbour.getId()) + ".html\">" +
                        neighbour.getName() + " <- sasiad </a>");

            }

            section.addParagraph(paragraphWithList2);
            cv.addSection(section);


            try {
                //PrintWriter writer = new PrintWriter("cv.html", "UTF-8");

                PrintStream writeToCV = new PrintStream(
                        new FileOutputStream(String.valueOf(unit.getId()) + ".html", false));
                cv.writeHTML(writeToCV);

                writeToCV.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    void writeRootsHTML() {
        Document cv = new Document("roots");
        Section section = new Section("Wojewodztwa: ");
        ParagraphWithList paragraphWithList = new ParagraphWithList();
        for (AdminUnit unit :
                units) {
            if (unit.getAdminLevel() == 4) {

                    paragraphWithList.addListItem("<a href=\"" + String.valueOf(unit.getId()) + ".html\">" +
                            unit.getName() + " <- wojewodztwo </a>");

            }
        }
        section.addParagraph(paragraphWithList);
        cv.addSection(section);
        try {
            PrintStream writeToCV = new PrintStream(
                    new FileOutputStream("roots.html", false));
            cv.writeHTML(writeToCV);

            writeToCV.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
