package ae.cyberspeed.scratch.core.initialization;

import ae.cyberspeed.scratch.core.Game;

import java.io.File;
import java.nio.file.Paths;

public final class ConfigFileResolver {

    public static File resolveConfigFile(String configResource) {
        File configFile = new File(configResource);
        if (!configFile.isAbsolute()) {
            String jarFileDirectory = new File(Game.class.getProtectionDomain().getCodeSource().getLocation().getFile())
                    .getParent();
            return Paths.get(jarFileDirectory, configResource).toFile();
        }
        return configFile;
    }
}
