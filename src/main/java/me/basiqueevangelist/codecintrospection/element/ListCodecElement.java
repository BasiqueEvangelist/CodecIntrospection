package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.ListCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.Nullable;

public record ListCodecElement(ListCodec<?> original, IntrospectionElement element) implements CodecIntrospectionElement {
    static @Nullable ListCodecElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof ListCodec<?> original)) return null;

        return new ListCodecElement(original, CodecIntrospection.introspect(original.elementCodec()));
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "ListCodecElement");
        element.dump(out, indent + 1);
    }
}
