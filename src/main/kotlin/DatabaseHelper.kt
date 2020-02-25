package com.example.demo

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.ResultSet
import java.sql.SQLException

class DatabaseHelper {

    companion object {
        // in the format of project:region:instance
        private const val CLOUD_SQL_CONNECTION_NAME = "project:region:instance"
        private const val SOCKET_FACTORY = "com.google.cloud.sql.mysql.SocketFactory"
        private const val JDBC_URL = "jdbc:mysql://127.0.0.1:3306"
        // database username, such as "admin"
        private const val USERNAME = "admin"
        // database password
        private const val PASSWORD = "ajcakjNJNKJNKN"
    }

    private val hikariConfig = HikariConfig().apply {
        jdbcUrl = JDBC_URL
        username = USERNAME
        password = PASSWORD
        addDataSourceProperty("socketFactory",  SOCKET_FACTORY)
        addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME)
        addDataSourceProperty("useSSL", "false")
    }

    fun runQuery(query: String) {
        val pool = HikariDataSource(hikariConfig)
        val con = pool.connection
        val pst = con.prepareStatement(query)
        var rs : ResultSet? = null
        try {
            rs = pst.executeQuery()
            while (rs.next()) {
                // weirdly columnIndex starts from 1
                System.out.format("DATABASE QUERY RESULT: %d %s", rs.getInt(1), rs.getString(2))
            }

        } catch (ex: SQLException) {
            println("error: ${ex.message}")
        } finally {
            try {
                rs?.close()
                pst.close()
                con.close()
                pool.close()
            } catch (ex: SQLException) {
                println("error closing: ${ex.message}")
            }
        }
    }

}