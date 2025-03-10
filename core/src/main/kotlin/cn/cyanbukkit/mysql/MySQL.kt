package cn.cyanbukkit.mysql

import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.SQLException
import java.util.*
import java.util.logging.Logger

fun HikariDataSource.createTables() {
    val query = "CREATE TABLE IF NOT EXISTS `shoutinvite` (" +
            "`uuid` VARCHAR(36) PRIMARY KEY," +
            "`name` VARCHAR(16) NOT NULL," +
            "`shouts` INT NOT NULL" +
            ")"
    try {
        this.connection.use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.executeUpdate()
            }
        }
        println("Tables created successfully.")
    } catch (e: SQLException) {
        println("Failed to create tables: " + e.message)
        throw e
    }
}

fun HikariDataSource.getPlayerShouts(uuid: UUID): Int {
    val query = "SELECT `shouts` FROM `shoutinvite` WHERE `uuid` = ?"
    try {
        this.connection.use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setString(1, uuid.toString())
                stmt.executeQuery().use { rs ->
                    if (rs.next()) {
                        return rs.getInt("shouts")
                    }
                }
            }
        }
    } catch (e: SQLException) {
        println("Failed to retrieve player shouts: " + e.message)
        throw e
    }
    return -1
}

fun HikariDataSource.setPlayerShouts(uuid: UUID, shouts: Int) {
    val query = "UPDATE `shoutinvite` SET `shouts` = ? WHERE `uuid` = ?"
    try {
        this.connection.use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setInt(1, shouts)
                stmt.setString(2, uuid.toString())
                println("Setting player shouts: $shouts: ${stmt.executeUpdate()}")
            }
        }
    } catch (e: SQLException) {
        println("Failed to set player shouts: " + e.message)
        throw e
    }
}

fun HikariDataSource.addPlayerShouts(uuid: UUID, name: String, shouts: Int) {
    // First, check if the player already exists to avoid duplicate entries
    if (getPlayerShouts(uuid) != -1) {
        println("玩家已经在了！")
        setPlayerShouts(uuid, getPlayerShouts(uuid) + shouts)
        return
    }
    // If the player doesn't exist, proceed to insert a new record
    val query = "INSERT INTO `shoutinvite` (`uuid`, `name`, `shouts`) VALUES (?, ?, ?)"
    try {
        this.connection.use { conn ->
            conn.prepareStatement(query).use { stmt ->
                stmt.setString(1, uuid.toString())
                stmt.setString(2, name)
                stmt.setInt(3, shouts)
                stmt.executeUpdate()
            }
        }
    } catch (e: SQLException) {
        println("Failed to create player with shouts: " + e.message)
        throw e
    }
}

fun HikariDataSource.removePlayerShouts(uuid: UUID, shouts: Int) {
    val now = getPlayerShouts(uuid)
    println("Removing $shouts shouts from $uuid now: $now")
    setPlayerShouts(uuid, now - shouts)
}