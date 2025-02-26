package com.theendercore.terastal_expansion.config

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.theendercore.terastal_expansion.TerastalConst.log
import java.io.File
import java.io.FileNotFoundException

data class TerastalConfig(val orbMaxCharges: Int = 10)

object TeraConfigObj {
    init {
        loadConfig()
    }

    private val gson = Gson()
    var cfg = TerastalConfig()

    private val path = "./config/terastal_expansion.json"

    fun loadConfig() = try {
        // Read file content
        val configFile = File(path)
        val jsonContent = configFile.bufferedReader().use { it.readText() }

        // Parse JSON to AppConfig
        cfg = gson.fromJson(jsonContent, TerastalConfig::class.java)
    } catch (e: Exception) {
        when (e) {
            is FileNotFoundException -> log.error("Config file not found!")
            is JsonSyntaxException -> log.error("Could not parse config file!", e)
            else -> log.error("Could not read config file!", e)
        }
        log.info("Using default config")
        cfg = TerastalConfig()
        saveConfig(cfg)
    }


    fun saveConfig(config: TerastalConfig) = try {
        File(path).writeText(gson.toJson(config))
    } catch (e: Exception) {
        log.error("Could not save configuration file!", e)
    }

}