package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.Model.AbstractModel;
import ch.supsi.minesweeper.view.DataView;

public interface ControlledView extends DataView {

    void initialize(EventHandler eventHandler, AbstractModel model);

}
