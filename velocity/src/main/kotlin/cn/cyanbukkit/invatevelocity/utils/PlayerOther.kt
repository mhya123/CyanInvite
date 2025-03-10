package cn.cyanbukkit.invatevelocity.utils

import cn.cyanbukkit.invatevelocity.CyanInvite
import cn.cyanbukkit.invatevelocity.CyanInvite.Companion.blockedMessagePlayers
import com.google.common.base.Ascii.equalsIgnoreCase
import com.velocitypowered.api.command.CommandSource
import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.server.RegisteredServer
import com.velocitypowered.api.proxy.server.ServerInfo
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import java.util.*


/**
 * 发送国际化消息
 */
fun Player.sendI18n(key: String, map : Map<String, String> = mapOf()) {
    var text = CyanInvite.instance.message.getString(key)!!.replace("&", "§")
    if (map.isNotEmpty()) {
        map.forEach { (k, v) ->
            println("replace $k to $v old $text new ${text.replace(k, v)}")
            text = text.replace(k, v)
        }
    }
    sendMessage(Component.text(text))
}

/**
 * 发送喊话给全服的玩家
 */
fun Player.sendShout(serverName: RegisteredServer, message: String) {
    val showServerName = CyanInvite.serverAlias[serverName.serverInfo.name] ?:serverName.serverInfo.name
    val text = Component.text().append(Component.text(CyanInvite.instance.message.getString("messages.shout-format")!!
                                 .replace("&", "§")
                                 .replace("%player%", username)
                                 .replace("%server%", showServerName)
                                 .replace("%players%", serverName.playersConnected.size.toString())
                                 .replace("%message%", message)))
    CyanInvite.instance.server.allPlayers.forEach { p ->
        if (p.canShowSendMessage()) p.sendMessage(text)
    }
}

/**
 * 发送邀请给全服的玩家
 */
fun Player.sendInviteRequest(serverName: RegisteredServer) {
    val showServerName = CyanInvite.serverAlias[serverName.serverInfo.name] ?: serverName.serverInfo.name
    var message = Component.text()
    message = message.append(Component.text(CyanInvite.instance.message.getString("messages.invite-format")!!
                                 .replace("&", "§")
                                 .replace("%player%", username)
                                 .replace("%server%", showServerName)
                                 .replace("%players%", serverName.playersConnected.size.toString())))
    val createInviteRequest = InviteRequest(this, serverName)
    CyanInvite.inviteRequests.add(createInviteRequest)
    message = message.clickEvent(ClickEvent.runCommand("/itp ${createInviteRequest.token}"))
    message = message.hoverEvent(Component.text(CyanInvite.instance.message.getString("messages.invite-hover")!!.replace("&", "§")))
    CyanInvite.instance.server.allPlayers.forEach { p ->
        if (p.canShowSendMessage()) p.sendMessage(message)
    }
    Timer().schedule(object : java.util.TimerTask() { // 过期
        override fun run() {
            CyanInvite.inviteRequests.remove(createInviteRequest)
        }
    }, CyanInvite.configModel.expiryTime * 1000L)
}

fun CommandSource.sendMess(str: String) {
    val tab = Component.text(str)
    sendMessage(tab)
}


/**
 * 可以显示 邀请和喇叭的 消息
 */
fun Player.canShowSendMessage() : Boolean {
    if (blockedMessagePlayers.contains(this)) return false
    val server = currentServer.get().serverInfo
    return !CyanInvite.instance.config.getStringList("BlockedServers").contains(server.name)
}

/**
 * 发的文字是否包含敏感词
 */
fun String.isSendBlocked() : Boolean {
    for (word in CyanInvite.instance.message.getStringList("BlockedWords")) {
        val operation = word.substring(0, 1)
        val realWord = word.substring(1)
        when (operation) {
            "^"  -> if (startsWith(realWord)) return true
            else continue

            "$"  -> if (endsWith(realWord)) return true
            else continue

            "*"  -> if (contains(realWord)) return true
            else continue

            else -> if (equalsIgnoreCase(this, realWord)) return true
        }
    }
    return false
}