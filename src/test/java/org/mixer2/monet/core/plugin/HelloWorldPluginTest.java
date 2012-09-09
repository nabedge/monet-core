package org.mixer2.monet.core.plugin;

import static org.junit.Assert.*;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;
import org.mixer2.Mixer2Engine;
import org.mixer2.jaxb.xhtml.Html;
import org.mixer2.monet.core.PluginExecutor;
import org.mixer2.monet.core.PluginLoader;
import org.mixer2.monet.core.TemplateLoader;

public class HelloWorldPluginTest {

    @Test
    public void test() throws ConfigurationException {
        System.setProperty("monet.home", "classpath");

        Mixer2Engine m2engine = new Mixer2Engine();
        PluginLoader pluginLoader = new PluginLoader();
        pluginLoader.init();
        TemplateLoader templateLoader = new TemplateLoader();
        templateLoader.setMixer2Engine(m2engine);

        PluginExecutor pluginExecutor = new PluginExecutor();
        pluginExecutor.setPluginLoader(pluginLoader);
        pluginExecutor.setTemplateLoader(templateLoader);

        Html html = pluginExecutor.execute("/HelloWorld.html");
        System.out.println(m2engine.saveToString(html));
    }

}
