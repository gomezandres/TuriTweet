package tesis.clasificacion.nb;


import java.sql.SQLException;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import tesis.util.DBConexion;
import tesis.util.Tweet;


/**
 * Abstract base extended by any concrete classifier.  It implements the basic
 * functionality for storing categories or features and can be used to calculate
 * basic probabilities – both category and feature probabilities. The classify
 * function has to be implemented by the concrete classifier class.
 *
 * @author Philipp Nolte
 *
 * @param <T> A feature class
 * @param <K> A category class
 */
public abstract class Classifier<T, K> implements IFeatureProbability<T, K> {

    /**
     * Initial capacity of category dictionaries.
     */
    private static final int INITIAL_CATEGORY_DICTIONARY_CAPACITY = 16;
    /**
     * Initial capacity of feature dictionaries. It should be quite big, because
     * the features will quickly outnumber the categories. 
     */
    private static final int INITIAL_FEATURE_DICTIONARY_CAPACITY = 5000;
    /**
     * The initial memory capacity or how many classifications are memorized.
     */
    private long memoryCapacity = 1000000000;
    /**
     * A dictionary mapping features to their number of occurrences in each
     * known category.
     */
    private Dictionary<K, Dictionary<T, Integer>> featureCountPerCategory;
    /**
     * A dictionary mapping features to their number of occurrences.
     */
    private Dictionary<T, Integer> totalFeatureCount;
    /**
     * A dictionary mapping categories to their number of occurrences.
     */
    private Dictionary<K, Integer> totalCategoryCount;
    /**
     * The classifier's memory. It will forget old classifications as soon as
     * they become too old.
     */
    private Queue<Classification<T, K>> memoryQueue;

    /**
     * Constructs a new classifier without any trained knowledge.
     */
    public Classifier() {
        this.reset();
    }

    /**
     * Resets the <i>learned</i> feature and category counts.
     */
    public void reset() {
        this.featureCountPerCategory =
                new Hashtable<K, Dictionary<T, Integer>>(
                Classifier.INITIAL_CATEGORY_DICTIONARY_CAPACITY);
        this.totalFeatureCount =
                new Hashtable<T, Integer>(
                Classifier.INITIAL_FEATURE_DICTIONARY_CAPACITY);
        this.totalCategoryCount =
                new Hashtable<K, Integer>(
                Classifier.INITIAL_CATEGORY_DICTIONARY_CAPACITY);
        this.memoryQueue = new LinkedList<Classification<T, K>>();
    }

    /**
     * Returns a <code>Set</code> of features the classifier knows about.
     *
     * @return The <code>Set</code> of features the classifier knows about.
     */
    public Set<T> getFeatures() {
        return ((Hashtable<T, Integer>) this.totalFeatureCount).keySet();
    }

    /**
     * Returns a <code>Set</code> of categories the classifier knows about.
     *
     * @return The <code>Set</code> of categories the classifier knows about.
     */
    public Set<K> getCategories() {
        return ((Hashtable<K, Integer>) this.totalCategoryCount).keySet();
    }

    /**
     * Retrieves the total number of categories the classifier knows about.
     *
     * @return The total category count.
     */
//    public int getCategoriesTotal() {
//        int toReturn = 0;
//        for (Enumeration<Integer> e = this.totalCategoryCount.elements();
//                e.hasMoreElements();) {
//            toReturn += e.nextElement();
//        }
//        return toReturn;
//    }
    public Double getCategoriesTotal() {
        Integer toReturn = 0;
        for (Enumeration<Integer> e = this.totalCategoryCount.elements();
                e.hasMoreElements();) {
            toReturn += e.nextElement();
        }
        //System.out.println("Category TOTAL: "+toReturn);
        return Double.parseDouble(toReturn.toString());
    }

    /**
     * Retrieves the memory's capacity.
     *
     * @return The memory's capacity.
     */
    public long getMemoryCapacity() {
        return memoryCapacity;
    }

    /**
     * Sets the memory's capacity.  If the new value is less than the old
     * value, the memory will be truncated accordingly.
     *
     * @param memoryCapacity The new memory capacity.
     */
    public void setMemoryCapacity(int memoryCapacity) {
        for (long i = this.memoryCapacity; i > memoryCapacity; i--) {
            this.memoryQueue.poll();
        }
        this.memoryCapacity = memoryCapacity;
    }

    /**
     * Increments the count of a given feature in the given category.  This is
     * equal to telling the classifier, that this feature has occurred in this
     * category.
     * Incrementa el conteo de una determinada característica en la categoría dada. 
     * Se le dice al clasificador, que esta caraceristica se ha producido en esta categoría. (Aprendizaje)
     *
     * @param feature The feature, which count to increase.
     * @param category The category the feature occurred in.
     */
    public void incrementFeature(T feature, K category) {
        Dictionary<T, Integer> features = this.featureCountPerCategory.get(category);
        if (features == null) {
            this.featureCountPerCategory.put(category,
                    new Hashtable<T, Integer>(
                    Classifier.INITIAL_FEATURE_DICTIONARY_CAPACITY));
            features = this.featureCountPerCategory.get(category);
        }
        Integer count = features.get(feature);
        if (count == null) {
            features.put(feature, 0);
            count = features.get(feature);
        }
        features.put(feature, ++count);

        Integer totalCount = this.totalFeatureCount.get(feature);
        if (totalCount == null) {
            this.totalFeatureCount.put(feature, 0);
            totalCount = this.totalFeatureCount.get(feature);
        }
        this.totalFeatureCount.put(feature, ++totalCount);
    }

