package org.mixer2.monet.core;

import java.util.LinkedHashMap;
import java.util.Map;

import org.mixer2.jaxb.xhtml.Html;

public class PluginExecutor {

    protected PluginLoader pluginLoader;
    protected TemplateLoader templateLoader;

    public void setPluginLoader(PluginLoader pluginLoader) {
        this.pluginLoader = pluginLoader;
    }

    public void setTemplateLoader(TemplateLoader templateLoader) {
        this.templateLoader = templateLoader;
    }

    public Html execute(String templatePath) {
        if (pluginLoader == null) {
            // TODO log
        }
        if (templateLoader == null) {
            // TODO log
        }

        LinkedHashMap<Plugin, PluginConfig> pluginMap = pluginLoader.getPluginMap();
        Html html = templateLoader.getTemplate(templatePath);

        for(Map.Entry<Plugin, PluginConfig> map : pluginMap.entrySet()) {
            // TODO configにあわせてexecuteするかどうか判断
            html = map.getKey().execute(html);
        }

        return html;
    }

}
