package cn.cyanbukkit.invatebungee

import cn.cyanbukkit.invatebungee.command.InviteTeleport
import cn.cyanbukkit.invatebungee.command.ManagerShout
import cn.cyanbukkit.invatebungee.command.main.BlockInvite
import cn.cyanbukkit.invatebungee.command.main.CallInvite
import cn.cyanbukkit.invatebungee.command.main.HornMessage
import cn.cyanbukkit.invatebungee.listener.ChatListener
import cn.cyanbukkit.invatebungee.task.Announce
import cn.cyanbukkit.invatebungee.utils.ConfigModel
import cn.cyanbukkit.invatebungee.utils.InviteRequest
import cn.cyanbukkit.mysql.createTables
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.plugin.Plugin
import net.md_5.bungee.config.Configuration
import net.md_5.bungee.config.ConfigurationProvider
import net.md_5.bungee.config.YamlConfiguration
import java.io.File
import java.nio.file.Files

class CyanInvite : Plugin() {


    lateinit var config : Configuration
    lateinit var configFile : File
    lateinit var message: Configuration
    lateinit var messageFile: File

    companion object {
        lateinit var instance: CyanInvite
        lateinit var configModel : ConfigModel
        lateinit var hikariDataSource: HikariDataSource
        val inviteRequests = mutableListOf<InviteRequest>()
        val serverAlias = mutableMapOf<String, String>()
        val blockedMessagePlayers = mutableSetOf<ProxiedPlayer>()

        /**
         * 记录上一次的喊话的时间戳 每次计算下次的统计
         */
        val playerCooldown = mutableMapOf<ProxiedPlayer, Long>()
    }



    override fun onEnable() {
        instance = this
        logger.info("CyanInvite load Config")
        configFile = File(dataFolder, "config.yml")
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            Files.copy(getResourceAsStream("config.yml"), configFile.toPath())
        }
        config = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(configFile)
        messageFile = File(dataFolder, "message.yml")
        if (!messageFile.exists()) {
            messageFile.parentFile.mkdirs()
            Files.copy(getResourceAsStream("message.yml"), messageFile.toPath())
        }
        message = ConfigurationProvider.getProvider(YamlConfiguration::class.java).load(messageFile)
        // 监听
        // mysql
        logger.info("CyanInvite load MySQL")
        val url= config.getString("JDBC_URL")
        val user = config.getString("USERNAME")
        val password = config.getString("PASSWORD")
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = url
        hikariConfig.username = user
        hikariConfig.password = password
        hikariConfig.maximumPoolSize = 10
        hikariConfig.minimumIdle = 5
        hikariConfig.connectionTimeout = 30000
        hikariConfig.idleTimeout = 600000
        hikariConfig.maxLifetime = 1800000
        hikariConfig.connectionInitSql = "SET NAMES 'utf8mb4';"
        hikariDataSource = HikariDataSource(hikariConfig)
        hikariDataSource.createTables()
        logger.info("CyanInvite load MySQL ${hikariDataSource.isRunning}")
        logger.info("CyanInvite load Config Success")
        configModel = ConfigModel(
            config.getString("Commands.喊话"),
            config.getString("Commands.邀请"),
            config.getString("Commands.屏蔽"),
            config.getBoolean("CanBlockedServerSeeMessages"),
            config.getInt("Times.join-cooldown"),
            config.getInt("Times.cooldown"),
            config.getInt("Times.expiry"),
            config.getBoolean("UseShout"),
            config.getInt("DefaultShout"),
                                 )
        config.keys.forEach {
            if (it.startsWith("ServersAlias.")) {
                serverAlias[it.substring(12)] = config.getString(it)
            }
        }
        proxy.pluginManager.registerCommand(this, BlockInvite)
        proxy.pluginManager.registerCommand(this, CallInvite)
        proxy.pluginManager.registerCommand(this, HornMessage)
        proxy.pluginManager.registerCommand(this, InviteTeleport)
        proxy.pluginManager.registerCommand(this, ManagerShout)
        Announce.init()
        proxy.pluginManager.registerListener(this, ChatListener)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
