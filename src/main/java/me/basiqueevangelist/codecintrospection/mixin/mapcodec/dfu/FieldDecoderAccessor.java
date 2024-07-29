package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import com.mojang.serialization.Decoder;
import com.mojang.serialization.codecs.FieldDecoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = FieldDecoder.class, remap = false)
public interface FieldDecoderAccessor {
    @Accessor
    String getName();

    @Accessor
    Decoder<?> getElementCodec();
}
