package ch.supsi.minesweeper.controller;

import ch.supsi.minesweeper.Model.AbstractModel;
import ch.supsi.minesweeper.view.DataView;

public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model);

}
