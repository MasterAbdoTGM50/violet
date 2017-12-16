package jonamato.violet.product;

import jonamato.violet.util.ITrackable;

public interface IProduct extends ITrackable {

    String getID();

    String getName();

    String getCategory();

    String getBrand();

    String getDescription();

}
