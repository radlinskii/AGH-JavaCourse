package CV;

import java.io.PrintStream;

class ListItem {
    String content;

    ListItem(String content) {
        this.content = content;
    }

    void writeHTML(PrintStream out) {
        out.printf("<li>%s</li>\n", content);
    }
}
