package sample.filters;

import com.jhlabs.image.SmearFilter;

/**
 * Created by Alexandra on 05.12.2018.
 */
public class Lang {
    private String name;
    private int index;


    public Lang(int index, String name) {
        this.name = name;
        this.index = index;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /**
     *CurvesFilter
     *ExposureFilter
     *GrayFilter
     *GrayscaleFilter
     *HSBAdjustFilter
     *     float hFactor - Amount to shift hue
           float sFactor - Amount to shift saturation
           float bFactor - Amount to shift brightness

     *
     * RGBAdjustFilter
     * BlurFilter
     * SmearFilter
     *
     * @return
     */


    @Override
    public String toString() {
        return name;
    }
}
