package CV;

import java.io.PrintStream;

class Photo {
    private String url;

    Photo(String url){
        this.url =url;
    }

    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Smiley face\" height=\"42\" width=\"42\"/>\n",url);
    }
}