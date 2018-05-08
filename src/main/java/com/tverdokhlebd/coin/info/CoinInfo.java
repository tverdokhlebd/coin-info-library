package com.tverdokhlebd.coin.info;

import java.math.BigDecimal;

import com.tverdokhlebd.mining.commons.coin.CoinType;

/**
 * General information about coin (block time, block reward and etc.).
 *
 * @author Dmitry Tverdokhleb
 *
 */
public class CoinInfo {

    /** Type of coin. */
    private final CoinType coinType;
    /** Block time in seconds. */
    private final BigDecimal blockTime;
    /** Block reward. */
    private final BigDecimal blockReward;
    /** Block count. */
    private final BigDecimal blockCount;
    /** Difficulty. */
    private final BigDecimal difficulty;
    /** Network hashrate. */
    private final BigDecimal networkHashrate;

    /**
     * Creates instance.
     *
     * @param coinType type of coin
     * @param blockTime block time in seconds
     * @param blockReward block reward
     * @param blockCount block count
     * @param difficulty difficulty
     * @param networkHashrate network hashrate
     */
    private CoinInfo(CoinType coinType, BigDecimal blockTime, BigDecimal blockReward, BigDecimal blockCount, BigDecimal difficulty,
            BigDecimal networkHashrate) {
        super();
        this.coinType = coinType;
        this.blockTime = blockTime;
        this.blockReward = blockReward;
        this.blockCount = blockCount;
        this.difficulty = difficulty;
        this.networkHashrate = networkHashrate;
    }

    /**
     * Gets coin type.
     *
     * @return coin type
     */
    public CoinType getCoinType() {
        return coinType;
    }

    /**
     * Gets block time.
     *
     * @return block time
     */
    public BigDecimal getBlockTime() {
        return blockTime;
    }

    /**
     * Gets block reward.
     *
     * @return block reward
     */
    public BigDecimal getBlockReward() {
        return blockReward;
    }

    /**
     * Gets block count.
     *
     * @return block count
     */
    public BigDecimal getBlockCount() {
        return blockCount;
    }

    /**
     * Gets difficulty.
     *
     * @return difficulty
     */
    public BigDecimal getDifficulty() {
        return difficulty;
    }

    /**
     * Gets network hashrate.
     *
     * @return network hashrate
     */
    public BigDecimal getNetworkHashrate() {
        return networkHashrate;
    }

    /**
     * Builder of coin info.
     *
     * @author Dmitry Tverdokhleb
     *
     */
    public static class Builder {

        /** Type of coin. */
        private CoinType coinType;
        /** Block time in seconds. */
        private BigDecimal blockTime;
        /** Block reward. */
        private BigDecimal blockReward;
        /** Block count. */
        private BigDecimal blockCount;
        /** Difficulty. */
        private BigDecimal difficulty;
        /** Network hashrate. */
        private BigDecimal networkHashrate;

        /**
         * Creates instance.
         */
        public Builder() {
            super();
        }

        /**
         * Sets coin type.
         *
         * @param coinType new coin type
         * @return builder
         */
        public Builder setCoinType(CoinType coinType) {
            this.coinType = coinType;
            return this;
        }

        /**
         * Sets block time.
         *
         * @param blockTime new block time
         * @return builder
         */
        public Builder setBlockTime(BigDecimal blockTime) {
            this.blockTime = blockTime;
            return this;
        }

        /**
         * Sets block reward.
         *
         * @param blockReward new block reward
         * @return builder
         */
        public Builder setBlockReward(BigDecimal blockReward) {
            this.blockReward = blockReward;
            return this;
        }

        /**
         * Sets block count.
         *
         * @param blockCount new block count
         * @return builder
         */
        public Builder setBlockCount(BigDecimal blockCount) {
            this.blockCount = blockCount;
            return this;
        }

        /**
         * Sets difficulty.
         *
         * @param difficulty new difficulty
         * @return builder
         */
        public Builder setDifficulty(BigDecimal difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * Sets network hashrate.
         *
         * @param networkHashrate new network hashrate
         * @return builder
         */
        public Builder setNetworkHashrate(BigDecimal networkHashrate) {
            this.networkHashrate = networkHashrate;
            return this;
        }

        /**
         * Builds coin info.
         *
         * @return coin info
         */
        public CoinInfo build() {
            return new CoinInfo(coinType, blockTime, blockReward, blockCount, difficulty, networkHashrate);
        }

    }

}
