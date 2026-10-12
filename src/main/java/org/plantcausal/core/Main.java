package org.plantcausal.core;
import java.nio.file.*;
public final class Main {
    public static void main(String[] args) throws Exception {
        if(args.length==1 && (args[0].equals("--help")||args[0].equals("-h"))) {
            System.out.println("PlantCausal 0.3.0\nUsage: gui | validate <project> | analyze <project> <empty-output-folder> [--covariates=id1,id2] [--genotype] [--block] | demo-maize [package-directory output.pdf] | import-pine <xlsx> <output> | stage-grass <xls> <output> | fetch-benchmark <output>");return;
        }
        if(args.length==0 || (args.length==1&&args[0].equals("gui"))) {org.plantcausal.desktop.DesktopApp.launch();return;}
        if(args[0].equals("analyze")) {
            if(args.length<3) throw new IllegalArgumentException("Usage: analyze <project> <output-directory> [--covariates=id1,id2] [--genotype] [--block]");
            java.util.List<String> covariates=new java.util.ArrayList<>(); boolean genotype=false,block=false;
            for(int i=3;i<args.length;i++) {
                if(args[i].startsWith("--covariates=")) {
                    if(!covariates.isEmpty()) throw new IllegalArgumentException("Repeated covariate option");
                    String value=args[i].substring(13); if(!value.isBlank()) covariates.addAll(java.util.Arrays.asList(value.split(",",-1)));
                } else if(args[i].equals("--genotype")) genotype=true;
                else if(args[i].equals("--block")) block=true;
                else throw new IllegalArgumentException("Unknown analysis option: "+args[i]);
            }
            Path input=Path.of(args[1]),output=Path.of(args[2]);
            var result=new org.plantcausal.analysis.AnalysisService().analyze(input,new org.plantcausal.analysis.AnalysisService.Options(covariates,genotype,block));
            new org.plantcausal.analysis.AnalysisExporter().export(input,result,output);
            System.out.println(PackageReader.JSON.writerWithDefaultPrettyPrinter().writeValueAsString(result));return;
        }
        if(args[0].equals("demo-maize")) {
            org.plantcausal.benchmark.MaizeSaltStressDemoRunner.main(java.util.Arrays.copyOfRange(args,1,args.length)); return;
        }
        if(args.length==2 && args[0].equals("fetch-benchmark")) {new BenchmarkFetcher().fetch(Path.of(args[1]));return;}
        if(args.length==3 && args[0].equals("import-pine")) {new BenchmarkImporter().importPine(Path.of(args[1]),Path.of(args[2]));return;}
        if(args.length==3 && args[0].equals("stage-grass")) {new BenchmarkImporter().stageGrass(Path.of(args[1]),Path.of(args[2]));return;}
        if(args.length!=2 || !args[0].equals("validate")) {
            System.err.println("Usage: analyze <project> <output> [options] | demo-maize [package-directory output.pdf] | validate <directory> | import-pine <xlsx> <output> | stage-grass <xls> <output>");System.exit(2);
        }
        var report=new ValidationService().validate(Path.of(args[1]));
        System.out.println(PackageReader.JSON.writerWithDefaultPrettyPrinter().writeValueAsString(report));
        if(!report.schemaValid()) System.exit(1);
    }
}
