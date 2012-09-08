package org.mixer2.monet.core;

import java.io.InputStream;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class PluginLoader {

    protected XMLConfiguration config = new XMLConfiguration();

    public void init() throws ConfigurationException {
        InputStream inputStream = getClass().getResourceAsStream("/monet.xml");
        if (inputStream == null) {
            throw new ConfigurationException("monet.xml not found on classpath");
        }
        // TODO クラスパス直下にない場合は monet.configration プロパティで指定されたフルパスから探す

        // load setting
        config.load(inputStream);
        List<HierarchicalConfiguration> plugins = config.configurationsAt("plugins.plugin");
        for (HierarchicalConfiguration plugin : plugins) {
            String className = plugin.getString("[@class]");
            System.out.println(className);
            List<HierarchicalConfiguration> filesets = plugin.configurationsAt("fileset");
            System.out.println(filesets.size());
        }
    }

}
