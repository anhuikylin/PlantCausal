package org.plantcausal.causal;

public class DAGBuilder {

    private final CausalGraph graph;

    public DAGBuilder() {
        this.graph = new CausalGraph();
    }

    public DAGBuilder addRelation(String source, String target) {
        CausalNode s = new CausalNode();
        s.setName(source);
        CausalNode t = new CausalNode();
        t.setName(target);
        graph.addNode(s);
        graph.addNode(t);
        graph.addEdge(new CausalEdge(s, t));
        return this;
    }

    public CausalGraph build() {
        return graph;
    }
}
