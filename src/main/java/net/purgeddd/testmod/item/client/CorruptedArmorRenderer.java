package net.purgeddd.testmod.item.client;

import net.purgeddd.testmod.item.custom.CorruptedArmorItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class CorruptedArmorRenderer extends GeoArmorRenderer<CorruptedArmorItem> {
    public CorruptedArmorRenderer() {
        super(new CorruptedArmorModel());
    }
}
