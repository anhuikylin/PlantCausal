package org.plantcausal.causal;

import org.plantcausal.data.PlantCausalDataset;
import org.plantcausal.parser.TsvTable;

import java.util.List;

public class BaselineCausalModel implements CausalModel {

    @Override
    public CausalResult estimate(PlantCausalDataset dataset){

        CausalResult result = new CausalResult();
        Object table = dataset.getTables().get("phenotype");

        if (!(table instanceof TsvTable)) {
            result.setDiagnostics("Phenotype table not found.");
            return result;
        }

        TsvTable phenotype=(TsvTable)table;
        int treatmentIndex=phenotype.getHeaders().indexOf("treatment");
        int outcomeIndex=phenotype.getHeaders().indexOf("outcome");

        if(treatmentIndex<0 || outcomeIndex<0){
            result.setDiagnostics("Required columns: treatment, outcome");
            return result;
        }

        double treatedSum=0, controlSum=0;
        double treatedSq=0, controlSq=0;
        int treatedN=0, controlN=0;

        for(List<String> row: phenotype.getRows()){
            try{
                int treatment=Integer.parseInt(row.get(treatmentIndex));
                double y=Double.parseDouble(row.get(outcomeIndex));
                if(treatment==1){
                    treatedSum+=y;
                    treatedSq+=y*y;
                    treatedN++;
                }else{
                    controlSum+=y;
                    controlSq+=y*y;
                    controlN++;
                }
            }catch(Exception ignored){}
        }

        if(treatedN==0 || controlN==0){
            result.setDiagnostics("Insufficient groups.");
            return result;
        }

        double mean1=treatedSum/treatedN;
        double mean0=controlSum/controlN;
        double ate=mean1-mean0;

        double var1=(treatedSq/treatedN)-mean1*mean1;
        double var0=(controlSq/controlN)-mean0*mean0;
        double se=Math.sqrt(Math.max(0,var1/treatedN+var0/controlN));

        result.setAverageTreatmentEffect(ate);
        result.setStandardError(se);
        result.setConfidenceLow(ate-1.96*se);
        result.setConfidenceHigh(ate+1.96*se);
        result.setSampleSize(treatedN+controlN);
        result.setDiagnostics("Difference-in-means estimator with 95% confidence interval.");

        return result;
    }
}
