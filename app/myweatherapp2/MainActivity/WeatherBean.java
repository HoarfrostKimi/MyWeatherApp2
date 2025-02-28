package com.example.myweatherapp2.MainActivity;

import java.util.List;

public class WeatherBean {

    /**
     * code : 200
     * updateTime : 2025-02-25T14:13+08:00
     * fxLink : https://www.qweather.com/en/weather/changsha-101250101.html
     * daily : [{"fxDate":"2025-02-25","sunrise":"06:58","sunset":"18:26","moonrise":"05:00","moonset":"15:32","moonPhase":"残月","moonPhaseIcon":"807","tempMax":"11","tempMin":"5","iconDay":"101","textDay":"多云","iconNight":"151","textNight":"多云","wind360Day":"270","windDirDay":"西风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"135","windDirNight":"东南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"86","precip":"0.0","pressure":"1020","vis":"24","cloud":"25","uvIndex":"2"},{"fxDate":"2025-02-26","sunrise":"06:57","sunset":"18:27","moonrise":"05:44","moonset":"16:38","moonPhase":"残月","moonPhaseIcon":"807","tempMax":"14","tempMin":"6","iconDay":"305","textDay":"小雨","iconNight":"104","textNight":"阴","wind360Day":"135","windDirDay":"东南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"180","windDirNight":"南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"93","precip":"1.0","pressure":"1011","vis":"24","cloud":"55","uvIndex":"2"},{"fxDate":"2025-02-27","sunrise":"06:56","sunset":"18:28","moonrise":"06:23","moonset":"17:43","moonPhase":"残月","moonPhaseIcon":"807","tempMax":"22","tempMin":"11","iconDay":"101","textDay":"多云","iconNight":"151","textNight":"多云","wind360Day":"180","windDirDay":"南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"180","windDirNight":"南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"91","precip":"0.0","pressure":"1007","vis":"25","cloud":"25","uvIndex":"6"},{"fxDate":"2025-02-28","sunrise":"06:55","sunset":"18:28","moonrise":"06:58","moonset":"18:48","moonPhase":"新月","moonPhaseIcon":"800","tempMax":"25","tempMin":"15","iconDay":"100","textDay":"晴","iconNight":"150","textNight":"晴","wind360Day":"180","windDirDay":"南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"180","windDirNight":"南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"89","precip":"0.0","pressure":"1004","vis":"25","cloud":"25","uvIndex":"6"},{"fxDate":"2025-03-01","sunrise":"06:54","sunset":"18:29","moonrise":"07:32","moonset":"19:53","moonPhase":"蛾眉月","moonPhaseIcon":"801","tempMax":"27","tempMin":"18","iconDay":"100","textDay":"晴","iconNight":"151","textNight":"多云","wind360Day":"180","windDirDay":"南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"180","windDirNight":"南风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"87","precip":"0.0","pressure":"1001","vis":"21","cloud":"25","uvIndex":"4"},{"fxDate":"2025-03-02","sunrise":"06:53","sunset":"18:30","moonrise":"08:05","moonset":"20:58","moonPhase":"蛾眉月","moonPhaseIcon":"801","tempMax":"26","tempMin":"7","iconDay":"306","textDay":"中雨","iconNight":"306","textNight":"中雨","wind360Day":"225","windDirDay":"西南风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"0","windDirNight":"北风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"88","precip":"2.2","pressure":"1006","vis":"19","cloud":"63","uvIndex":"4"},{"fxDate":"2025-03-03","sunrise":"06:52","sunset":"18:30","moonrise":"08:41","moonset":"22:05","moonPhase":"蛾眉月","moonPhaseIcon":"801","tempMax":"7","tempMin":"4","iconDay":"305","textDay":"小雨","iconNight":"151","textNight":"多云","wind360Day":"315","windDirDay":"西北风","windScaleDay":"1-3","windSpeedDay":"3","wind360Night":"0","windDirNight":"北风","windScaleNight":"1-3","windSpeedNight":"3","humidity":"89","precip":"5.1","pressure":"1014","vis":"24","cloud":"80","uvIndex":"1"}]
     * refer : {"sources":["QWeather"],"license":["CC BY-SA 4.0"]}
     */

