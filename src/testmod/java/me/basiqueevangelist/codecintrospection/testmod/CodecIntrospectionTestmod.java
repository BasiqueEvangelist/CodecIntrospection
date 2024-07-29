package me.basiqueevangelist.codecintrospection.testmod;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodecIntrospectionTestmod implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        SharedConstants.tryDetectVersion();
        Bootstrap.bootStrap();

        try (var os = Files.newOutputStream(Path.of("codec-introspection-testmod-dump.txt"))) {
            FormattedDumper dumper = new FormattedDumper(new PrintStream(os));

            demo(dumper, "Codec.STRING", Codec.STRING);
            demo(dumper, "ResourceLocation.CODEC", ResourceLocation.CODEC);
            demo(dumper, "ItemStack.CODEC", ItemStack.CODEC);
            demo(dumper, "Biome.DIRECT_CODEC", Biome.DIRECT_CODEC);

            for (var componentType : BuiltInRegistries.DATA_COMPONENT_TYPE) {
                var codec = componentType.codec();

                if (codec == null) continue;

                demo(dumper, BuiltInRegistries.DATA_COMPONENT_TYPE.getKey(componentType).toString(), codec);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.exit(0);
    }

    private void demo(FormattedDumper dumper, String name, Codec<?> codec) {
        dumper.write(0, name + " => ");
        CodecIntrospection.introspect(codec).dump(dumper, 1);
    }
}
