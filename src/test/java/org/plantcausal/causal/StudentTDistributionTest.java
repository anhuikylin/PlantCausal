package org.plantcausal.causal;

/**
 * Numerical validation tests for Student-t inference.
 */
public class StudentTDistributionTest {

    public static void main(String[] args) {
        ExactStudentTDistributionEngine engine = new ExactStudentTDistributionEngine();

        double p0 = engine.twoSidedPValue(0.0, 10);
        double pLarge = engine.twoSidedPValue(5.0, 10);

        if (Math.abs(p0 - 1.0) > 1e-6) {
            throw new RuntimeException("Student-t p-value validation failed at t=0");
        }

        if (!(pLarge < p0)) {
            throw new RuntimeException("Student-t tail probability validation failed");
        }

        System.out.println("Student-t validation passed");
    }
}
