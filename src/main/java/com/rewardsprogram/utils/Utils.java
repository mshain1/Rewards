package com.rewardsprogram.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
public class Utils {
    //Get the values from property for easier changes
    @Value("${overHundred:2}")
    private int overHundred;
    @Value("${overFifty:1}")
    private int overFifty;

    public int calculateReward (int amount)
    {
        int total = 0;
        if (amount > 50 && amount <= 100)
        {
            total = calculateFiftyRewards(amount);
        }
        if (amount > 100)
        {
            total = calculateHundredRewards(amount);
        }

        return total;
    }

    public int calculateHundredRewards (int amount)
    {
        int total =  ((amount - 100) * overHundred) + 50;
        return total;
    }

    public int calculateFiftyRewards (int amount)
    {
        int total =  (amount - 50) * overFifty;
        return total;
    }


}
