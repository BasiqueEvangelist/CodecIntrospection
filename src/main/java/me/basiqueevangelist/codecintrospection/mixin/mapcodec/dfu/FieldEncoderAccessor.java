package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import com.mojang.serialization.Encoder;
import com.mojang.serialization.codecs.FieldEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = FieldEncoder.class, remap = false)
public interface FieldEncoderAccessor {
    @Accessor
    Encoder<?> getElementCodec();

    @Accessor
    String getName();
}
