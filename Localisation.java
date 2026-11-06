public class Localisation {
    private final String batiment;
    private final int etage;

    public Localisation(String batiment, int etage) {
        if (batiment == null || batiment.isBlank()) {
            throw new IllegalArgumentException("Le bâtiment ne doit pas être nul ou vide");
        }
        if (etage < 0) {
            throw new IllegalArgumentException("L'étage ne peut pas être négatif");
        }
        this.batiment = batiment;
        this.etage = etage;
    }

    public String getBatiment() { return batiment; }
    public int getEtage() { return etage; }

    @Override
    public String toString() { return batiment + ", étage " + etage; }
}
