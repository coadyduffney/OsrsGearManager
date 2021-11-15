package com.osrsgearmanager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.osrsgearmanager.cellfactories.ItemListCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

public class MainController {
    @FXML
    private AnchorPane equipmentPane;
    @FXML
    private ImageView capeSlotIcon;
    @FXML
    private ComboBox<Item> capeSlotSelection;
    @FXML
    private Label attackStab;
    @FXML
    private Label attackSlash;
    @FXML
    private Label attackCrush;
    @FXML
    private Label attackMagic;
    @FXML
    private Label attackRange;
    @FXML
    private Label defenceStab;
    @FXML
    private Label defenseSlash;
    @FXML
    private Label defenceCrush;
    @FXML
    private Label defenceMagic;
    @FXML
    private Label defenceRange;
    @FXML
    private Label meleeStrength;
    @FXML
    private Label rangedStrength;
    @FXML
    private Label magicDamage;

    private List<Item> capeSlotItems;

    public MainController() {
        capeSlotItems = new ArrayList<>();
    }

    @FXML
    public void initialize() throws IOException {
        getCapeSlotItems();

        capeSlotSelection.getItems().addAll(capeSlotItems);
        capeSlotSelection.setCellFactory(param -> new ItemListCell());
        capeSlotSelection.setButtonCell(new ItemListCell());

        capeSlotSelection
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((opts, oldItem, newItem) -> {
                    if (newItem != null) {
                        capeSlotIcon.setImage(newItem.getIcon());
                        capeSlotIcon.setFitHeight(55);
                        capeSlotIcon.setFitWidth(60);
                    } else {
                        capeSlotIcon.setImage(null);
                    }
        });
    }

    private void getCapeSlotItems() throws IOException {
        URL url = new URL("https://www.osrsbox.com/osrsbox-db/items-json-slot/items-cape.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        Type mapType = new TypeToken<Map<String, Map>>(){}.getType();
        Map<String, Map> itemsMap = new Gson().fromJson(content.toString(), mapType);

        for (Map.Entry<String, Map> entry : itemsMap.entrySet()) {
            Map value = entry.getValue();

            String itemId = entry.getKey();
            String itemName = (String) value.get("name");

            String itemIconEncoded = (String) value.get("icon");
            byte[] bytes = Base64.getDecoder().decode(itemIconEncoded);
            Image itemIcon = new Image(new ByteArrayInputStream(bytes));

            Map<String, Double> itemsStats = (Map<String, Double>) value.get("equipment");

            capeSlotItems.add(new Item(itemName, itemId, itemIcon, itemsStats));
        }
    }

}