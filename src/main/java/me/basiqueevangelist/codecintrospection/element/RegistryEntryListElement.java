package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.mixin.minecraft.RegistryEntryListCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryElementCodec;
import net.minecraft.registry.entry.RegistryEntryListCodec;
import org.jetbrains.annotations.Nullable;

public record RegistryEntryListElement(RegistryEntryListCodec<?> original, RegistryKey<? extends Registry<?>> registry) implements CodecIntrospectionElement {
    static @Nullable RegistryEntryListElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof RegistryEntryListCodec<?> original)) return null;

        return new RegistryEntryListElement(original, ((RegistryEntryListCodecAccessor) original).getRegistry());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "RegistryEntryListElement (" + registry.getValue() + ")");
    }
}
