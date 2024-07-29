package me.basiqueevangelist.codecintrospection.testmod;

import com.mojang.serialization.Codec;
import me.basiqueevangelist.codecintrospection.CodecIntrospection;
import me.basiqueevangelist.codecintrospection.util.FormattedDumper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.Bootstrap;
import net.minecraft.SharedConstants;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import org.slf4j.LoggerFactory;

public class CodecIntrospectionTestmod implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        SharedConstants.createGameVersion();
        Bootstrap.initialize();

        demo("Codec.STRING", Codec.STRING);
        demo("Identifier.CODEC", Identifier.CODEC);
        demo("ItemStack.CODEC", ItemStack.CODEC);
        demo("Biome.CODEC", Biome.CODEC);
        demo("BiomeEffects.CODEC", BiomeEffects.CODEC);

        System.exit(0);
    }

    private void demo(String name, Codec<?> codec) {
        LoggerFactory.getLogger("CodecIntrospectionTestmod").info("{} => \n{}", name, CodecIntrospection.introspect(codec).dumpToString());
    }
}
