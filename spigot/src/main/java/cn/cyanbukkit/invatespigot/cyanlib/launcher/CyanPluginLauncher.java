package cn.cyanbukkit.invatespigot.cyanlib.launcher;

import cn.cyanbukkit.invatespigot.cyanlib.loader.KotlinBootstrap;
import cn.cyanbukkit.invatespigot.ShoutInvite;
import cn.cyanbukkit.invatespigot.SpigotShoutInvite;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import java.lang.reflect.Field;
import java.sql.SQLException;

import static cn.cyanbukkit.mysql.MySQLKt.createTables;

/**
 * 嵌套框架
 */

public class CyanPluginLauncher extends JavaPlugin {

    public static CyanPluginLauncher cyanPlugin;

    public CyanPluginLauncher() {
        cyanPlugin = this;
        KotlinBootstrap.init();
        KotlinBootstrap.loadDepend("com.zaxxer", "HikariCP", "4.0.3");
        KotlinBootstrap.loadDepend("org.apache.commons", "commons-lang3", "3.12.0");
        KotlinBootstrap.loadDepend("org.slf4j", "slf4j-api", "2.1.0-alpha1");
        // sql 驱动
        KotlinBootstrap.loadDepend("mysql", "mysql-connector-java", "8.0.26");
    }

    public HikariDataSource hikariDataSource;


    @Override
    public void onEnable() {
        saveDefaultConfig();
        String url = getConfig().getString("JDBC_URL");
        String user = getConfig().getString("USERNAME");
        String password = getConfig().getString("PASSWORD");
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(5);
        hikariConfig.setIdleTimeout(60000);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setConnectionInitSql("SELECT 1");
        hikariDataSource = new HikariDataSource(hikariConfig);
        // 检查数据库中是否有shoutinvite 表
        createTables(hikariDataSource);
        ShoutInvite.INSTANCE.register();
        getLogger().info("插件已加载");
        registerCommand(SpigotShoutInvite.INSTANCE);
    }



    public void registerCommand(Command cmd) {
        Class<?> clazz = getServer().getPluginManager().getClass();
        try {
            Field field = clazz.getDeclaredField("commandMap");
            field.setAccessible(true);
            SimpleCommandMap commandMap = (SimpleCommandMap) field.get(getServer().getPluginManager());
            commandMap.register(cyanPlugin.getName(), cmd);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void onDisable() {

    }


}