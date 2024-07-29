package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import com.mojang.serialization.MapCodec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/MapCodec$4", remap = false)
public interface MapResultMapCodecAccessor {
    @Accessor
    MapCodec.ResultFunction<?> getVal$function();

    @Accessor
    MapCodec<?> getThis$0();
}
