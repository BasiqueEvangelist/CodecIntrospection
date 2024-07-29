package me.basiqueevangelist.codecintrospection.mixin.dfu.codec;

import com.mojang.serialization.Decoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/Decoder$3", remap = false)
public interface PromotePartialDecoderAccessor {
    @Accessor
    Decoder<?> getThis$0();
}
