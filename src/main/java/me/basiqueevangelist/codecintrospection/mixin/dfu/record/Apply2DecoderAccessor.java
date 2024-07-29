package me.basiqueevangelist.codecintrospection.mixin.dfu.record;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.mojang.serialization.codecs.RecordCodecBuilder;

@Mixin(targets = "com.mojang.serialization.codecs.RecordCodecBuilder$Instance$3", remap = false)
public interface Apply2DecoderAccessor {
    @Accessor
    RecordCodecBuilder<?, ?> getVal$function();

    @Accessor
    RecordCodecBuilder<?, ?> getVal$fa();

    @Accessor
    RecordCodecBuilder<?, ?> getVal$fb();
}