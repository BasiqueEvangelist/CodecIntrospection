package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapDecoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(targets = "com/mojang/serialization/MapDecoder$3", remap = false)
public interface FlatMappedMapDecoderAccessor {
    @Accessor
    Function<?, DataResult<?>> getVal$function();

    @Accessor
    MapDecoder<?> getThis$0();
}
