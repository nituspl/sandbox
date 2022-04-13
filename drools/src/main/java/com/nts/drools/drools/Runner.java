package com.nts.drools.drools;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nts.drools.model.CalcData;
import com.nts.drools.model.CarData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private KieContainer kContainer;

    @Override
    public void run(String... args) throws Exception {
        CalcData calcData = new CalcData();
        CarData car = new CarData();
        car.setAge(10);
        calcData.setCar(car);

        executeRules(calcData);
        System.out.println(calcData);
    }

    public void executeRules(CalcData calcData) {
        KieSession kieSession = kContainer.newKieSession();
        kieSession.insert(calcData);
        int rulesFired = kieSession.fireAllRules();
        log.info("All {} rules fired", rulesFired);
        kieSession.dispose();
    }
}
