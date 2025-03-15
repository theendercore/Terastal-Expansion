package com.theendercore.terastal_expansion.client

import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.util.Mth

class TerastalParticle(
    level: ClientLevel,
    x: Double, y: Double, z: Double,
    xSpeed: Double, ySpeed: Double, zSpeed: Double,
    private var sprites: SpriteSet
) : TextureSheetParticle(
    level, x, y, z,
    0.5 - level.random.nextDouble(), ySpeed, 0.5 - level.random.nextDouble()
) {
    private var originalAlpha = 1.0f

    init {
        this.friction = 0.96f
        this.gravity = -0.1f
        this.speedUpWhenYMotionIsBlocked = true
        this.yd *= 0.2
        if (xSpeed == 0.0 && zSpeed == 0.0) {
            this.xd *= 0.1
            this.zd *= 0.1
        }

//        this.quadSize *= 0.75f
        this.lifetime = (8.0 / (Math.random() * 0.8 + 0.2)).toInt()
        this.hasPhysics = false
        this.setSpriteFromAge(sprites)
        if (this.isCloseToScopingPlayer) this.setAlpha(0.0f)
    }

    override fun getRenderType(): ParticleRenderType = ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT
    override fun tick() {
        super.tick()
        this.setSpriteFromAge(this.sprites)
        this.alpha =
            if (this.isCloseToScopingPlayer) 0.0f
            else Mth.lerp(0.05f, this.alpha, this.originalAlpha)

    }

    public override fun setAlpha(alpha: Float) {
        super.setAlpha(alpha)
        this.originalAlpha = alpha
    }

    private val isCloseToScopingPlayer: Boolean
        get() {
            val minecraft = Minecraft.getInstance()
            val localPlayer = minecraft.player
            return localPlayer != null && localPlayer.eyePosition.distanceToSqr(this.x, this.y, this.z) <= 9.0
                    && minecraft.options.cameraType.isFirstPerson && localPlayer.isScoping
        }

    class Provider(private val sprite: SpriteSet) :
        ParticleProvider<ColorParticleOption> {
        override fun createParticle(
            type: ColorParticleOption, level: ClientLevel,
            x: Double, y: Double, z: Double,
            xSpeed: Double, ySpeed: Double, zSpeed: Double
        ): Particle {
            val particle = TerastalParticle(
                level, x, y, z, xSpeed, ySpeed, zSpeed,
                this.sprite
            )
            particle.setColor(type.red, type.green, type.blue)
            particle.setAlpha(type.alpha)
            return particle
        }
    }

}