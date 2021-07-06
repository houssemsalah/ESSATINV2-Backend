package tn.essatin.erp.strategy.payement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PayementStrategyFactory {
    private Map<PayementStrategyName, PayementStrategy> strategies;

    @Autowired
    public PayementStrategyFactory(Set<PayementStrategy> strategySet) {
        createStrategy(strategySet);
    }

    public PayementStrategy findStrategy(PayementStrategyName strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<PayementStrategy> strategySet) {
        strategies = new HashMap<PayementStrategyName, PayementStrategy>();
        strategySet.forEach(
            strategy -> strategies.put(strategy.getPayementStrategyName(), strategy));
    }
}
