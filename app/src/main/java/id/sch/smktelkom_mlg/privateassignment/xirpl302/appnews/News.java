package id.sch.smktelkom_mlg.privateassignment.xirpl302.appnews;

import java.io.Serializable;

/**
 * Created by Affandhy on 14/05/2017.
 */

public class News implements Serializable {

    private String gambar;
    private String judul;
    private String deskrip;
    private String buat;

    public News(String gambar, String judul, String deskrip, String buat) {
        this.gambar = gambar;
        this.judul = judul;
        this.deskrip = deskrip;
        this.buat = buat;
    }

    public String getGambar() {
        return gambar;
    }

    public String getJudul() {
        return judul;
    }

    public String getDeskrip() {
        return deskrip;
    }

    public String getBuat() {
        return buat;
    }
}
