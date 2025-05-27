package view;


import model.EventHandler;
import model.AbstractModel;

public interface ControlledView extends DataView {

    void initialize(EventHandler eventHandler, AbstractModel model);

}
