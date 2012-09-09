package org.mixer2.monet.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Html;

public class TemplateLoader {

    protected TemplateMap templateMap = new TemplateMap();

    protected Mixer2Engine m2engine;

    public void setMixer2Engine(Mixer2Engine m2engine) {
        this.m2engine = m2engine;
    }

    public Html getTemplate(String path) {
        Html html = null;
        html = templateMap.get(path);
        if (html == null) {
            html = loadTemplate(path);
            if (html != null) {
                templateMap.putIfAbsent(path, html);
            }
        }
        return html;
    }

    public Html loadTemplate(String path) {
        String monetHome = System.getProperty("monet.home");
        Html html = null;
        if ("classpath".equals(monetHome)) {
            InputStream is = getClass().getResourceAsStream(
                    "/monethome/template/" + path);
            StringBuffer sb = convertStreamToStringBuffer(is);
            html = m2engine.loadHtmlTemplate(sb);
        } else {
            File file = new File(monetHome + "/template/" + path);
            try {
                html = m2engine.loadHtmlTemplate(file);
            } catch (IOException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
        return html;
    }

    private StringBuffer convertStreamToStringBuffer(InputStream is) {
        StringBuffer buffer = new StringBuffer();
        byte[] b = new byte[4096];
        try {
            for (int n; (n = is.read(b)) != -1;) {
              buffer.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return buffer;
    }

}
