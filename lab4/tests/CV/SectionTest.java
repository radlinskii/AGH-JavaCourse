package CV;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class SectionTest {
    @org.junit.Test
    public void writeHTML() throws Exception {
        String title = "tytul sekcji";
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        new Section(title).addParagraph("bla bla jestem parafem").writeHTML(ps);
        String result = null;
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertTrue(result.contains("<section>"));
        assertTrue(result.contains("</section>"));
        assertTrue(result.contains("</h2>"));
        assertTrue(result.contains("<h2>"));
        assertTrue(result.contains(title));
    }

}