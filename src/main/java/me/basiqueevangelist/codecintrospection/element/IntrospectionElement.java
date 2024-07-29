package me.basiqueevangelist.codecintrospection.element;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import org.jetbrains.annotations.ApiStatus;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public interface IntrospectionElement {
    void dump(FormattedDumper out, int indent);

    @ApiStatus.NonExtendable
    default String dumpToString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FormattedDumper dumper = new FormattedDumper(new PrintStream(baos));

        dump(dumper, 0);

        return baos.toString(StandardCharsets.UTF_8);
    }
}
