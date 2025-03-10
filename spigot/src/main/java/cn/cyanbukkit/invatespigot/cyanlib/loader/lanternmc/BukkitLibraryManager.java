// Decompiled with: CFR 0.152
// Class Version: 8
package cn.cyanbukkit.invatespigot.cyanlib.loader.lanternmc;

import cn.cyanbukkit.invatespigot.cyanlib.loader.lanternmc.Logger.adapters.JDKLogAdapter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Objects;

public class BukkitLibraryManager extends LibraryManager {
    private final URLClassLoaderHelper classLoader;

    public BukkitLibraryManager(JavaPlugin plugin) {
        this(plugin, "libs");
    }

    public BukkitLibraryManager(JavaPlugin plugin, String directoryName) {
        super(new JDKLogAdapter(Objects.requireNonNull(plugin, "plugin").getLogger()),
                new File("").toPath(), directoryName);
        this.classLoader = new URLClassLoaderHelper((URLClassLoader) plugin.getClass().getClassLoader(), this);
    }

    @Override
    protected void addToClasspath(Path file) {
        this.classLoader.addToClasspath(file);
    }
}
