package me.basiqueevangelist.codecintrospection.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.KeyDispatchCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(KeyDispatchCodec.class)
public interface KeyDispatchCodecAccessor {
    @Accessor
    String getTypeKey();

    @Accessor
    Codec<?> getKeyCodec();
}
