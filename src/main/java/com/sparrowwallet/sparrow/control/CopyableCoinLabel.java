package com.sparrowwallet.sparrow.control;

import com.sparrowwallet.drongo.BitcoinUnit;
import com.sparrowwallet.sparrow.UnitFormat;
import com.sparrowwallet.sparrow.io.Config;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyableCoinLabel extends CopyableLabel {
    private final LongProperty valueProperty = new SimpleLongProperty(-1);
    private final Tooltip tooltip;
    private final CoinContextMenu contextMenu;

    public CopyableCoinLabel() {
        this("Unknown");
    }

    public CopyableCoinLabel(String text) {
        super(text);
        valueProperty().addListener((observable, oldValue, newValue) -> setValueAsText((Long)newValue, Config.get().getUnitFormat(), Config.get().getBitcoinUnit()));
        tooltip = new Tooltip();
        contextMenu = new CoinContextMenu();
    }

    public final LongProperty valueProperty() {
        return valueProperty;
    }

    public final long getValue() {
        return valueProperty.get();
    }

    public final void setValue(long value) {
        this.valueProperty.set(value);
    }

    public void refresh() {
        refresh(Config.get().getUnitFormat(), Config.get().getBitcoinUnit());
    }

    public void refresh(UnitFormat unitFormat, BitcoinUnit bitcoinUnit) {
        setValueAsText(getValue(), unitFormat, bitcoinUnit);
    }

    private void setValueAsText(Long value, UnitFormat unitFormat, BitcoinUnit bitcoinUnit) {
        setTooltip(tooltip);
        setContextMenu(contextMenu);

        if(unitFormat == null) {
            unitFormat = UnitFormat.DOT;
        }

        String satsValue = unitFormat.formatSatsValue(value) + " gros";
        String btcValue = unitFormat.formatBtcValue(value) + " GRS";

        BitcoinUnit unit = bitcoinUnit;
        if(unit == null || unit.equals(BitcoinUnit.AUTO)) {
            unit = (value >= BitcoinUnit.getAutoThreshold() ? BitcoinUnit.BTC : BitcoinUnit.SATOSHIS);
        }

        if(unit.equals(BitcoinUnit.BTC)) {
            tooltip.setText(satsValue);
            setText(btcValue);
        } else {
            tooltip.setText(btcValue);
            setText(satsValue);
        }
    }

    private class CoinContextMenu extends ContextMenu {
        public CoinContextMenu() {
            MenuItem copySatsValue = new MenuItem("Copy Value in gros");
            copySatsValue.setOnAction(AE -> {
                hide();
                ClipboardContent content = new ClipboardContent();
                content.putString(Long.toString(getValue()));
                Clipboard.getSystemClipboard().setContent(content);
            });

            MenuItem copyBtcValue = new MenuItem("Copy Value in GRS");
            copyBtcValue.setOnAction(AE -> {
                hide();
                ClipboardContent content = new ClipboardContent();
                UnitFormat format = Config.get().getUnitFormat() == null ? UnitFormat.DOT : Config.get().getUnitFormat();
                content.putString(format.formatBtcValue(getValue()));
                Clipboard.getSystemClipboard().setContent(content);
            });

            getItems().addAll(copySatsValue, copyBtcValue);
        }
    }
}
