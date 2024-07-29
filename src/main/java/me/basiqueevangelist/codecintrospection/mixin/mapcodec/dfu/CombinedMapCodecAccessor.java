package me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu;

import com.mojang.serialization.MapDecoder;
import com.mojang.serialization.MapEncoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/MapCodec$2", remap = false)
public interface CombinedMapCodecAccessor {
    @Accessor
    MapDecoder<?> getVal$decoder();

    @Accessor
    MapEncoder<?> getVal$encoder();
}
