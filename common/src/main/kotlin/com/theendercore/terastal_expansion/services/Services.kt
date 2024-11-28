package com.theendercore.terastal_expansion.platform

import com.theendercore.terastal_expansion.TeraConst
import java.lang.NullPointerException
import java.util.ServiceLoader

object Services {
    @JvmStatic
//    val PLATFORM = load<IPlatformHelper>(IPlatformHelper::class.java)
    fun <T> load(clazz: Class<T>): T {
        val loadedService = ServiceLoader.load<T>(clazz)
            .findFirst()
            .orElseThrow { NullPointerException("Failed to load service for " + clazz.getName()) }
        TeraConst.log.debug("Loaded {} for service {}", loadedService, clazz)
        return loadedService
    }
}