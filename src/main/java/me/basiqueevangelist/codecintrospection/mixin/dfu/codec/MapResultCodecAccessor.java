package me.basiqueevangelist.codecintrospection.mixin.dfu.codec;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/Codec$4", remap = false)
public interface MapResultCodecAccessor {
    @Accessor
    Codec.ResultFunction<?> getVal$function();

    @Accessor
    Codec<?> getThis$0();
}
