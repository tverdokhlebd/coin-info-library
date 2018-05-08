package com.tverdokhlebd.coin.info;

import static com.tverdokhlebd.mining.commons.coin.CoinType.BTC;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ETC;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ETH;
import static com.tverdokhlebd.mining.commons.coin.CoinType.XMR;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ZEC;

import java.util.Arrays;
import java.util.List;

import com.tverdokhlebd.mining.commons.coin.CoinType;

/**
 * Enumerations of coin info types.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public enum CoinInfoType {

    WHAT_TO_MINE(Arrays.asList(BTC, ETH, ETC, XMR, ZEC));

    /** Supported list of coin types. */
    private final List<CoinType> coinTypeList;

    /**
     * Creates instance.
     *
     * @param coinTypeList supported list of coin types
     */
    private CoinInfoType(List<CoinType> coinTypeList) {
        this.coinTypeList = coinTypeList;
    }

    /**
     * Gets coin type list.
     *
     * @return coin type list
     */
    public List<CoinType> getCoinTypeList() {
        return coinTypeList;
    }

}
