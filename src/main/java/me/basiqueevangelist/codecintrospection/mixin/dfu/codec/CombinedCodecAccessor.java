package me.basiqueevangelist.codecintrospection.mixin.dfu.codec;

import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/Codec$2", remap = false)
public interface CombinedCodecAccessor {
    @Accessor
    Encoder<?> getVal$encoder();

    @Accessor
    Decoder<?> getVal$decoder();
}
