package es.iesnervion.yeray.galeriaimagenes.GalleryList;

import java.util.ArrayList;

public class ItemGalleryList {
    private String _galleryName;
    private int _maxNumbersOfImages;
    private int _idGalleryImages;

    public ItemGalleryList(){
        _galleryName = "DEFAULT";
        _maxNumbersOfImages = 20;
        _idGalleryImages = 0;
    }

    public ItemGalleryList(String galleryName, int maxNumbersOfImages, int idGalleryImages){
        _galleryName = galleryName;
        _maxNumbersOfImages = maxNumbersOfImages;
        _idGalleryImages = idGalleryImages;
    }

    public String get_galleryName() {
        return _galleryName;
    }

    public void set_galleryName(String _galleryName) {
        this._galleryName = _galleryName;
    }

    public int get_maxNumbersOfImages() {
        return _maxNumbersOfImages;
    }

    public void set_maxNumbersOfImages(int _maxNumbersOfImages) {
        this._maxNumbersOfImages = _maxNumbersOfImages;
    }

    public int get_idGalleryImages() {
        return _idGalleryImages;
    }

    public void set_images(int idGalleryImages) {
        this._idGalleryImages = idGalleryImages;
    }
}
