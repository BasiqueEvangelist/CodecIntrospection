package me.basiqueevangelist.codecintrospection.element.mapcodec.dfu;

import com.mojang.serialization.MapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.mapcodec.MapCodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.mixin.mapcodec.dfu.OptionalFieldCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;

public record OptionalFieldElement(MapCodec<?> original, String name, IntrospectionElement codec) implements MapCodecIntrospectionElement {
    @ApiStatus.Internal
    public static OptionalFieldElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof OptionalFieldCodecAccessor optionalField)) return null;

        return new OptionalFieldElement(codec, optionalField.getName(), CodecIntrospection.introspect(optionalField.getElementCodec()));
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "OptionalFieldElement [" + name + "]");
        codec.dump(out, indent + 1);
    }
}
