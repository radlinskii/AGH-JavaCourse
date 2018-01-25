package poprawa;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Document {
    @XmlElement
    private String title;
    @XmlElement(name="section")
    private List<Section> sections = new ArrayList<>();
    @XmlElement
    private Photo photo;


    Document(){}

    public Document(String title){
        this.title = title;
    }

    Document setTitle(String title){
        this.title = title;
        return this;
    }

    public Document addPhoto(String photoUrl){
        photo = new Photo(photoUrl);
        return this;
    }

    public Section addSection(String sectionTitle){
        // utwórz sekcję o danym tytule i dodaj do sections
        Section newSection = new Section(sectionTitle);
        newSection.setTitle(sectionTitle);
        sections.add(newSection);
        return newSection;
    }

    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    public void writeHTML(PrintStream out){
        // zapisz niezbędne znaczniki HTML
        // dodaj tytuł i obrazek
        // dla każdej sekcji wywołaj section.writeHTML(out)
        out.printf("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title> %s</title>\n" +
                "</head>\n" +
                "<body>\n", title);

        out.printf("<h1> %s </h1>\n",title);
        for (Section s : sections) {
            s.writeHTML(out);
        }

        out.printf("</body>\n" +
                "</html>");
    }

    public void write(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            FileWriter writer= new FileWriter(fileName);;
            m.marshal(this, writer);
        } catch (JAXBException | IOException ex) {
            ex.printStackTrace();
        }

    }
    public static Document read(String fileName){
        try {
            JAXBContext jc = JAXBContext.newInstance(Document.class);
            Unmarshaller m = jc.createUnmarshaller();
            FileReader reader = new FileReader(fileName);
            return (Document) m.unmarshal(reader);
        } catch (JAXBException | FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
