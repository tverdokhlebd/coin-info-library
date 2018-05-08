package com.tverdokhlebd.coin.info.requestor;

import com.tverdokhlebd.coin.info.CoinInfoType;
import com.tverdokhlebd.coin.info.whattomine.WhatToMineRequestor;
import com.tverdokhlebd.mining.http.HttpClientFactory;

import okhttp3.OkHttpClient;

/**
 * Factory for creating coin info requestor.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public class CoinInfoRequestorFactory {

    /** Endpoints update. */
    private static final int ENDPOINTS_UPDATE = 6;

    /**
     * Creates coin info requestor.
     *
     * @param coinInfoType coin info type
     * @return coin info requestor
     */
    public static CoinInfoRequestor create(CoinInfoType coinInfoType) {
        OkHttpClient httpClient = HttpClientFactory.create();
        return create(coinInfoType, httpClient);
    }

    /**
     * Creates coin info requestor.
     *
     * @param coinInfoType coin info type
     * @param httpClient HTTP client
     * @return coin info requestor
     */
    public static CoinInfoRequestor create(CoinInfoType coinInfoType, OkHttpClient httpClient) {
        return create(coinInfoType, httpClient, ENDPOINTS_UPDATE);
    }

    /**
     * Creates coin info requestor.
     *
     * @param coinInfoType coin info type
     * @param httpClient HTTP client
     * @param endpointsUpdate endpoints update
     * @return coin info requestor
     */
    public static CoinInfoRequestor create(CoinInfoType coinInfoType, OkHttpClient httpClient, int endpointsUpdate) {
        switch (coinInfoType) {
        case WHAT_TO_MINE: {
            return new WhatToMineRequestor(httpClient, endpointsUpdate);
        }
        default:
            throw new IllegalArgumentException(coinInfoType.name());
        }
    }

}
