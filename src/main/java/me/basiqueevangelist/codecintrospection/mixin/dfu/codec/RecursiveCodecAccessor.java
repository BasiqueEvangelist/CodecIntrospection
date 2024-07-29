package me.basiqueevangelist.codecintrospection.mixin.dfu.codec;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(value = Codec.RecursiveCodec.class, remap = false)
public interface RecursiveCodecAccessor {
    @Accessor
    Supplier<Codec<?>> getWrapped();
}
