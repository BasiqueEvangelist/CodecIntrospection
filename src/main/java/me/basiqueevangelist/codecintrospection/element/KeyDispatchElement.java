package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.KeyDispatchCodec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.mixin.KeyDispatchCodecAccessor;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.Nullable;

public class KeyDispatchElement implements MapCodecIntrospectionElement {
    private final KeyDispatchCodec<?, ?> original;
    private final String typeKey;
    private final IntrospectionElement keyCodec;

    protected KeyDispatchElement(KeyDispatchCodec<?, ?> original, String typeKey, IntrospectionElement keyCodec) {
        this.original = original;
        this.typeKey = typeKey;
        this.keyCodec = keyCodec;
    }

    static @Nullable KeyDispatchElement fromMapCodec(MapCodec<?> codec) {
        if (!(codec instanceof KeyDispatchCodec<?,?> original)) return null;

        var accessor = (KeyDispatchCodecAccessor) original;

        return new KeyDispatchElement(original, accessor.getTypeKey(), CodecIntrospection.introspect(accessor.getKeyCodec()));
    }

    @Override
    public MapCodec<?> original() {
        return original;
    }

    public String typeKey() {
        return typeKey;
    }

    public IntrospectionElement keyCodec() {
        return keyCodec;
    }

    @Override
    public void dump(FormattedDumper out, int indent) {
        out.write(indent, "KeyDispatchElement");
        out.write(indent + 1, "Type Key = " + typeKey);
        out.write(indent + 1, "Key");
        keyCodec.dump(out, indent + 2);
    }
}
