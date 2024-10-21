# CocktailApp

**CocktailApp** est une application Android développée en Kotlin sous Android Studio qui permet de rechercher et d'afficher des cocktails en utilisant l'API de [TheCocktailDB](https://www.thecocktaildb.com/api.php). L'application propose une interface utilisateur moderne avec un splash screen, une page d'accueil, une page de connexion, et une page de recherche permettant de lister et rechercher des cocktails.

## Fonctionnalités principales

- **Splash Screen** : Affiche un écran de démarrage pendant le chargement de l'application.
- **Page de connexion** : Accès à une page de connexion pour authentifier l'utilisateur.
- **Page d'accueil** : Navigation intuitive permettant de passer à la recherche de cocktails ou à la consultation des listes de cocktails par catégorie ou ingrédient.
- **Recherche de cocktails** : Recherche par nom, ingrédient, ou première lettre via un champ de saisie.
- **Liste de cocktails** : Affiche une liste de cocktails filtrée par catégorie, ingrédient ou type d'alcool (alcoolisé ou non-alcoolisé).
- **Affichage des détails d'un cocktail** : Affichage complet des informations d'un cocktail en cliquant sur un élément de la liste.
- **Recherche d'un ingrédient par nom** : Recherche et consultation des détails des ingrédients.

## API Utilisée

L'application utilise l'API publique de [TheCocktailDB](https://www.thecocktaildb.com/api.php) pour obtenir les données des cocktails. Voici quelques endpoints importants utilisés dans l'application :

### 1. Recherche de cocktail par nom
Rechercher un cocktail par son nom :
```
GET https://www.thecocktaildb.com/api/json/v1/1/search.php?s={cocktail_name}
```

Exemple :
```
GET https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
```

### 2. Lister tous les cocktails par première lettre
Afficher la liste des cocktails en fonction de leur première lettre :
```
GET https://www.thecocktaildb.com/api/json/v1/1/search.php?f={letter}
```

### 3. Recherche de cocktails par ingrédient
Rechercher des cocktails contenant un ingrédient spécifique :
```
GET https://www.thecocktaildb.com/api/json/v1/1/filter.php?i={ingredient}
```

Exemples :
```
GET https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Gin
GET https://www.thecocktaildb.com/api/json/v1/1/filter.php?i=Vodka
```

### 4. Filtrer par type d'alcool
Afficher la liste des cocktails en fonction de leur type (alcoolisé ou non) :
```
GET https://www.thecocktaildb.com/api/json/v1/1/filter.php?a={alcoholic_type}
```

Exemples :
```
GET https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Alcoholic
GET https://www.thecocktaildb.com/api/json/v1/1/filter.php?a=Non_Alcoholic
```

### 6. Recherche d'un ingrédient par nom
Obtenir les détails d'un ingrédient en le recherchant par nom :
```
GET https://www.thecocktaildb.com/api/json/v1/1/search.php?i={ingredient_name}
```

Exemple :
```
GET https://www.thecocktaildb.com/api/json/v1/1/search.php?i=vodka
```

### 8. Obtenir un cocktail aléatoire
Afficher les détails d'un cocktail aléatoire :
```
GET https://www.thecocktaildb.com/api/json/v1/1/random.php
```

## Installation

1. **Clonez le projet** :
    ```bash
    git clone https://github.com/votre-utilisateur/CocktailApp.git
    ```

2. **Ouvrez le projet dans Android Studio**.

3. **Ajoutez votre clé API** dans le fichier de configuration si nécessaire (la clé API n'est pas requise pour la version gratuite de l'API de TheCocktailDB).

4. **Lancez l'application** dans un émulateur ou sur un appareil Android.

Voici la section mise à jour avec les technologies supplémentaires :

---

## Technologies utilisées

- **Kotlin** : Langage principal utilisé pour le développement Android.
- **Android Studio** : IDE utilisé pour développer l'application.
- **Retrofit** : Pour les appels réseau à l'API.
- **ViewModel & LiveData** : Pour gérer l'UI en suivant le pattern MVVM.
- **Coroutines** : Pour gérer les opérations asynchrones.
- **Picasso/Glide** : Pour le chargement et l'affichage des images des cocktails.
- **Room** : Pour la gestion d'une base de données SQLite locale, permettant de stocker et récupérer des informations de manière efficace.
- **Firebase Authentication** : Utilisé pour la gestion de l'authentification des utilisateurs, permettant la connexion et l'inscription via des fournisseurs comme Google, Facebook, ou email/mot de passe.
- **Firebase Cloud Messaging (FCM)** : Pour l'envoi de notifications push aux utilisateurs, leur permettant de recevoir des alertes ou mises à jour en temps réel.

## Captures d'écran

*(Ajouter des captures d'écran de l'application ici)*
