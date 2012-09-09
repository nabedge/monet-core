package org.mixer2.monet.core;

import java.util.concurrent.ConcurrentHashMap;

import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.xhtml.exception.TagTypeUnmatchException;

public class TemplateMap extends ConcurrentHashMap<String,Html> {

    private static final long serialVersionUID = 3912169900807251477L;

    public Html get(Object key) {
        Html html = super.get(key);
        if (html == null) {
            return null;
        }
        try {
            html = super.get(key).copy(Html.class);
        } catch (TagTypeUnmatchException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return html;
    }

}
