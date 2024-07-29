package me.basiqueevangelist.codecintrospection.mixin.codec.minecraft;

import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(RegistryFileCodec.class)
public interface RegistryFileCodecAccessor {
    @Accessor
    ResourceKey<? extends Registry<?>> getRegistryKey();
}
