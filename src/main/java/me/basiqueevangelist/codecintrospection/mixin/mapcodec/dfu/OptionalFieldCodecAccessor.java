package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.OptionalFieldCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = OptionalFieldCodec.class, remap = false)
public interface OptionalFieldCodecAccessor {
    @Accessor
    String getName();

    @Accessor
    Codec<?> getElementCodec();
}
