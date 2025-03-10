package cn.cyanbukkit.invatevelocity.utils

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.server.RegisteredServer
import com.velocitypowered.api.proxy.server.ServerInfo
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
    val inviter : Player,
    val serverInfo : RegisteredServer,
    val token : UUID = UUID.randomUUID(),
    val inviteTime : Long = System.currentTimeMillis()
                        )