    /**
     * Increments the count of a given category.  This is equal to telling the
     * classifier, that this category has occurred once more.
     * Incrementa el conteo apra una categoria dada. Se le dice al clasificador que esta categoria ha ocurrido una vez mas
     * @param category The category, which count to increase.
     */
    public void incrementCategory(K category) {
        Integer count = this.totalCategoryCount.get(category);
        if (count == null) {
            this.totalCategoryCount.put(category, 0);
            count = this.totalCategoryCount.get(category);
        }
        this.totalCategoryCount.put(category, ++count);
    }

    /**
     * Decrements the count of a given feature in the given category.  This is
     * equal to telling the classifier that this feature was classified once in
     * the category.
     *
     * @param feature The feature to decrement the count for.
     * @param category The category.
     */
    public void decrementFeature(T feature, K category) {
        Dictionary<T, Integer> features =
                this.featureCountPerCategory.get(category);
        if (features == null) {
            return;
        }
        Integer count = features.get(feature);
        if (count == null) {
            return;
        }
        if (count.intValue() == 1) {
            features.remove(feature);
            if (features.size() == 0) {
                this.featureCountPerCategory.remove(category);
            }
        } else {
            features.put(feature, --count);
        }

        Integer totalCount = this.totalFeatureCount.get(feature);
        if (totalCount == null) {
            return;
        }
        if (totalCount.intValue() == 1) {
            this.totalFeatureCount.remove(feature);
        } else {
            this.totalFeatureCount.put(feature, --totalCount);
        }
    }

    /**
     * Decrements the count of a given category.  This is equal to telling the
     * classifier, that this category has occurred once less.
     *
     * @param category The category, which count to increase.
     */
    public void decrementCategory(K category) {
        Integer count = this.totalCategoryCount.get(category);
        if (count == null) {
            return;
        }
        if (count.intValue() == 1) {
            this.totalCategoryCount.remove(category);
        } else {
            this.totalCategoryCount.put(category, --count);
        }
    }

    /**
     * Retrieves the number of occurrences of the given feature in the given
     * category.
     *
     * @param feature The feature, which count to retrieve.
     * @param category The category, which the feature occurred in.
     * @return The number of occurrences of the feature in the category.
     */
public int featureCount(T feature, K category) {
        Dictionary<T, Integer> features =
                this.featureCountPerCategory.get(category);
        if (features == null) {
            return 0;
        }
        Integer count = features.get(feature);
        return (count == null) ? 0 : count.intValue();
    }

    /**
     * Retrieves the number of occurrences of the given category.
     * 
     * @param category The category, which count should be retrieved.
     * @return The number of occurrences.
     */
//    public int categoryCount(K category) {
//        Integer count = this.totalCategoryCount.get(category);
//        return (count == null) ? 0 : count.intValue();
//    }
    public Double categoryCount(K category) {
        Integer count = this.totalCategoryCount.get(category);
        Double retorno = Double.parseDouble(count.toString());
        return (count == null) ? 0.0 : retorno;
    }

    /**
     * {@inheritDoc}
     */
//    @Override
//    public float featureProbability(T feature, K category) {
//        if (this.categoryCount(category) == 0) {
//            return 0;
//        }
//                float retorno = (float) this.featureCount(feature, category)
//                / (float) this.categoryCount(category);
//        System.out.println(category+" - "+feature+ ": " + retorno);
//        return retorno;
//    }
    @Override
    public float featureProbability(T feature, K category) {
        if (this.categoryCount(category) == 0) {
            return 0;
        }       //Calculo de Verosimilitud
        Double retorno = this.featureCount(feature, category) / this.categoryCount(category);
//        System.out.println("caracteristica:"+feature);
//        System.out.println("categoria:"+category);
//        System.out.println(this.featureCount(feature, category)+"/"+this.categoryCount(category)+"="+retorno);
        return retorno.floatValue();
    }

    /**
     * Retrieves the weighed average <code>P(feature|category)</code> with
     * overall weight of <code>1.0</code> and an assumed probability of
     * <code>0.5</code>. The probability defaults to the overall feature
     * probability.
     *
     * @see de.daslaboratorium.machinelearning.classifier.Classifier#featureProbability(Object, Object)
     * @see de.daslaboratorium.machinelearning.classifier.Classifier#featureWeighedAverage(Object, Object, IFeatureProbability, float, float)
     *
     * @param feature The feature, which probability to calculate.
     * @param category The category.
     * @return The weighed average probability.
     */
    public float featureWeighedAverage(T feature, K category) {
        return this.featureWeighedAverage(feature, category,
                null, 1.0f, 0.5f);
    }

