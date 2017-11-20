package CV;

import java.io.PrintStream;

class Paragraph {
    String content;
    Paragraph(String text) {
        this.content = text;
    }

    Paragraph(){ }
    Paragraph setContent(String newContent) {
        content = newContent;
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>\n" + content + "\n</p>\n");
    }

}
