package me.basiqueevangelist.codecintrospection.element.codec.minecraft;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.pond.StringRepresentableCodecAccess;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public record StringRepresentableEnumElement(StringRepresentable.EnumCodec<?> original, Class<?> enumClass) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable StringRepresentableEnumElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof StringRepresentable.EnumCodec<?> original)) return null;

        var access = (StringRepresentableCodecAccess) original;

        return new StringRepresentableEnumElement(original, ((Enum<?>) access.codecintrospection$values()[0]).getDeclaringClass());
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "StringRepresentableEnumElement (" + enumClass.getName() + ")");
    }
}
