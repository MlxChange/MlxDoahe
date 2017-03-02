package com.example.mlx.daohe.entiy;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 项目名：Daohe2
 * 包名：com.example.mlx.daohe.entiy
 * 创建者：MLX
 * 创建时间：2017/2/16 20:22
 * 用途：餐饮数据实体类
 */

public class Hot_Meishi {

    /**
     * resultcode : 200
     * reason : 查询成功
     * result : [{"name":"故乡一家人粗粮美食城","navigation":"长治餐厅>>城区>>其他中餐>>故乡一家人粗粮美食城","city":"长治","area":"城区","address":"府后西街338号","phone":"","latitude":"36.19665","longitude":"113.09452","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"驴肉香","navigation":"长治餐厅>>城区>>其他中餐>>驴肉香","city":"长治","area":"城区","address":"府后西街347号客运中心大巴进站口对面(近新世界KTV)","phone":"0355-6031313","latitude":"36.19779","longitude":"113.09467","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/pc/f460813f5d0362abb2de9469815a4845/35024270_m.jpg","tags":"其他中餐","all_remarks":"5","very_good_remarks":"2","good_remarks":"2","common_remarks":"0","bad_remarks":"0","very_bad_remarks":"1","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"山药大锅,泰米红烧肉,腊八蒜杏鲍菇,香驴如意卷等等","nearby_shops":""},{"name":"浙江小吃","navigation":"长治餐厅>>城区>>其他中餐>>浙江小吃","city":"长治","area":"城区","address":"五一路","phone":"","latitude":"36.18813","longitude":"113.09469","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"通顺茗茶","navigation":"长治餐厅>>城区>>茶馆>>通顺茗茶","city":"长治","area":"城区","address":"华丰北路与五一桥交汇北40米路西侧","phone":"","latitude":"36.18837","longitude":"113.09497","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"茶馆","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"哈尔滨饺子王","navigation":"长治餐厅>>城区>>快餐简餐>>哈尔滨饺子王","city":"长治","area":"城区","address":"紫金西街","phone":"","latitude":"36.20155","longitude":"113.09539","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"快餐简餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"客运酒店","navigation":"长治餐厅>>城区>>其他中餐>>客运酒店","city":"长治","area":"城区","address":"潞酒厂","phone":"","latitude":"36.19817","longitude":"113.09541","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"客运宾馆餐厅","navigation":"长治餐厅>>城区>>其他中餐>>客运宾馆餐厅","city":"长治","area":"城区","address":"太行南路","phone":"","latitude":"36.1984","longitude":"113.09547","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"桂花蒸饺馆","navigation":"长治餐厅>>城区>>其他中餐>>桂花蒸饺馆","city":"长治","area":"城区","address":"太行西街256号(近火炬中学)","phone":"","latitude":"36.21009","longitude":"113.09551","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/2011-07-22/8811347_m.jpg","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"私房小厨","navigation":"长治餐厅>>城区>>晋菜>>私房小厨","city":"长治","area":"城区","address":"史家庄村(智业机床 对面)","phone":"0355-6022492,13152752986","latitude":"36.21886","longitude":"113.09559","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"晋菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"清真饭店","navigation":"长治餐厅>>城区>>清真菜>>清真饭店","city":"长治","area":"城区","address":"华丰南路","phone":"","latitude":"36.18953","longitude":"113.09574","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"清真菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"金凝快餐","navigation":"长治餐厅>>城区>>快餐简餐>>金凝快餐","city":"长治","area":"城区","address":"太行南路与府后西街交汇北100米路东侧","phone":"","latitude":"36.19835","longitude":"113.09596","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"快餐简餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"晋浙一口鲜饭庄","navigation":"长治餐厅>>城区>>新疆菜>>晋浙一口鲜饭庄","city":"长治","area":"城区","address":"太行南路与紫金西街交汇北50米路西侧","phone":"15034526303","latitude":"36.20173","longitude":"113.09613","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"新疆菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"正宗药膳大盘鸡","navigation":"长治餐厅>>城区>>其他中餐>>正宗药膳大盘鸡","city":"长治","area":"城区","address":"太行南路","phone":"0355-6032950","latitude":"36.20163","longitude":"113.09617","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"梅子农家大锅台","navigation":"长治餐厅>>城区>>其他中餐>>梅子农家大锅台","city":"长治","area":"城区","address":"客运中心北紫坊中街100米路西胡同","phone":"0355-8587706","latitude":"36.20161","longitude":"113.09621","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/pc/c216386d02c26023ca44bb236feb6e6f/22055072_m.jpg","tags":"其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"永济手工牛肉饺子馆","navigation":"长治餐厅>>城区>>快餐简餐>>永济手工牛肉饺子馆","city":"长治","area":"城区","address":"华丰南路(长治华丰显微手足创伤骨科医院西南)","phone":"","latitude":"36.19246","longitude":"113.09644","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"快餐简餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"成都菜馆","navigation":"长治餐厅>>城区>>川菜>>成都菜馆","city":"长治","area":"城区","address":"太行南路与紫坊村校前街交汇处","phone":"13111256918","latitude":"36.20311","longitude":"113.09647","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"川菜,其他中餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"川香居","navigation":"长治餐厅>>城区>>川菜>>川香居","city":"长治","area":"城区","address":"紫坊村中街79号","phone":"13935553282","latitude":"36.20332","longitude":"113.09652","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"川菜","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"石柱土家","navigation":"长治餐厅>>城区>>湘菜>>石柱土家","city":"长治","area":"城区","address":"太行南路与紫坊村校前街交汇北70米路西侧","phone":"18734502899","latitude":"36.20347","longitude":"113.09654","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"湘菜,小吃面食","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"转角奶茶超市","navigation":"长治餐厅>>城区>>面包甜点>>转角奶茶超市","city":"长治","area":"城区","address":"华丰南路","phone":"","latitude":"36.19282","longitude":"113.09657","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"面包甜点","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""},{"name":"哈尔滨饺子王","navigation":"长治餐厅>>城区>>快餐简餐>>哈尔滨饺子王","city":"长治","area":"城区","address":"太行南路5号","phone":"","latitude":"36.20335","longitude":"113.09658","stars":"0.0","avg_price":"0","photos":"http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png","tags":"快餐简餐","all_remarks":"","very_good_remarks":"","good_remarks":"","common_remarks":"","bad_remarks":"","very_bad_remarks":"","product_rating":"","environment_rating":"","service_rating":"","recommended_products":"","recommended_dishes":"","nearby_shops":""}]
     * error_code : 0
     */

