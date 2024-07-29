package me.basiqueevangelist.codecintrospection.element.codec.minecraft;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.minecraft.HolderSetCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.minecraft.core.Registry;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.resources.ResourceKey;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public record HolderSetElement(HolderSetCodec<?> original, ResourceKey<? extends Registry<?>> registry) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable HolderSetElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof HolderSetCodec<?> original)) return null;

        return new HolderSetElement(original, ((HolderSetCodecAccessor) original).getRegistryKey());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "HolderSetElement (" + registry.location() + ")");
    }
}
