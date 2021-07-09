package xyz.cbfh.eden;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.registry.*;
import xyz.cbfh.eden.Tools.Sword;

public class Main implements ModInitializer {

	//public static final ItemGroup EDEN_THINGS = FabricItemGroupBuilder.build(new Identifier("cbfh", "eden_things"), () -> new ItemStack((ItemConvertible) Main.EDEN_MATERIAL));
    public static final Edenium EDEN_MATERIAL = new Edenium(Edenium.EDENIUM);
    public static String MODID = "cbfh";
    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "eden_material"), EDEN_MATERIAL);
        Registry.register(Registry.ITEM, new Identifier(MODID, "eden_sword"), new Sword(new EdenMaterial()));
    }
}
