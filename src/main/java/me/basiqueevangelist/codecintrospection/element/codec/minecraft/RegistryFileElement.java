package me.basiqueevangelist.codecintrospection.element.codec.minecraft;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.minecraft.RegistryFileCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.minecraft.core.Registry;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public record RegistryFileElement(RegistryFileCodec<?> original, ResourceKey<? extends Registry<?>> registry) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable RegistryFileElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof RegistryFileCodec<?> original)) return null;

        return new RegistryFileElement(original, ((RegistryFileCodecAccessor)(Object) original).getRegistryKey());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "RegistryFileElement (" + registry.location() + ")");
    }
}
