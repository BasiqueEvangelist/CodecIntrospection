package me.basiqueevangelist.codecintrospection.mixin.codec.minecraft;

import net.minecraft.core.Registry;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HolderSetCodec.class)
public interface HolderSetCodecAccessor {
    @Accessor
    ResourceKey<? extends Registry<?>> getRegistryKey();
}
