package me.basiqueevangelist.codecintrospection.mixin.dfu.record;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.mojang.serialization.codecs.RecordCodecBuilder;

@Mixin(targets = "com.mojang.serialization.codecs.RecordCodecBuilder$Instance$7", remap = false)
public interface Apply4DecoderAccessor {
    @Accessor
    RecordCodecBuilder<?, ?> getVal$function();

    @Accessor
    RecordCodecBuilder<?, ?> getVal$f1();

    @Accessor
    RecordCodecBuilder<?, ?> getVal$f2();

    @Accessor
    RecordCodecBuilder<?, ?> getVal$f3();

    @Accessor
    RecordCodecBuilder<?, ?> getVal$f4();
}