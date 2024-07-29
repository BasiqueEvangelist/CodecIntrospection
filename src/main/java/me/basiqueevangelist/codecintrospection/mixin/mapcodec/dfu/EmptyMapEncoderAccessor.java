package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(targets = "com/mojang/serialization/Encoder$4", remap = false)
public interface EmptyMapEncoderAccessor {
    // Used in instanceof to detect empty map encoders.
}
