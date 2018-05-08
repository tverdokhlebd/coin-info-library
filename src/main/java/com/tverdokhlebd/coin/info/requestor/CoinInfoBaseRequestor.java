package com.tverdokhlebd.coin.info.requestor;

import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.List;

import com.tverdokhlebd.coin.info.CoinInfo;
import com.tverdokhlebd.coin.info.CoinInfoCaching;
import com.tverdokhlebd.coin.info.CoinInfoType;
import com.tverdokhlebd.mining.coin.CoinType;
import com.tverdokhlebd.mining.http.BaseRequestor;
import com.tverdokhlebd.mining.http.RequestException;

import okhttp3.OkHttpClient;

/**
 * Coin info base HTTP requestor.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public abstract class CoinInfoBaseRequestor extends BaseRequestor<CoinInfo.Builder> implements CoinInfoRequestor, CoinInfoCaching {

    /**
     * Creates instance.
     *
     * @param httpClient HTTP client
     */
    protected CoinInfoBaseRequestor(OkHttpClient httpClient) {
        super(httpClient);
    }

    @Override
    public CoinInfo requestCoinInfo(CoinType coinType) throws CoinInfoRequestorException {
        if (geCoinInfoType().getCoinTypeList().indexOf(coinType) == -1) {
            throw new IllegalArgumentException(coinType.name() + " is not supported");
        }
        try {
            if (new Date().after(getCachedNextUpdate(coinType))) {
                CoinInfo.Builder coinInfoBuilder = new CoinInfo.Builder();
                coinInfoBuilder.setCoinType(coinType);
                List<SimpleEntry<String, String>> urlList = getUrlList(coinType);
                for (int i = 0; i < urlList.size(); i++) {
                    SimpleEntry<String, String> urlEntry = urlList.get(i);
                    String requestName = urlEntry.getKey();
                    String preparedUrl = urlEntry.getValue();
                    super.request(preparedUrl, requestName, coinInfoBuilder);
                }
                setCachedCoinInfo(coinType, coinInfoBuilder.build());
            }
            return getCachedCoinInfo(coinType);
        } catch (RequestException e) {
            throw new CoinInfoRequestorException(e);
        }
    }

    /**
     * Gets coin info type.
     *
     * @return coin info type
     */
    protected abstract CoinInfoType geCoinInfoType();

    /**
     * Gets list of urls.
     *
     * @param coinType type of coin
     * @return list of urls
     */
    protected abstract List<SimpleEntry<String, String>> getUrlList(CoinType coinType);

}
