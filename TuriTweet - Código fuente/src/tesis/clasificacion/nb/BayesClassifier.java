package tesis.clasificacion.nb;


import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import tesis.util.Tweet;



/**
 * A concrete implementation of the abstract Classifier class.  The Bayes
 * classifier implements a naive Bayes approach to classifying a given set of
 * features: classify(feat1,...,featN) = argmax(P(cat)*PROD(P(featI|cat)
 * El clasificador de bayes, implementa un naive bayes para clasificar un set de caracteristicas dado
 * @author Philipp Nolte
 *
 * @see http://en.wikipedia.org/wiki/Naive_Bayes_classifier
 *
 * @param <T> The feature class.
 * @param <K> The category class.
 */
public class BayesClassifier<T, K> extends Classifier<T, K> {

    /**
     * Calculates the product of all feature probabilities: PROD(P(featI|cat)
     * Calcula la productoria de las verosimilitudes
     * @param features The set of features to use.
     * @param category The category to test for.
     * @return The product of all feature probabilities.
     */
	 //features: caracteristicas
	 //category: clase
    private float featuresProbabilityProduct(Collection<T> features,
            K category) {
        float product = 1.0f;
        //Calculo de productoria
        for (T feature : features)
            product *= this.featureWeighedAverage(feature, category);
        return product;
    }

    /**
     * Calculates the probability that the features can be classified as the
     * category given.
     * 
     * Calcula la probabilidad de una caracteristica dada una categoria.
     *
     * @param features The set of features to use.
     * @param category The category to test for.
     * @return The probability that the features can be classified as the
     *    category.
     * 
     */
    private Double categoryProbability(Collection<T> features, K category, Tweet tweet) {
        boolean NB = true;
        Double categoryCount = this.categoryCount(category);
        Double categoriesTotal = this.getCategoriesTotal();
        float featuresProbablitiProfuct = featuresProbabilityProduct(features, category);
        //Ecuacin de NaiveBayes :)
                            /***    P( Vj )  *  TT P ( ai | Vj )   ***/
        Double resultado = (categoryCount / categoriesTotal)  * featuresProbablitiProfuct;
        //Aplicar Heuristica elevando P(Vj)^Wt
//        Heuristicas heuristica = new Heuristicas();
//        Double wt = heuristica.obtenerHeuristica(tweet, category.toString());
//        resultado = Math.pow(resultado, wt);
        //System.out.println("Resultado: "+resultado);
        //System.out.println("("+categoryCount+"/"+categoriesTotal+")*"+featuresProbablitiProfuct+" = "+resultado);
       // System.out.println("------------------------------------------------------------------");
        return resultado;
    }

    /**
     * Retrieves a sorted <code>Set</code> of probabilities that the given set
     * of features is classified as the available categories.
     * Devuelve una lista ordenada sin duplicados de las probabilidades tomadas del conjunto de caracteristicas
     * que estan clasificadas en alguna de las categorias.
     * @param features The set of features to use.
     * @return A sorted <code>Set</code> of category-probability-entries.
     */
    private SortedSet<Classification<T, K>> categoryProbabilities(
            Collection<T> features, Tweet tweet) {
        /*
         * Sort the set according to the possibilities. Because we have to sort
         * by the mapped value and not by the mapped key, we can not use a
         * sorted tree (TreeMap) and we have to use a set-entry approach to
         * achieve the desired functionality. A custom comparator is therefore
         * needed.
         * Ordena las probabilidades
         */
        SortedSet<Classification<T, K>> probabilities =
                new TreeSet<>(
                        new Comparator<Classification<T, K>>() {
                    //Ordenamiento de las probabilidades y categorias mediante un comparador
                    @Override
                    public int compare(Classification<T, K> o1,
                            Classification<T, K> o2) {
                        int toReturn = Float.compare(
                                o1.getProbability(), o2.getProbability());
                        if ((toReturn == 0)
                                && !o1.getCategory().equals(o2.getCategory()))
                            toReturn = -1;
                        return toReturn;
                    }
                });

        for (K category : this.getCategories())
            probabilities.add(new Classification<>(features, category, this.categoryProbability(features, category, tweet).floatValue()));
        return probabilities;
    }

    /**
     * Classifies the given set of features.
     * Clasifica el conjunto de caracteristicas dadas
     * @return The category the set of features is classified as.
     * Devuelve la categoria con la probabilidad mas alta
     */
    @Override
    public Classification<T, K> classify(Collection<T> features, Tweet tweet) {
        SortedSet<Classification<T, K>> probabilites = this.categoryProbabilities(features, tweet);
        if (probabilites.size() > 0) {
            return probabilites.last();//Retorna la mayor probabilidad
        }
        return null;
    }

    /**
     * Classifies the given set of features. and return the full details of the
     * classification.
     *
     * @return The set of categories the set of features is classified as.
     */
    public Collection<Classification<T, K>> classifyDetailed(
            Collection<T> features, Tweet tweet) {
        return this.categoryProbabilities(features, tweet);
    }

}
