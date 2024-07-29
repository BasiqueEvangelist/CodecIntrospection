package me.basiqueevangelist.codecintrospection.element.codec.minecraft;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.pond.StringIdentifiableBasicCodecAccess;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.minecraft.util.StringIdentifiable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public record StringIdentifiableEnumElement(StringIdentifiable.EnumCodec<?> original, Class<?> enumClass) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable StringIdentifiableEnumElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof StringIdentifiable.EnumCodec<?> original)) return null;

        var access = (StringIdentifiableBasicCodecAccess) original;

        return new StringIdentifiableEnumElement(original, ((Enum<?>) access.codecintrospection$values()[0]).getDeclaringClass());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "StringIdentifiableEnumElement (" + enumClass.getName() + ")");
    }
}
