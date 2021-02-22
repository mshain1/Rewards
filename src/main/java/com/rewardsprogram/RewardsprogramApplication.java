package com.rewardsprogram;

import com.rewardsprogram.service.RewardsProgramService;
import com.rewardsprogram.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;


import static java.lang.System.exit;
@SpringBootApplication
public class RewardsprogramApplication implements CommandLineRunner{

    @Autowired
    private RewardsProgramService rewardsProgramService;

    private Utils utils;

    public static void main(String[] args) {
        SpringApplication.run(RewardsprogramApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        rewardsProgramService.calculateRewards();

        exit(0);

    }

}
