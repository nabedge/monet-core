package org.mixer2.monet.core;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class PluginLoader {

    protected XMLConfiguration monetConfig = new XMLConfiguration();

    protected LinkedHashMap<Plugin,PluginConfig> pluginMap = new LinkedHashMap<Plugin,PluginConfig>();

    public LinkedHashMap<Plugin,PluginConfig> getPluginMap() {
        return pluginMap;
    }

    public void init() throws ConfigurationException {
        InputStream inputStream = getClass().getResourceAsStream("/monet.xml");
        if (inputStream == null) {
            throw new ConfigurationException("monet.xml not found on classpath");
        }
        // TODO クラスパス直下にない場合は monet.configration プロパティで指定されたフルパスから探す

        // load setting
        monetConfig.load(inputStream);

        /*
        pluginタグはfilesetタグを1個以上持つ。
        filesetタグはinclude,includesfile,exclude,excludesfileタグを0個以上持つ
         */

        List<HierarchicalConfiguration> plugins = monetConfig.configurationsAt("plugins.plugin");

        for (HierarchicalConfiguration pluginConfig : plugins) {
            String className = pluginConfig.getString("[@class]");
            XMLConfiguration XMLPluginConfig = new XMLConfiguration(pluginConfig);
            Plugin plugin = null;
            try {
                plugin = (Plugin) Class.forName(className).newInstance();
            } catch (InstantiationException e) {
                //TODO log
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                //TODO log
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                //TODO log
                e.printStackTrace();
            } finally {
                if (plugin != null) {
                    pluginMap.put(plugin, new PluginConfig(XMLPluginConfig));
                }
            }
        }
    }

}
