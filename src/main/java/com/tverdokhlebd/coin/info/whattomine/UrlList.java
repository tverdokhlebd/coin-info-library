package com.tverdokhlebd.coin.info.whattomine;

import static com.tverdokhlebd.mining.commons.coin.CoinType.BTC;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ETC;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ETH;
import static com.tverdokhlebd.mining.commons.coin.CoinType.XMR;
import static com.tverdokhlebd.mining.commons.coin.CoinType.ZEC;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tverdokhlebd.mining.commons.coin.CoinType;

/**
 * List of urls for requestor.
 * 
 * @author Dmitry Tverdokhleb
 *
 */
class UrlList {

    /** Map of urls. */
    static final Map<CoinType, List<SimpleEntry<String, String>>> URL_MAP = new HashMap<>();
    /** Request name of BTC coin info. */
    static final String BTC_REQUEST_NAME = "BTC_REQUEST_NAME";
    /** Request name of ETH coin info. */
    static final String ETH_REQUEST_NAME = "ETH_REQUEST_NAME";
    /** Request name of ETC coin info. */
    static final String ETC_REQUEST_NAME = "ETC_REQUEST_NAME";
    /** Request name of XMR coin info. */
    static final String XMR_REQUEST_NAME = "XMR_REQUEST_NAME";
    /** Request name of ZEC coin info. */
    static final String ZEC_REQUEST_NAME = "ZEC_REQUEST_NAME";
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

}
