package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/MapCodec$1", remap = false)
public interface AssumeMapUnsafeMapCodecAccessor {
    @Accessor
    Codec<?> getVal$codec();
}
