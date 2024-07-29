package me.basiqueevangelist.codecintrospection.mixin.dfu.codec;

import com.mojang.serialization.Decoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(targets = "com/mojang/serialization/Decoder$2", remap = false)
public interface MappedDecoderAccessor {
    @Accessor
    Function<?, ?> getVal$function();

    @Accessor
    Decoder<?> getThis$0();
}
