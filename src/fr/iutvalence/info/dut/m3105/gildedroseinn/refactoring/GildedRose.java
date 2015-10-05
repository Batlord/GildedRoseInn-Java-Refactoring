package fr.iutvalence.info.dut.m3105.gildedroseinn.refactoring;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateItemsQualityAndSellIn();
	}

	// update items Quality and Sellin
	public static void updateItemsQualityAndSellIn() {
		for (int i = 0; i < items.size(); i++) {
			
			if (isLegendaryItem(items.get(i).getName())) {
				continue;
			}

			updateQualityItem(i);

			updateSellInItem(i);

			
			if (items.get(i).getSellIn() < 0) {
				updateOutdatedItem(i);
			}

		}
	}

	private static boolean isLegendaryItem(String name) {
		return "Sulfuras, Hand of Ragnaros".equals(name);
	}

	private static void updateOutdatedItem(int i) {

		if (!"Aged Brie".equals(items.get(i).getName())) {
			if (!"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName())) {
				if (items.get(i).getQuality() > 0) {
						items.get(i).setQuality(items.get(i).getQuality() - 1);
				}
			} else {
				items.get(i).setQuality(items.get(i).getQuality() - items.get(i).getQuality());
			}
		} else {
			if (items.get(i).getQuality() < 50) {
				items.get(i).setQuality(items.get(i).getQuality() + 1);
			}
		}

	}

	private static void updateQualityItem(int i) {
		if ("Aged Brie".equals(items.get(i).getName())) {
			increaseQuality(i);
			return;
		}
		
		if ((!"Aged Brie".equals(items.get(i).getName()))
				&& !"Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName()) && items.get(i).getQuality() > 0) {
			updateItemsQualityLess(i);
		} else {
			if (items.get(i).getQuality() < 50) {
				increaseQuality(i);

				if ("Backstage passes to a TAFKAL80ETC concert".equals(items.get(i).getName())) {
					if (items.get(i).getSellIn() < 11) {
						if (items.get(i).getQuality() < 50) {
							items.get(i).setQuality(items.get(i).getQuality() + 1);
						}
					}

					if (items.get(i).getSellIn() < 6) {
						if (items.get(i).getQuality() < 50) {
							items.get(i).setQuality(items.get(i).getQuality() + 1);
						}
					}
				}
			}
		}
	}

	public static void updateItemsQualityLess(int i) {
		items.get(i).setQuality(items.get(i).getQuality() - 1);
	}

	public static void increaseQuality(int i) {
		items.get(i).setQuality(items.get(i).getQuality() + 1);
	}

	public static void updateSellInItem(int i) {
		items.get(i).setSellIn(items.get(i).getSellIn() - 1);
	}

}