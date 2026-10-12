# Scientific and implementation audit — 0.3.0

## Identification and scope

A target trial specifies eligibility, treatment strategies, assignment, time zero, follow-up, outcome and causal contrast. Emulating its design does not resolve unmeasured confounding, missing information, interference or other data limitations. Software therefore preserves `identificationStatus=NOT_ASSESSED`; a successful regression is not proof of an effect.

The supported estimator fits an additive model

`Y = intercept + beta_T * I(strategy=A) + beta_X * baseline_X + category effects + error`.

With the protocol contrast `A-B`, `beta_T` is the adjusted association on the outcome's original mean-difference scale. A causal interpretation additionally requires the correct population/selection mechanism, exchangeability, positivity, consistency, appropriate model specification and a defensible no-interference/independence assumption. Without treatment interactions, the conditional treatment coefficient is constant; the software does not claim individualized effects.

Randomized metadata does not independently verify randomization, adherence or outcome availability. A sustained-strategy estimand cannot be identified by ignoring time-varying treatment or censoring. The estimator reports a baseline assignment contrast and warns when the requested strategy-effect definition differs.

## Statistical checks

- Column-scaled QR estimates coefficients; SVD checks ill-conditioning. Standard errors derive from the QR pseudoinverse and `SSE/(n-p)`.
- Two-sided p-values use `I_(df/(df+t²))(df/2, 1/2)`. CI limits use the finite-df inverse Student-t critical value, not the constant 1.96.
- Exact Student-t inference is distribution-based numerical evaluation; its inferential justification requires independent normal errors with common variance. It is not an unconditional guarantee for arbitrary observational data.
- Constant outcomes have undefined R-squared; zero coefficient variance gives degenerate test statistics. Numerical edge cases are covered explicitly.
- The four-record maize example has df=1. Treatment estimate -2.0, SE=0.4, p≈0.1256659164, 95% CI≈[-7.082481895,3.082481895]. No biological claim is supported by this illustrative fixture.
- The 24-record verification fixture recovers coefficients [4,2,0.5], df=21, SE(T)=0.1091089451, and CI(T)=[1.773095527,2.226904473]. NumPy/SciPy calculations independently verify the reference values.

## Data and workflow checks

Standard packages retain allocation IDs, clusters, eligibility, variable roles, time, provenance, explicit missingness and technical-replicate identifiers. Analysis reads exactly one primary-time endpoint and one prespecified baseline record per eligible analysis unit. Missing values or multiple measurements require an explicit estimator/aggregation plan; they are not silently handled.

Only numeric `baseline_confounder` or `precision_covariate` roles can enter numeric baseline adjustment. Time-zero covariates require declared `before_first_treatment` timing; source ordering still needs review. Mediators, post-treatment covariates, shared clusters and nested allocations are rejected by this estimator.

Exports retain exact inputs, their SHA-256, parameters, coefficients and per-unit fitted/residual values. Inputs are rechecked after analysis and before/during export. Changing parameters or protocol clears the desktop's displayed estimates. Long-running work runs outside Swing's event thread; active actions are disabled to prevent duplicate execution.

## Corrections from the previous implementation

The previous runner only printed stages, the end-to-end test did not run/assert the workflow, several distribution classes used incorrect placeholder formulas, regression APIs had incompatible constructors, and some PDF adapters wrote text files or returned nonexistent paths. Maintained statistical entry points now share actual inference. PDF adapters produce loadable PDFBox 3 documents, with an embedded licensed font. Missing schema/model interfaces and the JavaFX-only viewer dependency were corrected to match the Swing desktop.

## Remaining limitations

This is a complete executable baseline workflow, not a completed implementation of every causal method. No IPW/AIPW, longitudinal strategy emulation, cluster-robust variance, survival analysis, multiple imputation, automatic unit conversion or ontology reconciliation is offered. Baseline OLS must not be substituted for these designs. Older standalone UI prototypes are not exposed by the maintained desktop; future feature additions require meaningful behavior and tests before exposure.

## Primary references

- Hernán MA, Robins JM. *Causal Inference: What If*, Chapter 22. https://www.hsph.harvard.edu/miguel-hernan/wp-content/uploads/sites/1268/2024/01/hernanrobins_WhatIf_2jan24.pdf
- Hernán MA, Dahabreh IJ, Dickerman BA, Swanson SA. The Target Trial Framework for Causal Inference From Observational Data: Why and When Is It Helpful? https://pubmed.ncbi.nlm.nih.gov/39961105/
- Apache Commons Math 3.6.1 QR decomposition. https://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/org/apache/commons/math3/linear/QRDecomposition.html
