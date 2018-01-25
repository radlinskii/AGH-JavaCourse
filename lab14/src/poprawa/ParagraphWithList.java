package poprawa;

import javax.xml.bind.annotation.XmlElement;
import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {
    @XmlElement(name="list")
    UnorderedList unorderedList;

    public ParagraphWithList(){
        unorderedList = new UnorderedList();
    }

    public ParagraphWithList(String text) {
        super(text);
        unorderedList = new UnorderedList();
    }

    @Override
    public ParagraphWithList setContent(String newContent) {
        content = newContent;
        return this;
    }

    public ParagraphWithList addListItem(String content) {
        unorderedList.addItem(new ListItem(content));
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>\n");
        unorderedList.writeHTML(out);
        out.printf("</p>\n");
    }


}
