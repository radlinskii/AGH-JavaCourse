package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class UnorderedList {
    List<ListItem> list;

    UnorderedList(){
        list = new ArrayList<>();
    }

    void addItem(ListItem item){
        list.add(item);
    }

    void writeHTML(PrintStream out) {
        out.print("<ul>\n");
        for (ListItem i : list) {
            i.writeHTML(out);
        }
        out.print("</ul>\n");
    }
}
