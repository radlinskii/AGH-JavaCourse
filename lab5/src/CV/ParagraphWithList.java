package CV;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    @XmlElement(name="list")
    UnorderedList unorderedList;

    ParagraphWithList(){
        unorderedList = new UnorderedList();
    }

    public ParagraphWithList(String text) {
        super(text);
        unorderedList = new UnorderedList();
    }

    @Override
    ParagraphWithList setContent(String newContent) {
        content = newContent;
        return this;
    }

    ParagraphWithList addListItem(String content) {
        unorderedList.addItem(new ListItem(content));
        return this;
    }

    void writeHTML(PrintStream out){
        out.print("<p>\n");
        unorderedList.writeHTML(out);
        out.print("</p>\n");
    }


}
