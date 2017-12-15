package product;

import misc.ITrackable;

public interface IProduct extends ITrackable {

    String getID();

    String getName();

    String getCategory();

    String getBrand();

    String getDescription();

    String getImage();

}
