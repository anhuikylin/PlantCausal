# PlantCausal

**PlantCausal** is a Java-based framework for plant causal analysis. It establishes a standardized **Target Trial Data Model** for integrating plant experiments, multi-omics datasets, and future causal inference algorithms.

## Current version: Desktop 0.2.1

This release focuses on the foundation of causal analysis:

- PlantCausal Data Standard v1.0 core model
- Target Trial representation
- Schema validation service
- Validation report framework
- Desktop GUI validation interface
- Benchmark-ready architecture

The current version performs **data design auditing and validation only**. Causal effect estimation algorithms will be integrated in subsequent releases.

## Core concept

PlantCausal follows a workflow inspired by target trial emulation:

```
Experimental design
        |
        v
Target Trial Schema
        |
        v
Data validation
        |
        v
Benchmark datasets
        |
        v
Causal inference engine
```

## Data Standard v1.0

The core schema describes:

| Component | Description |
|---|---|
| Population | Plant materials, sample size, genotype availability |
| Intervention | Environmental or experimental treatments |
| Outcome | Phenotypes and molecular traits |
| Covariates | Potential confounding variables |
| Measurements | Omics platforms and data files |
| Data source | Provenance and publication information |

## Validation Service

The validator checks:

- required fields
- schema completeness
- missing provenance
- experimental design consistency

Example:

```java
ValidationResult result = new ValidationService().validate(targetTrial);
```

## Desktop application

Modules:

| Module | Function |
|---|---|
| Overview | Project management and examples |
| Protocol | Target Trial editing |
| Data Preview | Dataset inspection |
| Validation | Schema checking and reports |
| Benchmarks | Public dataset management |

## Roadmap

### Phase 1: Standardization

- Target Trial Data Standard v1.0
- Benchmark database
- Data quality control

### Phase 2: Causal engine

- Bayesian networks
- Structural equation models
- causal discovery methods
- graph-based causal models

### Phase 3: Plant knowledge integration

- genotype
- transcriptomics
- proteomics
- metabolomics
- environmental metadata

## Build

Requirements:

- Java 17+
- Maven 3.8+

```bash
mvn verify
java -jar target/plantcausal-core.jar
```

## Repository structure

```
PlantCausal
 |
 |-- schema          # Data Standard models
 |-- service         # Validation services
 |-- gui             # Desktop interface
 |-- benchmark       # Public datasets
 |-- engine          # Future causal algorithms
```

## License

MIT License

PlantCausal aims to provide an open computational framework for reproducible causal analysis in plant science.
