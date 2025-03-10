package cn.cyanbukkit.invatespigot

import cn.cyanbukkit.mysql.getPlayerShouts
import cn.cyanbukkit.invatespigot.cyanlib.launcher.CyanPluginLauncher
import cn.cyanbukkit.invatespigot.cyanlib.launcher.CyanPluginLauncher.cyanPlugin
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player

object ShoutInvite : PlaceholderExpansion() {
    override fun getIdentifier(): String {
        return "shoutinvite"
    }

    override fun getAuthor(): String {
        return "CyanBukkit"
    }

    override fun getVersion(): String {
        return "1.0.0"
    }

    override fun onPlaceholderRequest(player: Player, params: String): String {
        // 喇叭
        when (params) {
            "shouts" -> {
                val shouts = cyanPlugin.hikariDataSource.getPlayerShouts(player.uniqueId)
                return if (shouts == -1) "0" else shouts.toString()
            }
        }
        return ""
    }




}