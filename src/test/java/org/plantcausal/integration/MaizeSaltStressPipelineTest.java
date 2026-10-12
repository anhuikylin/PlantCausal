package org.plantcausal.integration;

import org.junit.jupiter.api.Test;
import org.plantcausal.benchmark.MaizeSaltStressDemoRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MaizeSaltStressPipelineTest {

    @Test
    void runMaizeSaltStressPipeline() {
        assertDoesNotThrow(() -> {
            MaizeSaltStressDemoRunner runner = new MaizeSaltStressDemoRunner();
            runner.run();
        });
    }
}
