package fr.plaisance.papes.data;

public class Item {

    private String label;
    private Object value;

    public static Item of(String label, Object value) {
        Item item = new Item();
        item.label = label;
        item.value = value;
        return item;
    }

    public String getLabel() {
        return label;
    }

    public Object getValue() {
        return value;
    }
}
