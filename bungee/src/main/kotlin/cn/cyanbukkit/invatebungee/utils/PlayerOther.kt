package cn.cyanbukkit.invatebungee.utils

import cn.cyanbukkit.invatebungee.CyanInvite
import cn.cyanbukkit.invatebungee.CyanInvite.Companion.blockedMessagePlayers
import cn.cyanbukkit.invatebungee.CyanInvite.Companion.playerCooldown
import com.google.common.base.Ascii.equalsIgnoreCase
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.config.ServerInfo
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.Timer


/**
 * 发送国际化消息
 */
fun ProxiedPlayer.sendI18n(key: String, map : Map<String, String> = mapOf()) {
    var text = CyanInvite.instance.message.getString(key)
        .replace("&", "§")
    if (map.isNotEmpty()) {
        map.forEach { (k, v) ->
            println("replace $k to $v old $text new ${text.replace(k, v)}")
            text = text.replace(k, v)
        }
    }
    sendMessage(TextComponent(text))
}

/**
 * 发送喊话给全服的玩家
 */
fun ProxiedPlayer.sendShout(serverName: ServerInfo, message: String) {
    val showServerName = CyanInvite.serverAlias[serverName.name] ?: serverName.name
    val text = TextComponent(CyanInvite.instance.message.getString("messages.shout-format")
        .replace("&", "§")
        .replace("%player%", name)
        .replace("%server%", showServerName)
        .replace("%players%", serverName.players.size.toString())
        .replace("%message%", message))
    ProxyServer.getInstance().players.forEach { p ->
        if (p.canShowSendMessage()) p.sendMessage(text)
    }
}

/**
 * 发送邀请给全服的玩家
 */
fun ProxiedPlayer.sendInviteRequest(serverName: ServerInfo) {
    val showServerName = CyanInvite.serverAlias[serverName.name] ?: serverName.name
    val text = TextComponent(CyanInvite.instance.message.getString("messages.invite-format")
        .replace("&", "§")
        .replace("%player%", name)
        .replace("%server%", showServerName)
        .replace("%players%", serverName.players.size.toString()))
    val createInviteRequest = InviteRequest(this, serverName)
    CyanInvite.inviteRequests.add(createInviteRequest)
    text.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/itp ${createInviteRequest.token}")
    text.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf<BaseComponent>(TextComponent(CyanInvite.instance.message.getString("messages.invite-hover").replace("&", "§"))))
    ProxyServer.getInstance().players.forEach { p ->
        if (p.canShowSendMessage()) p.sendMessage(text)
    }
    Timer().schedule(object : java.util.TimerTask() { // 过期
        override fun run() {
            CyanInvite.inviteRequests.remove(createInviteRequest)
        }
    }, CyanInvite.configModel.expiryTime * 1000L)
}

fun CommandSender.sendMess(str: String) {
    val tab = TextComponent(str)
    sendMessage(tab)
}


/**
 * 可以显示 邀请和喇叭的 消息
 */
fun ProxiedPlayer.canShowSendMessage() : Boolean {
    if (blockedMessagePlayers.contains(this)) return false
    if (CyanInvite.instance.config.getStringList("BlockedServers").contains(server.info.name)) return false
    return true
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