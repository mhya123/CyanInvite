package cn.cyanbukkit.invatevelocity;

import cn.cyanbukkit.invatevelocity.commands.InviteTeleport
import cn.cyanbukkit.invatevelocity.commands.ManagerShout
import cn.cyanbukkit.invatevelocity.commands.main.BlockInvite
import cn.cyanbukkit.invatevelocity.commands.main.CallInvite
import cn.cyanbukkit.invatevelocity.commands.main.HornMessage
import cn.cyanbukkit.invatevelocity.config.file.YamlConfiguration
import cn.cyanbukkit.invatevelocity.listener.ChatListener
import cn.cyanbukkit.invatevelocity.task.Announce
import cn.cyanbukkit.invatevelocity.utils.ConfigModel
import cn.cyanbukkit.invatevelocity.utils.InviteRequest
import cn.cyanbukkit.mysql.createTables
import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.Logger
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

@Plugin(id = "cyaninvite", name = "CyanInvite", version = BuildConstants.VERSION, description = "VC版",
        authors = ["CyanBukkit"])
class CyanInvite @Inject constructor(
    val server: ProxyServer,
    val logger: Logger,
    @DataDirectory val dataDirectory: Path
                                    ) {
    fun getResourceAsStream(resourcePath: String): InputStream? {
        return javaClass.getResourceAsStream(resourcePath)
    }

    lateinit var configFile: File
    lateinit var config: YamlConfiguration
    lateinit var message: YamlConfiguration
    lateinit var messageFile: File

    companion object {
        lateinit var configModel : ConfigModel
        lateinit var instance: CyanInvite
        val blockedMessagePlayers = mutableSetOf<Player>()
        val inviteRequests = mutableListOf<InviteRequest>()
        val serverAlias = mutableMapOf<String, String>()
        lateinit var hikariDataSource: HikariDataSource



        /**
         * 记录上一次的喊话的时间戳 每次计算下次的统计
         */
        val playerCooldown = mutableMapOf<Player, Long>()
    }

    @Subscribe fun onProxyInitialization(e: ProxyInitializeEvent?) {
        instance = this
        logger.info("CyanInvite load Config")
        configFile = File(dataDirectory.toString(), "config.yml")
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            getResourceAsStream("/config.yml")?.use { inputStream ->
                Files.copy(inputStream as InputStream, configFile.toPath())
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile)
        messageFile = File(dataDirectory.toString(), "message.yml")
        if (!messageFile.exists()) {
            messageFile.parentFile.mkdirs()
            getResourceAsStream("/message.yml")?.use { inputStream ->
                Files.copy(inputStream as InputStream, messageFile.toPath())
            }
        }
        message = YamlConfiguration.loadConfiguration(messageFile)
        logger.info("CyanInvite load MySQL")
        val url = config.getString("JDBC_URL")
        val user = config.getString("USERNAME")
        val password = config.getString("PASSWORD")
        val hikariConfig = HikariConfig()
        hikariConfig.jdbcUrl = url
        hikariConfig.username = user
        hikariConfig.password = password
        hikariConfig.driverClassName = "com.mysql.cj.jdbc.Driver"
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
            config.getString("Commands.喊话")!!,
            config.getString("Commands.邀请")!!,
            config.getString("Commands.屏蔽")!!,
            config.getBoolean("CanBlockedServerSeeMessages"),
            config.getInt("Times.join-cooldown"),
            config.getInt("Times.cooldown"),
            config.getInt("Times.expiry"),
            config.getBoolean("UseShout"),
            config.getInt("DefaultShout"),
                                 )
        config.getKeys(true).forEach {
            if (it.startsWith("ServersAlias.")) {
                serverAlias[it.substring(12)] = config.getString(it)!!
            }
        }
        server.eventManager.register(this, ChatListener)
        server.commandManager.register(server.commandManager.metaBuilder(configModel.blockCommand)
                                           .plugin(this)
                                           .build(), BlockInvite)
        server.commandManager.register(server.commandManager.metaBuilder(configModel.inviteCommand)
                                             .plugin(this)
                                             .build(), CallInvite)
        server.commandManager.register(server.commandManager.metaBuilder(configModel.shoutCommand)
                                             .plugin(this)
                                             .build(), HornMessage)
        server.commandManager.register(server.commandManager.metaBuilder("itp")
                                             .plugin(this)
                                             .build(), InviteTeleport)
        server.commandManager.register(server.commandManager.metaBuilder("mshout")
                                             .plugin(this)
                                             .build(), ManagerShout)
        Announce.init()

    }
}
