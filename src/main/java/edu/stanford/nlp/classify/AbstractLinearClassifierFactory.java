package edu.stanford.nlp.classify;

import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.ling.RVFDatum;
import edu.stanford.nlp.util.Index;
import edu.stanford.nlp.util.HashIndex;

import java.lang.ref.Reference;
import java.util.Collection;
import java.util.List;

/**
 * Shared methods for training a {@link edu.stanford.nlp.classify.LinearClassifier}.
 * Inheriting classes need to implement the
 * <code>trainWeights</code> method.
 *
 * @author Dan Klein
 *
 * @author Sarah Spikes (sdspikes@cs.stanford.edu) (Templatization)
 *
 * @param <L> The type of the labels in the Dataset and Datum
 * @param <F> The type of the features in the Dataset and Datum
 */

public abstract class AbstractLinearClassifierFactory<L, F> implements ClassifierFactory<L, F, Classifier<L, F>> {

  private static final long serialVersionUID = 1L;

  Index<L> labelIndex = new HashIndex<L>();
  Index<F> featureIndex = new HashIndex<F>();

  public AbstractLinearClassifierFactory() {
  }

  int numFeatures() {
    return featureIndex.size();
  }

  int numClasses() {
    return labelIndex.size();
  }

  public Classifier<L,F> trainClassifier(List<RVFDatum<L, F>> examples) {
    Dataset<L, F> dataset = new Dataset<L, F>();
    dataset.addAll(examples);
    return trainClassifier(dataset);
  }

  protected abstract double[][] trainWeights(GeneralDataset<L, F> dataset) ;

  /**
   * Takes a {@link java.util.Collection} of {@link edu.stanford.nlp.ling.Datum} objects and gives you back a
   * {@link edu.stanford.nlp.classify.Classifier} trained on it.
   *
   * @param examples {@link java.util.Collection} of {@link edu.stanford.nlp.ling.Datum} objects to train the
   *                 classifier on
   * @return A {@link edu.stanford.nlp.classify.Classifier} trained on it.
   */
  public LinearClassifier<L, F> trainClassifier(Collection<Datum<L, F>> examples) {
    Dataset<L, F> dataset = new Dataset<L, F>();
    dataset.addAll(examples);
    return trainClassifier(dataset);
  }

  /**
   * Takes a {@link java.lang.ref.Reference} to a {@link java.util.Collection} of {@link edu.stanford.nlp.ling.Datum}
   * objects and gives you back a {@link edu.stanford.nlp.classify.Classifier} trained on them
   *
   * @param ref {@link java.lang.ref.Reference} to a {@link java.util.Collection} of {@link
   *            edu.stanford.nlp.ling.Datum} objects to train the classifier on
   * @return A Classifier trained on a collection of Datum
   */
  public LinearClassifier<L, F> trainClassifier(Reference<? extends Collection<Datum<L, F>>> ref) {
    Collection<Datum<L, F>> examples = ref.get();
    return trainClassifier(examples);
  }


  /**
   * Trains a {@link edu.stanford.nlp.classify.Classifier} on a {@link edu.stanford.nlp.classify.Dataset}.
   *
   * @return A {@link edu.stanford.nlp.classify.Classifier} trained on the data.
   */
  public LinearClassifier<L, F> trainClassifier(GeneralDataset<L, F> data) {
    labelIndex = data.labelIndex();
    featureIndex = data.featureIndex();
    double[][] weights = trainWeights(data);
    return new LinearClassifier<L, F>(weights, featureIndex, labelIndex);
  }

}
