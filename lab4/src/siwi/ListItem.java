package siwi;

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem(String _content){
        content = _content;
    }

    void writeHTML(PrintStream out){
        out.printf("<li>" + content + "</li>\n");
    }
}
