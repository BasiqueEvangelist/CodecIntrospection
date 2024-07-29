package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.record;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/codecs/RecordCodecBuilder$2", remap = false)
public interface RecordMapCodecAccessor {
    @Accessor
    RecordCodecBuilder<?, ?> getVal$builder();
}
