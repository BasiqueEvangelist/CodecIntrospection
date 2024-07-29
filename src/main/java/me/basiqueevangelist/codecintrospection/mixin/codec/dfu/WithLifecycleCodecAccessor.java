package me.basiqueevangelist.codecintrospection.mixin.codec.dfu;

import com.mojang.serialization.Codec;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(targets = "com/mojang/serialization/Codec$1", remap = false)
public interface WithLifecycleCodecAccessor {
    @Accessor
    Codec<?> getThis$0();
}
