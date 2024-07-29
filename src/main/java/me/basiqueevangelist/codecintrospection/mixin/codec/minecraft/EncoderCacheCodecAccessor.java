package me.basiqueevangelist.codecintrospection.mixin.codec.minecraft;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "net/minecraft/util/EncoderCache$2")
public interface EncoderCacheCodecAccessor {
    @Accessor
    Codec<?> getVal$codec();
}
