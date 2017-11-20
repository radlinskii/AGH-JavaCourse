package CV;

import javax.xml.bind.annotation.XmlValue;
import java.io.PrintStream;

class Paragraph {
    @XmlValue()
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
