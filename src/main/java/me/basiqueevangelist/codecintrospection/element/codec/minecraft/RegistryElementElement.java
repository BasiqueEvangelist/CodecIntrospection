package me.basiqueevangelist.codecintrospection.element.codec.minecraft;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.codec.minecraft.RegistryElementCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryElementCodec;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public record RegistryElementElement(RegistryElementCodec<?> original, RegistryKey<? extends Registry<?>> registry) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable RegistryElementElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof RegistryElementCodec<?> original)) return null;

        return new RegistryElementElement(original, ((RegistryElementCodecAccessor)(Object) original).getRegistryRef());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "RegistryElementElement (" + registry.getValue() + ")");
    }
}
