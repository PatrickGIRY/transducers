# Il était une fois une fonction `reduce`

Supposons une collection d'entiers:

    Collection<Integer> integers = Arrays.asList(1, 2, 3, 4)

Dans une approche impérative, lorsque nous voulons calculer une valeur à partir d'une liste de valeurs nous devons utiliser une boucle sur la liste de valeurs et accumuler le résultat à l'extérieur de la boucle. Par exemple pour calculer la somme des entiers nous procédons comme suit:

    int sum = 0;
    for (int i : integers) {
      sum += i;
    }

Dans une approche fonctionnelle nous déléguons ce traitement à une fonction nommée `reduce`. Pour sommer les entiers nous procédons comme ceci:

    reduce((sum, i) -> sum + i, 0, integers)

Le traitement à effectuer est passé en paramètre en définissant une <i> fonction de réduction </i> (`(sum, i) -&gt; sum + i`).

Savez-vous qu'avec cette <i> fonction de réduction </i> on peut définir les fonctions `map`et `filter`? On peut même combiner les `map`et `filter` pour former une <i> fonction de reduction </i> qui s'applique à une collection sans avoir de collection intermédiaire. Cette technique s'appelle les [`Transducers`](http://clojure.org/reference/transducers). Les [`Transducers`](http://clojure.org/reference/transducers) sont un concept qui vient de l'univers Clojure. Mais même en Clojure, tout le monde ne les connait pas. Je les ai découverts grâce aux excellentes présentations d'Arnaud Lemaire ([@lilobase](https://twitter.com/Lilobase)). Arnaud utilise des langages dynamiques non typés (python, PHP, etc...) pour sa démonstration. J'ai décider de relever le défi de coder les [`Transducers`](http://clojure.org/reference/transducers) en Java 8 avec ses types génériques qui ne sont pas toujours simples à dompter. Comme si cela ne suffit pas, je vais même montrer au passage comment ça marche, et même comment le concept est inévitable si comme un craftsman qui se respecte nous refactorisons régulièrement note code. Vous refactorisez régulièrement aussi n'est-ce pas?

Notre démarche consiste à l'aide d'un exemple métier concret (calculer le montant total des factures dues pour un mois donné) de partir d'une implémentation impérative, pour ensuite aller vers une implémentation fonctionnelle. On verra au passage comme écrire les fonctions `reduce`, `map` et `filter`. En refactorisant successivement notre fonction de calcul nous aboutirons au développement et à l'utilisation des [`Transducers`](http://clojure.org/reference/transducers).

Si le sujet vous intéresse vous pouvez retrouver mon article complet sur le [blog d'Arolla](http://blog.arolla.fr/).