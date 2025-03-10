package cn.cyanbukkit.invatevelocity.task

import cn.cyanbukkit.invatevelocity.CyanInvite
import com.velocitypowered.api.proxy.ProxyServer
import net.kyori.adventure.text.Component
import java.util.*

object Announce {

    val disableds = mutableListOf<String>()
    var interval = 0

    val enable = mutableMapOf<String, Timer>()

    fun init() {
        val announce = CyanInvite.instance.message.getConfigurationSection("Announcements")!!
        interval = announce.getInt("settings.interval")
        announce.getStringList("settings.disabledServers").forEach { e ->
            disableds.add(e)
        }
        val message = announce.getConfigurationSection("message")!!
        for (key in message.getKeys(false)) {
            val type = key.split(".")[0]
            if (!enable.containsKey(type)) {
                if ( message.getBoolean("${key}.enable") ) {
                    val timer = Timer()
                    timer.schedule(object : TimerTask( ) {
                        override fun run() {
                            val message = CyanInvite.instance.message.getStringList("${key}.message")
                            CyanInvite.instance.server.allPlayers.forEach { p ->
                                if (!disableds.contains(p.currentServer.get().serverInfo.name)) {
                                    message.forEach { m ->
                                        p.sendMessage(Component.text(m.replace("&", "§")))
                                    }
                                }
                            }
                        }
                    }, 0, interval * 1000L)
                }
            }
        }
    }


}