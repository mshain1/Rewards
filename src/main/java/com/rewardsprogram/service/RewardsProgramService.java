package com.rewardsprogram.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.rewardsprogram.model.Transaction;
import com.rewardsprogram.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.util.*;

@Service
public class RewardsProgramService {
    private Utils utils;
    public RewardsProgramService (Utils utils)
    {
        this.utils = utils;
    }

    public void calculateRewards(){
        List<Transaction> transaction = null;
        try {
            transaction = getTransactions();
        } catch (IOException e) {
            System.out.println("Error occurred on parsing dataset: " + e.getMessage());
        }
        printResults (createTransactionsMap(transaction));
    }

    private List<Transaction> getTransactions() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        return objectMapper.readValue(new File("src/main/resources/transactions.json"), typeFactory.constructCollectionType(List.class, Transaction.class));

    }

    protected Map<String, TreeMap<Integer,Integer>> createTransactionsMap(List<Transaction> transaction)
    {
        Map <String, TreeMap<Integer,Integer>> transactionsMap = new HashMap<>();
        Calendar cal = Calendar.getInstance();
        for (Transaction singleTransaction : transaction) {
            //If data came bad - ignore this entry
            if (singleTransaction.getCustomerId() != null && singleTransaction.getTransactionDate() != null && singleTransaction.getAmount()!=null)
            {
                cal.setTime(singleTransaction.getTransactionDate());
                int month = cal.get(Calendar.MONTH) + 1;
                int totalRewards = 0;
                //calculate rewards
                int currentReward = utils.calculateReward(singleTransaction.getAmount());
                if (transactionsMap.containsKey(singleTransaction.getCustomerId())) {
                    if (transactionsMap.get(singleTransaction.getCustomerId()).containsKey(month)) {
                        totalRewards = transactionsMap.get(singleTransaction.getCustomerId()).get(month);
                    }
                    //update new total for current month
                    totalRewards += currentReward;
                    transactionsMap.get(singleTransaction.getCustomerId()).put(month, totalRewards);
                } else {
                    //put new customer with initial month and reward - TreeMap for sorting months
                    TreeMap<Integer, Integer> newMap = new TreeMap<>();
                    newMap.put(month, currentReward);
                    transactionsMap.put(singleTransaction.getCustomerId(), newMap);
                }

            }
        }
        return transactionsMap;
    }

    private void printResults (Map <String, TreeMap<Integer,Integer>> transactionsMap){
        for (Map.Entry<String, TreeMap<Integer, Integer>> customerEntry : transactionsMap.entrySet()) {
            String customerId = customerEntry.getKey();
            int customerTotalRewards = 0;
            System.out.println("*******************");
            System.out.println("Summary of rewards for customer: " + customerId);
            for (Map.Entry<Integer, Integer> monthEntry : customerEntry.getValue().entrySet()) {
                Month m = Month.of(monthEntry.getKey());
                int totalRewardsPerMonth = monthEntry.getValue();
                customerTotalRewards += totalRewardsPerMonth;
                System.out.println("Month:" +m.name() + " Rewards:"+ totalRewardsPerMonth);
            }
            System.out.println("Total rewards for all months: " + customerTotalRewards);
        }
    }
}
