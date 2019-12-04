package es.iesnervion.yeray.galeriaimagenes.ImagesGallery;

import android.graphics.Bitmap;

public class ImageRecycle {
    private Bitmap image;
    private boolean imageLoaded;

    public ImageRecycle(Bitmap image){
        this.image = image;
        imageLoaded = false;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isImageLoaded() {
        return imageLoaded;
    }

    public void setImageLoaded(boolean imageLoaded) {
        this.imageLoaded = imageLoaded;
    }
}
