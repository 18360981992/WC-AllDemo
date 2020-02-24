package com.ifeng_tech.spotmall.weelview.weelview.listeners;


import com.ifeng_tech.spotmall.weelview.weelview.entity.City;
import com.ifeng_tech.spotmall.weelview.weelview.entity.County;
import com.ifeng_tech.spotmall.weelview.weelview.entity.Province;

/**
 * @author matt
 * blog: addapp.cn
 */

public interface OnLinkageListener {
    /**
     * 选择地址
     *
     * @param province the province
     * @param city    the city
     * @param county   the county ，if {@code hideCounty} is true，this is null
     */
    void onAddressPicked(Province province, City city, County county);
}
