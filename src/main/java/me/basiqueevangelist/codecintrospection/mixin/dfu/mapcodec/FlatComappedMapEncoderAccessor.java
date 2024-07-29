package me.basiqueevangelist.codecintrospection.mixin.dfu.mapcodec;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(targets = "com/mojang/serialization/MapEncoder$2", remap = false)
public interface FlatComappedMapEncoderAccessor {
    @Accessor
    Function<?, DataResult<?>> getVal$function();

    @Accessor
    MapEncoder<?> getThis$0();
}