    @SerializedName("resultcode")
    private String resultcode;
    @SerializedName("reason")
    private String reason;
    @SerializedName("error_code")
    private int errorCode;
    @SerializedName("result")
    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : 故乡一家人粗粮美食城
         * navigation : 长治餐厅>>城区>>其他中餐>>故乡一家人粗粮美食城
         * city : 长治
         * area : 城区
         * address : 府后西街338号
         * phone :
         * latitude : 36.19665
         * longitude : 113.09452
         * stars : 0.0
         * avg_price : 0
         * photos : http://i3.dpfile.com/s/c/app/shop/i/shop/no-photo.2ee36d9fa3e22353370b4c54124d2e22.png
         * tags : 其他中餐
         * all_remarks :
         * very_good_remarks :
         * good_remarks :
         * common_remarks :
         * bad_remarks :
         * very_bad_remarks :
         * product_rating :
         * environment_rating :
         * service_rating :
         * recommended_products :
         * recommended_dishes :
         * nearby_shops :
         */

        @SerializedName("name")
        private String name;
        @SerializedName("navigation")
        private String navigation;
        @SerializedName("city")
        private String city;
        @SerializedName("area")
        private String area;
        @SerializedName("address")
        private String address;
        @SerializedName("phone")
        private String phone;
        @SerializedName("latitude")
        private String latitude;
        @SerializedName("longitude")
        private String longitude;
        @SerializedName("stars")
        private String stars;
        @SerializedName("avg_price")
        private String avgPrice;
        @SerializedName("photos")
        private String photos;
        @SerializedName("tags")
        private String tags;
        @SerializedName("all_remarks")
        private String allRemarks;
        @SerializedName("very_good_remarks")
        private String veryGoodRemarks;
        @SerializedName("good_remarks")
        private String goodRemarks;
        @SerializedName("common_remarks")
        private String commonRemarks;
        @SerializedName("bad_remarks")
        private String badRemarks;
        @SerializedName("very_bad_remarks")
        private String veryBadRemarks;
        @SerializedName("product_rating")
        private String productRating;
        @SerializedName("environment_rating")
        private String environmentRating;
        @SerializedName("service_rating")
        private String serviceRating;
        @SerializedName("recommended_products")
        private String recommendedProducts;
        @SerializedName("recommended_dishes")
        private String recommendedDishes;
        @SerializedName("nearby_shops")
        private String nearbyShops;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNavigation() {
            return navigation;
        }

        public void setNavigation(String navigation) {
            this.navigation = navigation;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(String avgPrice) {
            this.avgPrice = avgPrice;
        }

        public String getPhotos() {
            return photos;
        }

        public void setPhotos(String photos) {
            this.photos = photos;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getAllRemarks() {
            return allRemarks;
        }

        public void setAllRemarks(String allRemarks) {
            this.allRemarks = allRemarks;
        }

        public String getVeryGoodRemarks() {
            return veryGoodRemarks;
        }

        public void setVeryGoodRemarks(String veryGoodRemarks) {
            this.veryGoodRemarks = veryGoodRemarks;
        }

        public String getGoodRemarks() {
            return goodRemarks;
        }

        public void setGoodRemarks(String goodRemarks) {
            this.goodRemarks = goodRemarks;
        }

        public String getCommonRemarks() {
            return commonRemarks;
        }

        public void setCommonRemarks(String commonRemarks) {
            this.commonRemarks = commonRemarks;
        }

        public String getBadRemarks() {
            return badRemarks;
        }

        public void setBadRemarks(String badRemarks) {
            this.badRemarks = badRemarks;
        }

        public String getVeryBadRemarks() {
            return veryBadRemarks;
        }

        public void setVeryBadRemarks(String veryBadRemarks) {
            this.veryBadRemarks = veryBadRemarks;
        }

        public String getProductRating() {
            return productRating;
        }

        public void setProductRating(String productRating) {
            this.productRating = productRating;
        }

        public String getEnvironmentRating() {
            return environmentRating;
        }

        public void setEnvironmentRating(String environmentRating) {
            this.environmentRating = environmentRating;
        }

        public String getServiceRating() {
            return serviceRating;
        }

        public void setServiceRating(String serviceRating) {
            this.serviceRating = serviceRating;
        }

        public String getRecommendedProducts() {
            return recommendedProducts;
        }

        public void setRecommendedProducts(String recommendedProducts) {
            this.recommendedProducts = recommendedProducts;
        }

        public String getRecommendedDishes() {
            return recommendedDishes;
        }

        public void setRecommendedDishes(String recommendedDishes) {
            this.recommendedDishes = recommendedDishes;
        }

        public String getNearbyShops() {
            return nearbyShops;
        }

        public void setNearbyShops(String nearbyShops) {
            this.nearbyShops = nearbyShops;
        }
    }
}
