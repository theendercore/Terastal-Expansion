package com.theendercore.terastal_expansion.misc

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

private val log = mutableListOf<String>()

private val gson = GsonBuilder().setPrettyPrinting().create()

fun addLog(vararg any: Any?) {
    any.forEach { addToLog(it?.toString() ?: "[null]") }
    writeLog()
}

private fun addToLog(str: String) {
    if (str.startsWith(">start")) log.add(">start " + gson.format(str.removePrefix(">start")))
    else if (str.startsWith(">player")) str.split(" ").let { log.add("${it[0]} ${it[1]} ${gson.format(it[2])}") }
    else if (str.startsWith("sideupdate")) {
        str.split("\n").forEach {
            if (it.startsWith("|request|")) log.add("|request|" + gson.format(it.removePrefix("|request|")))
            else log.add(it)
        }
    } else if (str.contains("""{"winner"""")) log.add(gson.format(str))
    else log.add(str)
}

fun writeLog() {
    val file = File("logs.txt")
    if (!file.exists()) file.createNewFile()
    file.writeText(log.joinToString("\n"))
}

private fun Gson.format(str: String): String = this.toJson(gson.fromJson(str, Any::class.java))
