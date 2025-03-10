package cn.cyanbukkit.invatebungee.utils

import net.md_5.bungee.api.config.ServerInfo
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*

data class ConfigModel(
    val shoutCommand: String,
    val inviteCommand: String,
    val blockCommand: String,
    val canBlockServerSeeMessage: Boolean,
    val joinCooldownTime: Int,
    val cooldownTime: Int,
    val expiryTime: Int,
    val useShout: Boolean,
    val defaultShout: Int,
                      )
data class InviteRequest(
    val inviter : ProxiedPlayer,
    val serverInfo : ServerInfo = inviter.server.info,
    val token : UUID = UUID.randomUUID(),
    val inviteTime : Long = System.currentTimeMillis()
                        )
