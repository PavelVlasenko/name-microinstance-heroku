package edu.stanford.nlp.trees;

/**
 * A general factory for {@link edu.stanford.nlp.trees.GrammaticalStructure} objects.
 *
 * @author Galen Andrew
 * @author John Bauer
 */
public interface GrammaticalStructureFactory {

  /**
   * Vend a new {@link edu.stanford.nlp.trees.GrammaticalStructure} based on the given {@link edu.stanford.nlp.trees.Tree}.
   *
   * @param t the tree to analyze
   * @return a GrammaticalStructure based on the tree
   */
  GrammaticalStructure newGrammaticalStructure(Tree t);
}
