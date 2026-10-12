# PlantCausal

Java 17 desktop workspace for plant target-trial protocols, standardized experimental data, design audits and reproducible fixed-endpoint analysis.

## Run

Install a JDK 17+ and Maven, then:

```bash
mvn clean verify
java -jar target/plantcausal-core-0.3.0.jar
```

On Windows, double-click `start-PlantCausal.bat`. The GitHub Actions artifact includes the executable JAR and verified example reports; a downloaded JAR runs with Java 17+, without Maven.

## Desktop workflow

1. Open a schema-v1 project or load built-in data.
2. Review and save the target-trial protocol; inspect its tables.
3. Validate identifiers, allocation levels, times, outcomes and provenance.
4. Open **Analysis**, enter prespecified numeric baseline covariate IDs, and optionally adjust genotype or block.
5. Run analysis, inspect coefficients, confidence intervals and residuals, then export to an empty folder outside the project.

**Load estimator demo** opens 24 independent synthetic units. Its prespecified covariate is `baseline_x`; the known treatment coefficient is 2. This is a numerical verification fixture, not a published biological dataset.

## CLI

```bash
java -jar target/plantcausal-core-0.3.0.jar validate examples/linear_verification
java -jar target/plantcausal-core-0.3.0.jar analyze examples/linear_verification output/analysis --covariates=baseline_x
java -jar target/plantcausal-core-0.3.0.jar demo-maize benchmark/examples/maize_salt_stress output/maize-demo.pdf
```

Analysis options: `--covariates=id1,id2`, `--genotype`, `--block`. All adjustment decisions require the study's scientific rationale; the software does not automatically select confounders. Output directories must be empty.

Exports include `PlantCausal_Report.pdf`, `analysis.json`, `coefficients.tsv`, `diagnostics.tsv` and a complete `inputs/` snapshot. JSON retains software version, selected parameters, input SHA-256, sample IDs, contrast direction and interpretation limitations.

Existing public-data import commands remain available:

```bash
java -jar target/plantcausal-core-0.3.0.jar import-pine benchmark/raw/10100580/Mycorrhiza_exposure_drought.xlsx output/pine
java -jar target/plantcausal-core-0.3.0.jar stage-grass benchmark/raw/4306840/181004_Biomass_2014.xls output/grass
```

## Supported analysis and scientific limits

Version 0.3.0 implements **additive OLS for one numeric endpoint at the protocol's primary time**, with independent analysis/allocation units, two strategy arms, numeric pre-treatment covariates and optional genotype/block fixed effects. Scaled QR fits avoid normal-equation inversion; finite-df Student-t inference uses classical homoskedastic errors. The coefficient contrasts the **first strategy minus the second**, in the outcome's original units.

Format validation does not establish causal identification. Observational outputs are labeled associations requiring identification review; randomized outputs remain subject to design review. Synthetic examples are labeled demonstrations. A baseline assignment contrast is not relabeled as a sustained-treatment or per-protocol effect.

Missing outcomes/covariates, duplicate endpoints or technical measurements, shared clusters, nested allocation, singular designs and unsupported effect scales are rejected. There is no silent row dropping, zero imputation, unrequested pooling or automatic confounder selection.

Cluster-robust inference, longitudinal treatment/censoring models, IPW/AIPW, survival endpoints and missing-data estimators are **not implemented**. These require separate algorithms and validation; this release does not claim a general-purpose causal inference engine. The maintained application is `org.plantcausal.core.Main` / `desktop.DesktopApp`; older standalone UI prototype classes are compatibility scaffolding, not application launchers.

## Standards, verification and provenance

- [Data standard](DATA_STANDARD.md)
- [Scientific and implementation audit](docs/SCIENTIFIC_AUDIT.md)
- [Current verification](docs/VERIFICATION_0.3.0.md)
- [Public benchmark provenance](benchmark/README.md)

Public benchmark datasets test import and format handling; their true causal effects are unknown. The legacy four-record maize salt example is hand-entered demo data with only one residual degree of freedom. It must not be used as evidence for a biological conclusion.
