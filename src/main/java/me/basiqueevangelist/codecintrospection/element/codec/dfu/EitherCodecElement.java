package me.basiqueevangelist.codecintrospection.element.codec.dfu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.EitherCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

public record EitherCodecElement(EitherCodec<?, ?> original, IntrospectionElement first, IntrospectionElement second) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static @Nullable EitherCodecElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof EitherCodec<?, ?> original)) return null;

        return new EitherCodecElement(
            original,
            CodecIntrospection.introspect(original.first()),
            CodecIntrospection.introspect(original.second())
        );
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "EitherCodecElement");
        first.dump(out, indent + 1);
        second.dump(out, indent + 1);
    }
}
