package ecom.ioc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 Lit les fichiers YAML de configuration et instancie les classes par réflexion.
 Supporte deux formats :
    - paires clé: valeur  (services.yml, discount.yml)
    - listes indentées    (notification.yml)
 */
public class ConfigLoader {

    // Lit un fichier au format clé: valeur 
    public static Map<String, String> lireFichier(String cheminRelatif) throws IOException {
        Map<String, String> config = new LinkedHashMap<>(); // LinkedHashMap = ordre de lecture conservé
        lireFichierInterne(cheminRelatif, lignes -> lirePaires(lignes, config));
        return config;
    }

    // Lit une liste YAML sous une clé donnée 
    public static List<String> lireListe(String cheminRelatif, String cle) throws IOException {
        List<String> resultat = new ArrayList<>();
        lireFichierInterne(cheminRelatif, lignes -> lireListeCible(lignes, cle, resultat));
        return resultat;
    }

    /*
      Cherche le fichier d'abord dans le classpath (JAR), sinon dans src/ (mode dev).
      Ex. cheminRelatif = "ecom/config/services.yml" → src/ecom/config/services.yml
     */
    private static void lireFichierInterne(String cheminRelatif, LecteurFichier lecteur) throws IOException {
        InputStream stream = ConfigLoader.class.getClassLoader().getResourceAsStream(cheminRelatif);

        if (stream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
                lecteur.lire(toutesLesLignes(reader));
            }
            return;
        }

        Path path = Path.of("src", cheminRelatif);
        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                lecteur.lire(toutesLesLignes(reader));
            }
        }
    }

    private static List<String> toutesLesLignes(BufferedReader reader) throws IOException {
        List<String> lignes = new ArrayList<>();
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            lignes.add(ligne);
        }
        return lignes;
    }

    // Parse les lignes "CLE: valeur" — ignore commentaires (#) et listes (-). 
    private static void lirePaires(List<String> lignes, Map<String, String> config) {
        for (String ligne : lignes) {
            String nettoyee = ligne.trim();
            if (nettoyee.isEmpty() || nettoyee.startsWith("#") || nettoyee.startsWith("-")) {
                continue;
            }
            int separateur = nettoyee.indexOf(':');
            if (separateur > 0) {
                String cle = nettoyee.substring(0, separateur).trim();
                String valeur = nettoyee.substring(separateur + 1).trim();
                if (!valeur.isEmpty()) {
                    config.put(cle, valeur);
                }
            }
        }
    }

    /*
      Parse une liste YAML sous une clé cible.
     Gère deux cas :
        IEmailSender: ecom.services.EmailNotificationService   (valeur sur la même ligne)
        IEmailSender:                                           (liste sur les lignes suivantes)
          - ecom.services.EmailNotificationService
     */
    private static void lireListeCible(List<String> lignes, String cleCible, List<String> resultat) {
        boolean dansSection = false;

        for (String ligne : lignes) {
            String nettoyee = ligne.trim();

            if (nettoyee.isEmpty() || nettoyee.startsWith("#")) {
                continue;
            }

            if (nettoyee.startsWith("-")) {
                if (dansSection) {
                    resultat.add(nettoyee.substring(1).trim());
                }
                continue;
            }

            int separateur = nettoyee.indexOf(':');
            if (separateur <= 0) {
                continue;
            }

            String cle = nettoyee.substring(0, separateur).trim();
            String valeur = nettoyee.substring(separateur + 1).trim();

            if (!cle.equals(cleCible)) {
                dansSection = false;
                continue;
            }

            if (!valeur.isEmpty()) {
                resultat.add(valeur);
                dansSection = false;
            } else {
                dansSection = true; // lignes "- ..." suivantes appartiennent à cette clé
            }
        }
    }

    @FunctionalInterface
    private interface LecteurFichier {
        void lire(List<String> lignes) throws IOException;
    }

    /*
      Instanciation dynamique par réflexion : transforme un nom de classe (String du YAML)
      en objet Java, sans new MaClasse() en dur dans le code.
     */
    @SuppressWarnings("unchecked")
    public static <T> T instancier(String nomClasse) {
        try {
            Class<?> clazz = Class.forName(nomClasse);
            return (T) clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Impossible d'instancier la classe : " + nomClasse, e);
        }
    }
}
