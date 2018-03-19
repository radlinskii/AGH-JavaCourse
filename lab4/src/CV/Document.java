package CV;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

class Document {
    private String title;
    private List<Section> sections = new ArrayList<>();
    private Photo photo;

    Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document addPhoto(String photoUrl){
        photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section newSection = new Section(sectionTitle);
        newSection.setTitle(sectionTitle);
        sections.add(newSection);
        return newSection;
    }

    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.printf("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title> %s</title>\n" +
                "</head>\n" +
                "<body>\n", title);

        out.printf("<h1> %s </h1>\n",title);
        photo.writeHTML(out);
        for (Section s : sections) {
            s.writeHTML(out);
        }

        out.print("</body>\n" +
                "</html>");
    }
}
