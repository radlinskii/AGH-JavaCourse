package siwi;

import java.io.PrintStream;

public class Paragraph {
    String content;

    Paragraph(String _content){
        content = _content;
    }

    public void setContent(String _content) {
        content = _content;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>" + content + "</p>\n");
    }

}
