package gliese832c.selectionGuiCrafting.integration.jei;

import gliese832c.selectionGuiCrafting.proxy.CommonProxy;
import gliese832c.selectionGuiCrafting.recipe.GuiSelectionRecipe;
import gliese832c.selectionGuiCrafting.recipe.RecipePairPair;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

import static gliese832c.SelectionGuiCrafting.MOD_ID;

@JEIPlugin
public final class JeiPlugin implements IModPlugin
{
    static final String SELECTION_UID = MOD_ID + ".crafting";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        registry.addRecipeCategories(
                new SelectionRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void register(IModRegistry registry)
    {
        registry.handleRecipes(RecipePairPair.class, SelectionRecipeCategory.Wrapper::new, SELECTION_UID);
        registry.addRecipes(CommonProxy.getAllRecipePairPairs(), SELECTION_UID);
    }
}