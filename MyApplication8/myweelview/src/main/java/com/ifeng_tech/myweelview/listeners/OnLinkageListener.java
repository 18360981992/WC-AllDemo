package com.ifeng_tech.myweelview.listeners;


import com.ifeng_tech.myweelview.entity.City;
import com.ifeng_tech.myweelview.entity.County;
import com.ifeng_tech.myweelview.entity.Province;

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
