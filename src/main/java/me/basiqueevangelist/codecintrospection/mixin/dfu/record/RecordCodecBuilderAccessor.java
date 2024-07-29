package me.basiqueevangelist.codecintrospection.mixin.dfu.record;

import com.mojang.serialization.MapDecoder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = RecordCodecBuilder.class, remap = false)
public interface RecordCodecBuilderAccessor {
    @Accessor
    MapDecoder<?> getDecoder();
}
