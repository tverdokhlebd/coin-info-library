package com.tverdokhlebd.coin.info.whattomine;

import static com.tverdokhlebd.coin.info.CoinInfoType.WHAT_TO_MINE;
import static com.tverdokhlebd.mining.commons.coin.CoinType.BTC;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ETC;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ETH;
import static com.tverdokhlebd.mining.commons.coin.CoinType.XMR;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ZEC;
import static com.tverdokhlebd.mining.commons.http.ErrorCode.API_ERROR;
import static com.tverdokhlebd.mining.commons.http.ErrorCode.PARSE_ERROR;

import java.math.BigDecimal;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tverdokhlebd.coin.info.CoinInfo;
import com.tverdokhlebd.coin.info.CoinInfo.Builder;
import com.tverdokhlebd.coin.info.CoinInfoType;
import com.tverdokhlebd.coin.info.requestor.CoinInfoBaseRequestor;
import com.tverdokhlebd.mining.commons.coin.CoinType;
import com.tverdokhlebd.mining.commons.http.RequestException;
import com.tverdokhlebd.mining.commons.utils.TimeUtils;

import okhttp3.OkHttpClient;

/**
 * WhatToMine coin info requestor.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public class WhatToMineRequestor extends CoinInfoBaseRequestor {

    /** Endpoints update. */
    private final int endpointsUpdate;
    /** Request name of BTC coin info. */
    private static final String BTC_REQUEST_NAME = "BTC_REQUEST_NAME";
    /** Request name of ETH coin info. */
    private static final String ETH_REQUEST_NAME = "ETH_REQUEST_NAME";
    /** Request name of ETC coin info. */
    private static final String ETC_REQUEST_NAME = "ETC_REQUEST_NAME";
    /** Request name of XMR coin info. */
    private static final String XMR_REQUEST_NAME = "XMR_REQUEST_NAME";
    /** Request name of ZEC coin info. */
    private static final String ZEC_REQUEST_NAME = "ZEC_REQUEST_NAME";
    /** Map of urls. */
    private static final Map<CoinType, List<SimpleEntry<String, String>>> URL_MAP = new HashMap<>();
    /** Fills map of urls. */
    static {
        List<SimpleEntry<String, String>> btcUrlList = new ArrayList<>();
        btcUrlList.add(new SimpleEntry<String, String>(BTC_REQUEST_NAME,
                                                       "https://whattomine.com/coins/1.json"));
        URL_MAP.put(BTC, btcUrlList);
        List<SimpleEntry<String, String>> ethUrlList = new ArrayList<>();
        ethUrlList.add(new SimpleEntry<String, String>(ETH_REQUEST_NAME,
                                                       "https://whattomine.com/coins/151.json"));
        URL_MAP.put(ETH, ethUrlList);
        List<SimpleEntry<String, String>> etcUrlList = new ArrayList<>();
        etcUrlList.add(new SimpleEntry<String, String>(ETC_REQUEST_NAME,
                                                       "https://whattomine.com/coins/162.json"));
        URL_MAP.put(ETC, etcUrlList);
        List<SimpleEntry<String, String>> xmrUrlList = new ArrayList<>();
        xmrUrlList.add(new SimpleEntry<String, String>(XMR_REQUEST_NAME,
                                                       "https://whattomine.com/coins/101.json"));
        URL_MAP.put(XMR, xmrUrlList);
        List<SimpleEntry<String, String>> zecUrlList = new ArrayList<>();
        zecUrlList.add(new SimpleEntry<String, String>(ZEC_REQUEST_NAME,
                                                       "https://whattomine.com/coins/166.json"));
        URL_MAP.put(ZEC, zecUrlList);
    }
    /** Map of cached coin info. */
    private static final Map<CoinType, SimpleEntry<CoinInfo, Date>> CACHED_COIN_INFO_MAP = new ConcurrentHashMap<>();

    /**
     * Creates instance.
     *
     * @param httpClient HTTP client
     * @param endpointsUpdate endpoints update
     */
    public WhatToMineRequestor(OkHttpClient httpClient, int endpointsUpdate) {
        super(httpClient);
        this.endpointsUpdate = endpointsUpdate;
    }

    @Override
    public Date getCachedNextUpdate(CoinType coinType) {
        SimpleEntry<CoinInfo, Date> cachedCoinInfo = CACHED_COIN_INFO_MAP.get(coinType);
        return cachedCoinInfo == null ? new Date(0) : cachedCoinInfo.getValue();
    }

    @Override
    public CoinInfo getCachedCoinInfo(CoinType coinType) {
        return CACHED_COIN_INFO_MAP.get(coinType).getKey();
    }

    @Override
    public void setCachedCoinInfo(CoinType coinType, CoinInfo coinInfo) {
        SimpleEntry<CoinInfo, Date> cachedCoinMarket = CACHED_COIN_INFO_MAP.get(coinType);
        CACHED_COIN_INFO_MAP.replace(coinType, new SimpleEntry<CoinInfo, Date>(coinInfo, cachedCoinMarket.getValue()));
    }

    @Override
    protected CoinInfoType geCoinInfoType() {
        return WHAT_TO_MINE;
    }

    @Override
    protected List<SimpleEntry<String, String>> getUrlList(CoinType coinType) {
        return URL_MAP.get(coinType);
    }

    @Override
    protected void checkApiError(String responseBody, String requestName) throws RequestException {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            JSONArray errors = jsonResponse.optJSONArray("errors");
            if (errors != null) {
                throw new RequestException(API_ERROR, errors.getString(0));
            }
        } catch (JSONException e) {
            throw new RequestException(PARSE_ERROR, e);
        }
    }

    @Override
    protected void parseResponse(String responseBody, String requestName, Builder result) throws RequestException {
        try {
            JSONObject jsonResponse = new JSONObject(responseBody);
            result.setBlockTime(BigDecimal.valueOf(jsonResponse.getDouble("block_time")))
                  .setBlockReward(BigDecimal.valueOf(jsonResponse.getDouble("block_reward")))
                  .setBlockCount(BigDecimal.valueOf(jsonResponse.getDouble("last_block")))
                  .setDifficulty(BigDecimal.valueOf(jsonResponse.getDouble("difficulty")))
                  .setNetworkHashrate(BigDecimal.valueOf(jsonResponse.getDouble("nethash")));
            Date lastUpdated = new Date(jsonResponse.getLong("timestamp") * 1000);
            Date nextUpdate = TimeUtils.addMinutes(lastUpdated, endpointsUpdate);
            CoinType coinType = null;
            switch (requestName) {
            case BTC_REQUEST_NAME: {
                coinType = BTC;
                break;
            }
            case ETH_REQUEST_NAME: {
                coinType = ETH;
                break;
            }
            case ETC_REQUEST_NAME: {
                coinType = ETC;
                break;
            }
            case XMR_REQUEST_NAME: {
                coinType = XMR;
                break;
            }
            case ZEC_REQUEST_NAME: {
                coinType = ZEC;
                break;
            }
            }
            CACHED_COIN_INFO_MAP.put(coinType, new SimpleEntry<CoinInfo, Date>(null, nextUpdate));
        } catch (JSONException e) {
            throw new RequestException(PARSE_ERROR, e);
        }
    }

}
