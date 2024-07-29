package me.basiqueevangelist.codecintrospection.mixin.dfu.mapcodec;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(targets = "com/mojang/serialization/Decoder$5", remap = false)
public interface UnitMapDecoderAccessor {
    @Accessor
    Supplier<?> getVal$instance();
}
