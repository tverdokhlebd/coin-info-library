package com.tverdokhlebd.coin.info;

import java.util.Date;

import com.tverdokhlebd.mining.commons.coin.CoinType;

/**
 * Interface for coin info caching.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public interface CoinInfoCaching {

    /**
     * Gets cached next update.
     *
     * @param coinType type of coin
     * @return cached next update
     */
    Date getCachedNextUpdate(CoinType coinType);

    /**
     * Gets cached coin info.
     *
     * @param coinType type of coin
     * @return cached coin info
     */
    CoinInfo getCachedCoinInfo(CoinType coinType);

    /**
     * Sets cached coin info.
     * 
     * @param coinType type of coin
     * @param coinInfo coin info
     */
    void setCachedCoinInfo(CoinType coinType, CoinInfo coinInfo);

}
