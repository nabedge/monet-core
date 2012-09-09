package org.mixer2.monet.core.plugin;

import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.jaxb.xhtml.Span;
import org.mixer2.monet.core.Plugin;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;

public class HelloWorldPlugin implements Plugin {

    public Html execute(Html html) {
        Span hello = null;
        try {
            hello = html.getById("helloMessage", Span.class);
        } catch (TagTypeUnmatchException e) {
            e.printStackTrace();
            return html;
        }
        hello.getContent().clear();
        hello.getContent().add("Hello World !");
        return html;
    }

}
