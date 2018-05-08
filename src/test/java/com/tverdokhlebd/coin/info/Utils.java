package com.tverdokhlebd.coin.info;

import static com.tverdokhlebd.mining.commons.http.ErrorCode.API_ERROR;
import static com.tverdokhlebd.mining.commons.http.ErrorCode.HTTP_ERROR;
import static com.tverdokhlebd.mining.commons.http.ErrorCode.PARSE_ERROR;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.json.JSONObject;

import com.tverdokhlebd.coin.info.requestor.CoinInfoRequestor;
import com.tverdokhlebd.coin.info.requestor.CoinInfoRequestorException;
import com.tverdokhlebd.coin.info.requestor.CoinInfoRequestorFactory;
import com.tverdokhlebd.mining.commons.coin.CoinType;
import com.tverdokhlebd.mining.commons.utils.HttpClientUtils;

import okhttp3.OkHttpClient;

/**
 * Utils for tests.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public class Utils {

    /**
     * Tests coin info.
     *
     * @param httpClient HTTP client
     * @param coinInfoType type of coin info
     * @param coinType type of coin
     * @param expectedBlockTime expected block time
     * @param expectedBlockReward expected block reward
     * @param expectedBlockCount expected block count
     * @param expectedDifficulty expected difficulty
     * @param expectedNetworkHashrate expected network hashrate
     * @throws CoinInfoRequestorException if there is any error in coin info requesting
     */
    public static void testCoinInfo(OkHttpClient httpClient, CoinInfoType coinInfoType, CoinType coinType, BigDecimal expectedBlockTime,
            BigDecimal expectedBlockReward, BigDecimal expectedBlockCount, BigDecimal expectedDifficulty,
            BigDecimal expectedNetworkHashrate)
            throws CoinInfoRequestorException {
        CoinInfoRequestor coinInfoRequestor = CoinInfoRequestorFactory.create(coinInfoType, httpClient, 0);
        CoinInfo coinInfo = coinInfoRequestor.requestCoinInfo(coinType);
        assertEquals(coinType, coinInfo.getCoinType());
        assertEquals(expectedBlockTime, coinInfo.getBlockTime());
        assertEquals(expectedBlockReward, coinInfo.getBlockReward());
        assertEquals(expectedBlockCount, coinInfo.getBlockCount());
        assertEquals(expectedDifficulty, coinInfo.getDifficulty());
        assertEquals(expectedNetworkHashrate, coinInfo.getNetworkHashrate());
    }

    /**
     * Tests API error.
     *
     * @param httpClient HTTP client
     * @param coinInfoType type of coin info
     * @param coinType type of coin
     * @param expectedErrorMessage expected error message
     * @throws CoinInfoRequestorException if there is any error in coin info requesting
     */
    public static void testApiError(OkHttpClient httpClient, CoinInfoType coinInfoType, CoinType coinType, String expectedErrorMessage)
            throws CoinInfoRequestorException {
        CoinInfoRequestor coinInfoRequestor = CoinInfoRequestorFactory.create(coinInfoType, httpClient, 0);
        try {
            coinInfoRequestor.requestCoinInfo(coinType);
        } catch (CoinInfoRequestorException e) {
            assertEquals(API_ERROR, e.getErrorCode());
            assertEquals(expectedErrorMessage, e.getMessage());
            throw e;
        }
    }

    /**
     * Tests internal server error.
     *
     * @param coinInfoType type of coin info
     * @param coinType type of coin
     * @throws CoinInfoRequestorException if there is any error in coin info requesting
     */
    public static void testInternalServerError(CoinInfoType coinInfoType, CoinType coinType) throws CoinInfoRequestorException {
        OkHttpClient httpClient = HttpClientUtils.createHttpClient(new JSONObject().toString(), 500);
        CoinInfoRequestor coinInfoRequestor = CoinInfoRequestorFactory.create(coinInfoType, httpClient, 0);
        try {
            coinInfoRequestor.requestCoinInfo(coinType);
        } catch (CoinInfoRequestorException e) {
            assertEquals(HTTP_ERROR, e.getErrorCode());
            throw e;
        }
    }

    /**
     * Tests empty response.
     *
     * @param coinInfoType type of coin info
     * @param coinType type of coin
     * @throws CoinInfoRequestorException if there is any error in coin info requesting
     */
    public static void testEmptyResponse(CoinInfoType coinInfoType, CoinType coinType) throws CoinInfoRequestorException {
        OkHttpClient httpClient = HttpClientUtils.createHttpClient(new JSONObject().toString(), 200);
        CoinInfoRequestor coinInfoRequestor = CoinInfoRequestorFactory.create(coinInfoType, httpClient, 0);
        try {
            coinInfoRequestor.requestCoinInfo(coinType);
        } catch (CoinInfoRequestorException e) {
            assertEquals(PARSE_ERROR, e.getErrorCode());
            throw e;
        }
    }

    /**
     * Tests unsupported coin.
     *
     * @param coinInfoType type of coin info
     * @param coinType type of coin
     * @throws CoinInfoRequestorException if there is any error in coin info requesting
     */
    public static void testUnsupportedCoin(CoinInfoType coinInfoType, CoinType coinType) throws CoinInfoRequestorException {
        OkHttpClient httpClient = HttpClientUtils.createHttpClient(new JSONObject().toString(), 200);
        CoinInfoRequestor coinInfoRequestor = CoinInfoRequestorFactory.create(coinInfoType, httpClient, 0);
        try {
            coinInfoRequestor.requestCoinInfo(coinType);
        } catch (IllegalArgumentException e) {
            assertEquals(coinType.name() + " is not supported", e.getMessage());
            throw e;
        }
    }

}
