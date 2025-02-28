package com.example.myweatherapp2.SearchActivity;

import java.util.List;

public class CityBean {

    /**
     * code : 200
     * location : [{"name":"长沙","id":"101250101","lat":"28.22830","lon":"112.93888","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"11","fxLink":"https://www.qweather.com/weather/changsha-101250101.html"},{"name":"长沙县","id":"101250106","lat":"28.23789","lon":"113.08010","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"25","fxLink":"https://www.qweather.com/weather/changsha-county-101250106.html"},{"name":"宁乡","id":"101250102","lat":"28.25393","lon":"112.55319","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"https://www.qweather.com/weather/ningxiang-101250102.html"},{"name":"浏阳","id":"101250103","lat":"28.14111","lon":"113.63330","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"23","fxLink":"https://www.qweather.com/weather/liuyang-101250103.html"},{"name":"望城","id":"101250105","lat":"28.34746","lon":"112.81955","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/wangcheng-101250105.html"},{"name":"芙蓉","id":"101250107","lat":"28.19311","lon":"112.98809","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/furong-101250107.html"},{"name":"天心","id":"101250108","lat":"28.11454","lon":"112.98978","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/tianxin-101250108.html"},{"name":"岳麓","id":"101250109","lat":"28.21304","lon":"112.91159","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/yuelu-101250109.html"},{"name":"开福","id":"101250110","lat":"28.25729","lon":"112.98528","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/kaifu-101250110.html"},{"name":"雨花","id":"101250111","lat":"28.10994","lon":"113.01634","adm2":"长沙","adm1":"湖南省","country":"中国","tz":"Asia/Shanghai","utcOffset":"+08:00","isDst":"0","type":"city","rank":"35","fxLink":"https://www.qweather.com/weather/yuhua-101250111.html"}]
     * refer : {"sources":["QWeather"],"license":["QWeather Developers License"]}
     */

    private String code;
    private ReferBean refer;
    private List<LocationBean> location;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ReferBean getRefer() {
        return refer;
    }

    public void setRefer(ReferBean refer) {
        this.refer = refer;
    }

    public List<LocationBean> getLocation() {
        return location;
    }

    public void setLocation(List<LocationBean> location) {
        this.location = location;
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

    public static class LocationBean {
        /**
         * name : 长沙
         * id : 101250101
         * lat : 28.22830
         * lon : 112.93888
         * adm2 : 长沙
         * adm1 : 湖南省
         * country : 中国
         * tz : Asia/Shanghai
         * utcOffset : +08:00
         * isDst : 0
         * type : city
         * rank : 11
         * fxLink : https://www.qweather.com/weather/changsha-101250101.html
         */

        private String name;
        private String id;
        private String lat;
        private String lon;
        private String adm2;
        private String adm1;
        private String country;
        private String tz;
        private String utcOffset;
        private String isDst;
        private String type;
        private String rank;
        private String fxLink;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getAdm2() {
            return adm2;
        }

        public void setAdm2(String adm2) {
            this.adm2 = adm2;
        }

        public String getAdm1() {
            return adm1;
        }

        public void setAdm1(String adm1) {
            this.adm1 = adm1;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
        }

        public String getUtcOffset() {
            return utcOffset;
        }

        public void setUtcOffset(String utcOffset) {
            this.utcOffset = utcOffset;
        }

        public String getIsDst() {
            return isDst;
        }

        public void setIsDst(String isDst) {
            this.isDst = isDst;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getFxLink() {
            return fxLink;
        }

        public void setFxLink(String fxLink) {
            this.fxLink = fxLink;
        }
    }
}
