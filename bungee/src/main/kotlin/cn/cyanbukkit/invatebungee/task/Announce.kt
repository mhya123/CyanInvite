package cn.cyanbukkit.invatebungee.task

import cn.cyanbukkit.invatebungee.CyanInvite
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.TextComponent
import java.util.Timer
import java.util.TimerTask

object Announce {

    val disableds = mutableListOf<String>()
    var interval = 0

    val enable = mutableMapOf<String, Timer>()

    fun init() {
        val announce = CyanInvite.instance.message.getSection("Announcements")
        interval = announce.getInt("settings.interval")
        announce.getStringList("settings.disabledServers").forEach { e ->
            disableds.add(e)
        }
        val message = announce.getSection("message")
        for (key in message.keys) {
           val type = key.split(".")[0]
            if (!enable.containsKey(type)) {
                if ( message.getBoolean("${key}.enable") ) {
                    val timer = Timer()
                    timer.schedule(object : TimerTask( ) {
                        override fun run() {
                               val message = CyanInvite.instance.message.getStringList("${key}.message")
                            ProxyServer.getInstance().players.forEach { p ->
                                if (!disableds.contains(p.server.info.name)) {
                                    message.forEach { m ->
                                        p.sendMessage(TextComponent(m.replace("&", "§")))
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