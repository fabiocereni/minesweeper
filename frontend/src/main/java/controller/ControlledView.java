package controller;


import Model.AbstractModel;
import view.DataView;

public interface ControlledView extends DataView {

    void initialize(EventHandler eventHandler, AbstractModel model);

}
