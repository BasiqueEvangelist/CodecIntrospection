package me.basiqueevangelist.codecintrospection.element;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.SimpleMapCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;

import java.util.List;
import java.util.stream.Collectors;

public record SimpleMapElement(SimpleMapCodec<?, ?> original, IntrospectionElement key, IntrospectionElement element, List<String> keys) implements MapCodecIntrospectionElement {
    static SimpleMapElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof SimpleMapCodec<?,?> simpleMap)) return null;

        return new SimpleMapElement(
            simpleMap,
            CodecIntrospection.introspect(simpleMap.keyCodec()),
            CodecIntrospection.introspect(simpleMap.elementCodec()),
            simpleMap.keys(JsonOps.INSTANCE).map(JsonElement::getAsString).toList()
        );
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "SimpleMapElement");
        out.write(indent + 1, "Keys = " + String.join(", ", keys));
        out.write(indent + 1, "Key");
        key.dump(out, indent + 2);
        out.write(indent + 1, "Element");
        element.dump(out, indent + 2);
    }
}
