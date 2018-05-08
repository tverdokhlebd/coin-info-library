package com.tverdokhlebd.coin.info.whattomine;

import static com.tverdokhlebd.coin.info.CoinInfoType.WHAT_TO_MINE;
import static com.tverdokhlebd.mining.commons.coin.CoinType.BCH;
import static com.tverdokhlebd.mining.commons.coin.CoinType.BTC;

import java.math.BigDecimal;

import org.json.JSONObject;
import org.junit.Test;

import com.tverdokhlebd.coin.info.Utils;
import com.tverdokhlebd.coin.info.requestor.CoinInfoRequestorException;
import com.tverdokhlebd.mining.commons.utils.HttpClientUtils;

import okhttp3.OkHttpClient;

/**
 * Tests of WhatToMine coin info requestor.
 *
 * @author Dmitry Tverdokhleb
 *
 */
public class WhatToMineRequestorTest {

    @Test
    public void testCoinInfo() throws CoinInfoRequestorException {
        JSONObject response = new JSONObject("{  \n" +
                "  \"id\":151,\n" +
                "  \"name\":\"Ethereum\",\n" +
                "  \"tag\":\"ETH\",\n" +
                "  \"algorithm\":\"Ethash\",\n" +
                "  \"block_time\":\"14.9005\",\n" +
                "  \"block_reward\":2.91,\n" +
                "  \"block_reward24\":2.91000000000001,\n" +
                "  \"block_reward3\":2.91,\n" +
                "  \"block_reward7\":2.91000000000001,\n" +
                "  \"last_block\":5579691,\n" +
                "  \"difficulty\":3.30379091836704e+15,\n" +
                "  \"difficulty24\":3.36274457606072e+15,\n" +
                "  \"difficulty3\":3.31498728179027e+15,\n" +
                "  \"difficulty7\":3.23471484613122e+15,\n" +
                "  \"nethash\":221723493732897,\n" +
                "  \"exchange_rate\":0.081102,\n" +
                "  \"exchange_rate24\":0.0806033793103448,\n" +
                "  \"exchange_rate3\":0.0808462743534483,\n" +
                "  \"exchange_rate7\":0.0796459982348111,\n" +
                "  \"exchange_rate_vol\":12569.36768651,\n" +
                "  \"exchange_rate_curr\":\"BTC\",\n" +
                "  \"market_cap\":\"$74,411,967,634\",\n" +
                "  \"pool_fee\":\"0.000000\",\n" +
                "  \"estimated_rewards\":\"0.006280\",\n" +
                "  \"btc_revenue\":\"0.00050936\",\n" +
                "  \"revenue\":\"$4.71\",\n" +
                "  \"cost\":\"$0.97\",\n" +
                "  \"profit\":\"$3.73\",\n" +
                "  \"status\":\"Active\",\n" +
                "  \"lagging\":false,\n" +
                "  \"timestamp\":1525810518\n" +
                "}");
        OkHttpClient httpClient = HttpClientUtils.createHttpClient(response.toString(), 200);
        BigDecimal expectedBlockTime = BigDecimal.valueOf(14.9005);
        BigDecimal expectedBlockReward = BigDecimal.valueOf(2.91);
        BigDecimal expectedBlockCount = BigDecimal.valueOf(5579691.0);
        BigDecimal expectedDifficulty = BigDecimal.valueOf(3.30379091836704e+15);
        BigDecimal expectedNetworkHashrate = BigDecimal.valueOf(221723493732897L);
        Utils.testCoinInfo(httpClient,
                           WHAT_TO_MINE,
                           BTC,
                           expectedBlockTime,
                           expectedBlockReward,
                           expectedBlockCount,
                           expectedDifficulty,
                           expectedNetworkHashrate);
    }

    @Test(expected = CoinInfoRequestorException.class)
    public void testApiError() throws CoinInfoRequestorException {
        JSONObject response = new JSONObject("{  \n" +
                "  \"errors\":[  \n" +
                "    \"Could not find active coin with id 0\"\n" +
                "  ]\n" +
                "}");
        OkHttpClient httpClient = HttpClientUtils.createHttpClient(response.toString(), 200);
        Utils.testApiError(httpClient, WHAT_TO_MINE, BTC, "Could not find active coin with id 0");
    }

    @Test(expected = CoinInfoRequestorException.class)
    public void testInternalServerError() throws CoinInfoRequestorException {
        Utils.testInternalServerError(WHAT_TO_MINE, BTC);
    }

    @Test(expected = CoinInfoRequestorException.class)
    public void testEmptyResponse() throws CoinInfoRequestorException {
        Utils.testEmptyResponse(WHAT_TO_MINE, BTC);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnsupportedCoin() throws CoinInfoRequestorException {
        Utils.testUnsupportedCoin(WHAT_TO_MINE, BCH);
    }

}