    private String code;
    private String updateTime;
    private String fxLink;
    private ReferBean refer;
    private List<DailyBean> daily;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
    }

    public static class ReferBean {
        private List<String> sources;
        private List<String> license;

        public List<String> getSources() {
            return sources;
        }

        public void setSources(List<String> sources) {
            this.sources = sources;
        }

        public List<String> getLicense() {
            return license;
        }

        public void setLicense(List<String> license) {
            this.license = license;
        }
    }

    public static class DailyBean {
        /**
         * fxDate : 2025-02-25
         * sunrise : 06:58
         * sunset : 18:26
         * moonrise : 05:00
         * moonset : 15:32
         * moonPhase : 残月
         * moonPhaseIcon : 807
         * tempMax : 11
         * tempMin : 5
         * iconDay : 101
         * textDay : 多云
         * iconNight : 151
         * textNight : 多云
         * wind360Day : 270
         * windDirDay : 西风
         * windScaleDay : 1-3
         * windSpeedDay : 3
         * wind360Night : 135
         * windDirNight : 东南风
         * windScaleNight : 1-3
         * windSpeedNight : 3
         * humidity : 86
         * precip : 0.0
         * pressure : 1020
         * vis : 24
         * cloud : 25
         * uvIndex : 2
         */

        private String fxDate;
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moonPhase;
        private String moonPhaseIcon;
        private String tempMax;
        private String tempMin;
        private String iconDay;
        private String textDay;
        private String iconNight;
        private String textNight;
        private String wind360Day;
        private String windDirDay;
        private String windScaleDay;
        private String windSpeedDay;
        private String wind360Night;
        private String windDirNight;
        private String windScaleNight;
        private String windSpeedNight;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String uvIndex;

        public String getFxDate() {
            return fxDate;
        }

        public void setFxDate(String fxDate) {
            this.fxDate = fxDate;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }

        public String getMoonrise() {
            return moonrise;
        }

        public void setMoonrise(String moonrise) {
            this.moonrise = moonrise;
        }

        public String getMoonset() {
            return moonset;
        }

        public void setMoonset(String moonset) {
            this.moonset = moonset;
        }

        public String getMoonPhase() {
            return moonPhase;
        }

        public void setMoonPhase(String moonPhase) {
            this.moonPhase = moonPhase;
        }

        public String getMoonPhaseIcon() {
            return moonPhaseIcon;
        }

        public void setMoonPhaseIcon(String moonPhaseIcon) {
            this.moonPhaseIcon = moonPhaseIcon;
        }

        public String getTempMax() {
            return tempMax;
        }

        public void setTempMax(String tempMax) {
            this.tempMax = tempMax;
        }

        public String getTempMin() {
            return tempMin;
        }

        public void setTempMin(String tempMin) {
            this.tempMin = tempMin;
        }

        public String getIconDay() {
            return iconDay;
        }

        public void setIconDay(String iconDay) {
            this.iconDay = iconDay;
        }

        public String getTextDay() {
            return textDay;
        }

        public void setTextDay(String textDay) {
            this.textDay = textDay;
        }

        public String getIconNight() {
            return iconNight;
        }

        public void setIconNight(String iconNight) {
            this.iconNight = iconNight;
        }

        public String getTextNight() {
            return textNight;
        }

        public void setTextNight(String textNight) {
            this.textNight = textNight;
        }

        public String getWind360Day() {
            return wind360Day;
        }

        public void setWind360Day(String wind360Day) {
            this.wind360Day = wind360Day;
        }

        public String getWindDirDay() {
            return windDirDay;
        }

        public void setWindDirDay(String windDirDay) {
            this.windDirDay = windDirDay;
        }

        public String getWindScaleDay() {
            return windScaleDay;
        }

        public void setWindScaleDay(String windScaleDay) {
            this.windScaleDay = windScaleDay;
        }

        public String getWindSpeedDay() {
            return windSpeedDay;
        }

        public void setWindSpeedDay(String windSpeedDay) {
            this.windSpeedDay = windSpeedDay;
        }

        public String getWind360Night() {
            return wind360Night;
        }

        public void setWind360Night(String wind360Night) {
            this.wind360Night = wind360Night;
        }

        public String getWindDirNight() {
            return windDirNight;
        }

        public void setWindDirNight(String windDirNight) {
            this.windDirNight = windDirNight;
        }

        public String getWindScaleNight() {
            return windScaleNight;
        }

        public void setWindScaleNight(String windScaleNight) {
            this.windScaleNight = windScaleNight;
        }

        public String getWindSpeedNight() {
            return windSpeedNight;
        }

        public void setWindSpeedNight(String windSpeedNight) {
            this.windSpeedNight = windSpeedNight;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(String uvIndex) {
            this.uvIndex = uvIndex;
        }
    }
}
