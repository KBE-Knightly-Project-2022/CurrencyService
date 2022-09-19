package knightly.CurrencyService.service;

import knightly.CurrencyService.enums.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyExchanger {
    private final BigDecimal silverDecimal = new BigDecimal(10);
    private final BigDecimal goldDecimal = new BigDecimal(100);
    private final BigDecimal donkeyDecimal = new BigDecimal(400);
    private final BigDecimal cowDecimal = new BigDecimal(800);
    private final String BRONZE = "bronze";
    private final String SILVER = "silver";
    private final String GOLD = "gold";
    private final String DONKEY = "donkey";
    private final String COW = "cow";

    public BigDecimal exchangeCurrency(int enteredAmount, Currency requestedCurrency) {
        return switch (requestedCurrency) {
            case bronze -> roundBigDecimal(new BigDecimal(enteredAmount));
            case silver ->  convertToSilver(enteredAmount);
            case gold -> convertToGold(enteredAmount);
            case donkey -> convertToDonkey(enteredAmount);
            case cow -> convertToCow(enteredAmount);
            default -> throw new IllegalStateException("Unexpected value: " + requestedCurrency);
        };
    }

    private BigDecimal convertToSilver(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal divided = copperDecimal.divide(this.silverDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal convertToGold(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal divided = copperDecimal.divide(this.goldDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal convertToDonkey(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal divided = copperDecimal.divide(this.donkeyDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal convertToCow(int copper) {
        BigDecimal copperDecimal = new BigDecimal(copper);
        BigDecimal divided = copperDecimal.divide(this.cowDecimal);

        return roundBigDecimal(divided);
    }

    private BigDecimal roundBigDecimal(BigDecimal toRound){
        return toRound.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
