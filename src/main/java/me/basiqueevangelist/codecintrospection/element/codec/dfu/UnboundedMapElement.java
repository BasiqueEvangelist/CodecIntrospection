package me.basiqueevangelist.codecintrospection.element.codec.dfu;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.UnboundedMapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.element.IntrospectionElement;
import me.basiqueevangelist.codecintrospection.element.codec.CodecIntrospectionElement;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;

public record UnboundedMapElement(UnboundedMapCodec<?, ?> original, IntrospectionElement key, IntrospectionElement element) implements CodecIntrospectionElement {
    @ApiStatus.Internal
    public static UnboundedMapElement fromCodec(Codec<?> codec) {
        if (!(codec instanceof UnboundedMapCodec<?,?> unboundedMap)) return null;

        return new UnboundedMapElement(
            unboundedMap,
            CodecIntrospection.introspect(unboundedMap.keyCodec()),
            CodecIntrospection.introspect(unboundedMap.elementCodec())
        );
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "UnboundedMapElement");
        out.write(indent + 1, "Key");
        key.dump(out, indent + 2);
        out.write(indent + 1, "Element");
        element.dump(out, indent + 2);
    }
}
