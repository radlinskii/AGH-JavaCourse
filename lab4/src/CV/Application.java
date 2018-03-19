package CV;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Application {

    public static void main(String [] args) throws FileNotFoundException, UnsupportedEncodingException {
        Document cv = new Document("Jana Kowalski - CV");
        cv.addPhoto("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTndn2xoW2S2ezvFJagOdG_nnhaJy6n1nKDPSU6dXDBM5sRIX9T7A");
        cv.addSection("Wykształcenie")
                .addParagraph("2000-2005 Przedszkole im. Królewny Snieżki w ...")
                .addParagraph("2006-2012 SP7 im Ronalda Regana w ...")
                .addParagraph("...");
        cv.addSection("Umiejętności")
                .addParagraph(
                        new ParagraphWithList().setContent("Umiejętności")
                                .addListItem("C")
                                .addListItem("C++")
                                .addListItem("Java")
                );
        try {
            PrintStream writeToCV = new PrintStream(
                    new FileOutputStream("cv.html", false));
            cv.writeHTML(writeToCV);

            writeToCV.close();
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        }
    }
}
