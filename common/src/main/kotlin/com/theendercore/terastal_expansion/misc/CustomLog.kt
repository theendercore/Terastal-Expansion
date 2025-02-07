package com.theendercore.terastal_expansion.misc

import java.io.File

private val log = mutableListOf<String>()

fun addLog(vararg any: Any?) {
    any.forEach { log.add(it?.toString() ?: "[null]") }
    writeLog()
}

fun writeLog() {
    val file = File("logs.txt")
    if (!file.exists()) file.createNewFile()
    file.writeText(log.joinToString("\n"))
}