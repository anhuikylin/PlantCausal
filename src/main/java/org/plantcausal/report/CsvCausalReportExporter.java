package org.plantcausal.report;

import java.util.Map;

public class CsvCausalReportExporter {

    public String export(Map<String, Object> values) {
        StringBuilder sb = new StringBuilder();
        sb.append("metric,value\n");
        for (Map.Entry<String,Object> e : values.entrySet()) {
            sb.append(e.getKey()).append(",").append(e.getValue()).append("\n");
        }
        return sb.toString();
    }
}
