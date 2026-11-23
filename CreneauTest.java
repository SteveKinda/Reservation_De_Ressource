import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreneauTest{

    @Test
    void creneauValideCalculeSaDuree(){
        Creneau creneau = new Creneau("Lundi", 8, 10);
        assertEquals(2, creneau.getDuree());
    }

    @Test
    void creneauAvecFinAvantDebutEstRefuse(){
        assertThrows(IllegalArgumentException.class, () -> new Creneau("Mardi", 14, 12));
    }

    @Test
    void creneauAvecJourVideEstRefuse(){
        assertThrows(IllegalArgumentException.class, () -> new Creneau("  ", 8, 10));
    }

    @Test
    void bornesHorairesSontControlees(){
        assertThrows(IllegalArgumentException.class, () -> new Creneau("Lundi", -1, 10));
        assertThrows(IllegalArgumentException.class, () -> new Creneau("Lundi", 20, 25));
        assertEquals(4, new Creneau("Lundi", 20, 24).getDuree());   // 24 h est la borne maximale permise
    }

    @Test
    void chevauchementDetecteEtCreneauxAdjacentsAcceptes(){
        Creneau matin = new Creneau("Lundi", 8, 10);
        assertTrue(matin.chevauche(new Creneau("Lundi", 9, 11)));
        assertFalse(matin.chevauche(new Creneau("Lundi", 10, 12)));
        assertFalse(matin.chevauche(new Creneau("Mardi", 8, 10)));
    }

    @Test
    void creneauxIdentiquesSontEgauxAvecLeMemeHashCode(){
        Creneau a = new Creneau("Lundi", 8, 10);
        Creneau b = new Creneau("Lundi", 8, 10);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, new Creneau("Mardi", 8, 10));
    }
}

