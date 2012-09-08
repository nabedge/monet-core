package org.mixer2.monet.core;

import static org.junit.Assert.*;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

public class PluginLoaderTest {

    @Test
    public void testInit() throws ConfigurationException {
        PluginLoader pluginLoader = new PluginLoader();
        pluginLoader.init();
    }

}
