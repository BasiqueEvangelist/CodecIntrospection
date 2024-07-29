package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.xmap;

import com.mojang.serialization.MapEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Function;

@Mixin(targets = "com/mojang/serialization/MapEncoder$1", remap = false)
public interface ComappedMapEncoderAccessor {
    @Accessor
    Function<?, ?> getVal$function();

    @Accessor
    MapEncoder<?> getThis$0();
}
