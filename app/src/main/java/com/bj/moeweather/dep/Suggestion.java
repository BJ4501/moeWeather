package com.bj.moeweather.dep;

import com.google.gson.annotations.SerializedName;

/**
 * JSON中的出行建议
 * Created by BJ on 2018/1/28.
 */
@Deprecated
public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort; //舒适度

    @SerializedName("cw")
    public CarWash carWash; //洗车

    public Sport sport; //运动建议

    public class Comfort{
        @SerializedName("txt")
        public String info;
    }

    public class CarWash{
        @SerializedName("txt")
        public String info;
    }

    public class Sport{
        @SerializedName("txt")
        public String info;
    }

}
