package com.group9.model.managers;

import com.group9.model.entities.EntityConfiguration;
import com.group9.model.entities.characters.attackers.AttackerType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Manages the probabilities for spawning different types of attackers.
 */
public class ProbabilityManager {
    private final Map<AttackerType, Double> probabilities;
    private final List<AttackerType> attackerTypes;
    private final Random random;

    public ProbabilityManager() {
        this.probabilities = new HashMap<>();
        this.attackerTypes = EntityConfiguration.getAttackerTypes();
        this.random = new Random();
        initializeProbabilities(attackerTypes);
    }

    // Quick probabilities fix for each attacker type, could use improvements.
    // Could specify probabilities (and which wave to spawn from) in a config file or in the AttackerType.
    /**
     * Initializes the probabilities for each attacker type.
     * Sets the initial probability of KNIGHTGARGAMEL to 0.
     *
     * @param attackerTypes List of attacker types to initialize probabilities for.
     */
    private void initializeProbabilities(List<AttackerType> attackerTypes) {
        double initialProbability = 1.0 / (attackerTypes.size() - 1); // Exclude KNIGHTGARGAMEL from initial probability distribution
        for (AttackerType type : attackerTypes) {
            if ("KNIGHTGARGAMEL".equals(type.getName())) {
                probabilities.put(type, 0.0);
            } else {
                probabilities.put(type, initialProbability);
            }
        }
    }

    /**
     * Updates the probability of spawning a specific attacker type.
     * Adjusts the probabilities of other attacker types proportionally.
     *
     * @param typeName      The name of the attacker type.
     * @param newProbability The new probability to set for the attacker type.
     */    public void setProbability(String typeName, double newProbability) {
        AttackerType type = getAttackerTypeByName(typeName);
        if (type != null) {
            double totalOtherProbability = 1.0 - newProbability;
            double totalCurrentOtherProbability = probabilities.values().stream()
                    .filter(prob -> !probabilities.get(type).equals(prob))
                    .mapToDouble(Double::doubleValue)
                    .sum();

            probabilities.put(type, newProbability);

            // Adjust other probabilities proportionally
            probabilities.replaceAll((attackerType, prob) -> {
                if (!attackerType.equals(type)) {
                    return prob / totalCurrentOtherProbability * totalOtherProbability;
                }
                return prob;
            });

            normalizeProbabilities();
        }
    }

    /**
     * Normalizes the probabilities so that their sum equals 1.
     */
    private void normalizeProbabilities() {
        double totalProbability = probabilities.values().stream().mapToDouble(Double::doubleValue).sum();
        probabilities.replaceAll((type, prob) -> prob / totalProbability);
    }

    public void resetProbabilities() {
        initializeProbabilities(attackerTypes);
    }

    private AttackerType getAttackerTypeByName(String name) {
        return attackerTypes.stream()
                .filter(type -> type.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public AttackerType getRandomAttackerType() {
        double totalProbability = probabilities.values().stream().mapToDouble(Double::doubleValue).sum();
        double randomValue = random.nextDouble() * totalProbability;

        double cumulativeProbability = 0.0;
        for (Map.Entry<AttackerType, Double> entry : probabilities.entrySet()) {
            cumulativeProbability += entry.getValue();
            if (randomValue <= cumulativeProbability) {
                return entry.getKey();
            }
        }
        return null; // This should never happen if probabilities are set correctly
    }
}