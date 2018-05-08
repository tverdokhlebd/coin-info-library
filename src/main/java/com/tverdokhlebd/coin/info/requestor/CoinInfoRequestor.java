package com.tverdokhlebd.coin.info.requestor;

import com.tverdokhlebd.coin.info.CoinInfo;
import com.tverdokhlebd.mining.commons.coin.CoinType;

/**
 * Interface for requesting coin info.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public interface CoinInfoRequestor {

    /**
     * Requests coin info.
     *
     * @param coinType type of coin
     * @return coin info
     * @throws CoinInfoRequestorException if there is any error in coin info requesting
     */
    CoinInfo requestCoinInfo(CoinType coinType) throws CoinInfoRequestorException;

}
