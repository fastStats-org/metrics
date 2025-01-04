package org.faststats;

import org.faststats.chart.SimplePieChart;
import org.faststats.chart.SingleLineChart;
import org.jspecify.annotations.NullMarked;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@NullMarked
public class TestMetrics extends Metrics {
    private final Logger logger = Logger.getLogger(getClass().getName());

    public TestMetrics(UUID consumerId, boolean enabled, int projectId) {
        super(consumerId, enabled, projectId);
        addChart(new SimplePieChart("onlineMode", () -> String.valueOf(true)));
        addChart(new SimplePieChart("pluginVersion", () -> "0.1.0"));
        addChart(new SimplePieChart("serverType", () -> "Paper"));
        addChart(new SimplePieChart("serverVersion", () -> "1.21.4"));
        addChart(new SingleLineChart("playerAmount", () -> 52));
    }

    @Override
    protected void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }

    @Override
    protected String getURL() {
        return "http://localhost:3000/metrics";
    }

    public static void main(String[] args) {
        new TestMetrics(UUID.randomUUID(), true, 12345);
        while (true);
    }
}
