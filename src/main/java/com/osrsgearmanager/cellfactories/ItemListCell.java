package com.osrsgearmanager.cellfactories;

import com.osrsgearmanager.Item;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ItemListCell extends ListCell<Item> {

    @Override
    protected void updateItem(Item item, boolean empty) {
        super.updateItem(item, empty);

        if (null != item && !empty) {
            setText(item.getName());
            setGraphic(new ImageView(item.getIcon()));
        } else {
            setText(null);
            setGraphic(null);
        }
    }
}
