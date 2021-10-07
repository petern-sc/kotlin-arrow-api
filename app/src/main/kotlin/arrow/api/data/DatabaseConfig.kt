package arrow.api.data

import com.zaxxer.hikari.HikariDataSource
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin

object DatabaseConfig {
    private val dataSource: HikariDataSource = HikariDataSource().apply {
        jdbcUrl = "jdbc:postgresql://localhost:5432/moviedb"
        username = "moviedb"
        password = "moviedb"
        dataSourceProperties = mapOf(
            Pair("cachePrepStmts", "true"),
            Pair("prepStmtCacheSize", "250"),
            Pair("prepStmtCacheSqlLimit", "2048")
        ).toProperties()
    }

    val jdbi: Jdbi = Jdbi.create(dataSource)
        .installPlugin(KotlinPlugin())

}