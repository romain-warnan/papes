package fr.plaisance.papes.data;

public class Item {

    private String label;
    private Long value;

    public static Item of(String label, Long value) {
        Item item = new Item();
        item.label = label;
        item.value = value;
        return item;
    }

    public String getLabel() {
        return label;
    }

    public Long getValue() {
        return value;
    }
}
