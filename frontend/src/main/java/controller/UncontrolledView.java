package controller;

import Model.AbstractModel;
import view.DataView;

public interface UncontrolledView extends DataView {

    void initialize(AbstractModel model);

}