    /**
     * Retrieves the weighed average <code>P(feature|category)</code> with
     * overall weight of <code>1.0</code>, an assumed probability of
     * <code>0.5</code> and the given object to use for probability calculation.
     *
     * @see de.daslaboratorium.machinelearning.classifier.Classifier#featureWeighedAverage(Object, Object, IFeatureProbability, float, float)
     *
     * @param feature The feature, which probability to calculate.
     * @param category The category.
     * @param calculator The calculating object.
     * @return The weighed average probability.
     */
    public float featureWeighedAverage(T feature, K category,
            IFeatureProbability<T, K> calculator) {
        //consultar peso para la caracteristica

        return this.featureWeighedAverage(feature, category,
                calculator, 0.5f, 0.5f);
    }

    /**
     * Retrieves the weighed average <code>P(feature|category)</code> with
     * the given weight and an assumed probability of <code>0.5</code> and the
     * given object to use for probability calculation.
     *
     * Recupera el P medio ponderado (feature | categoría)  con el peso dado y una probabilidad supuesta de  0.5  y el objeto dado a usar para el cálculo de probabilidades.
     * @see de.daslaboratorium.machinelearning.classifier.Classifier#featureWeighedAverage(Object, Object, IFeatureProbability, float, float)
     *
     * @param feature The feature, which probability to calculate.
     * @param category The category.
     * @param calculator The calculating object.
     * @param weight The feature weight.
     * @return The weighed average probability.
     */
    public float featureWeighedAverage(T feature, K category,
            IFeatureProbability<T, K> calculator, float weight) {
        return this.featureWeighedAverage(feature, category,
                calculator, weight, 0.5f);
    }

    /**
     * Retrieves the weighed average <code>P(feature|category)</code> with
     * the given weight, the given assumed probability and the given object to
     * use for probability calculation.
     * 
     * Recupera el promedio ponderado a utilizar en el calculo de probabilidades
     * 
     * @param feature The feature, which probability to calculate.
     * @param category The category.
     * @param calculator The calculating object.
     * @param weight The feature weight.
     * @param assumedProbability The assumed probability.
     * @return The weighed average probability.
     */
    public float featureWeighedAverage(T feature, K category,
            IFeatureProbability<T, K> calculator, float weight,
            float assumedProbability) {
//        DBConexion ttp = new DBConexion();
//        weight = ttp.obtenerPeso(feature.toString(), category.toString());
//        weight = ttp.obtenerPeso(feature.toString());
        weight = (float) 0.125;
        /*
         * use the given calculating object or the default method to calculate
         * the probability that the given feature occurred in the given
         * category.
         * 
         */ 
       
        //verosimilitud P(ai | Vj) (p)
        final float basicProbability = (calculator == null) ? this.featureProbability(feature, category) : calculator.featureProbability(feature, category);
        Integer totals = this.totalFeatureCount.get(feature);
        if (totals == null) {
            totals = 0;
        }
        assumedProbability = 0.125f;
//        weight = 0.1f;
//        float finalProbability = basicProbability;
        float finalProbability = (weight * assumedProbability + totals * basicProbability) / (weight + totals);
//        if (feature.equals("<link>")) {
//            System.out.println("LINK");
//            finalProbability = (float) (finalProbability + finalProbability * 0.1);
//        }
        
        return (finalProbability);
    }

    /**
     * Train the classifier by telling it that the given features resulted in
     * the given category.
     * Entrenamiento del clasificador a partir de las categoria para un conjunto de  caracteristicas dadas
     * @param category The category the features belong to.
     * @param features The features that resulted in the given category.
     */
    public void learn(K category, Collection<T> features) {
        this.learn(new Classification<T, K>(features, category));
    }

    /**
     * Train the classifier by telling it that the given features resulted in
     * the given category.
     * Entrenamiento del clasificador a partir de la categoria K para un conjunto de  caracteristicas T dadas
     * @param classification The classification to learn.
     */
    public void learn(Classification<T, K> classification) {

        //Recorre el conjunto de caracteristicas (getFeatureset) del texto
        for (T feature : classification.getFeatureset()) {
            this.incrementFeature(feature, classification.getCategory()); //Incrementa el conteo de una determinada característica en la categoría dada
        }
        this.incrementCategory(classification.getCategory()); //Incrementa el conteo para una categoria dada

//        this.memoryQueue.offer(classification);
        //si el tamaño de memoria en cola es mayor a la capacidad de memoria
//       if (this.memoryQueue.size() > this.memoryCapacity) {
//            Classification<T, K> toForget = this.memoryQueue.remove();
//
//            for (T feature : toForget.getFeatureset()) {
//                this.decrementFeature(feature, toForget.getCategory());
//            }
//            this.decrementCategory(toForget.getCategory());
//        }
    }

    /**
     * The classify method.  It will retrieve the most likely category for the
     * features given and depends on the concrete classifier implementation.
     * Recuperará la categoría más probable para las características dadas y depende de la aplicación concreta clasificador.
     * @param features The features to classify.
     * @return The category most likely.
     */
    public abstract Classification<T, K> classify(Collection<T> features, Tweet tweet);
}
