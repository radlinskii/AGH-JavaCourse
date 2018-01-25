package poprawa;

import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

class ListItem {
    @XmlValue()
    String content;

    ListItem(String content) {
        this.content = content;
    }

    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n", content);
    }
}
