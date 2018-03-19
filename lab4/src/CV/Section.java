package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section(String title){
        this.title = title;
    }

    Section setTitle(String title){
        this.title = title;
        return this;
    }

    Section addParagraph(String paragraphText){
        Paragraph newParagraph = new Paragraph(paragraphText);
        paragraphs.add(newParagraph);
        return this;
    }

    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;
    }

    Section addParagraph(ParagraphWithList p){
        paragraphs.add(p);
        return this;
    }

    void writeHTML(PrintStream out){
        if (paragraphs.isEmpty())
            return;

        out.print("<section>");
        out.printf("\n<h2> %s </h2>\n",title);
        for (Paragraph p : paragraphs) {
            p.writeHTML(out);
        }
        out.print("</section>\n");
    }
}
