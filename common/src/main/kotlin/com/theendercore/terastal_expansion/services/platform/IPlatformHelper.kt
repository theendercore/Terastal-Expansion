package com.theendercore.terastal_expansion.platform.services

interface IPlatformHelper {
    fun getPlatformName(): String
    fun isModLoaded(modId: String): Boolean
    fun isDevelopmentEnvironment(): Boolean
}