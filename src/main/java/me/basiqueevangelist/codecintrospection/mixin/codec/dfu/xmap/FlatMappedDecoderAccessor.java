package me.basiqueevangelist.codecintrospection.mixin.codec.dfu.xmap;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.Decoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(targets = "com/mojang/serialization/Decoder$1", remap = false)
public interface FlatMappedDecoderAccessor {
    @Accessor
    Function<?, DataResult<?>> getVal$function();

    @Accessor
    Decoder<?> getThis$0();
}
