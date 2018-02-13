/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lykoju
 */
public class Mehrwertsteuer {

    private final int id;
    private final char klasse;
    private final float steuer;

    /**
     * Konstruktor.
     *
     * @param id ID der Mehrwertsteuer in der DB
     * @param klasse Der Bezeichner fuer die Klasse
     * @param steuer Der Prozentsatz der Mehrwertsteuer von 1 ausgehend, z.B.
     * 19% -> 1.19
     */
    public Mehrwertsteuer(int id, char klasse, float steuer) {
        this.id = id;
        this.klasse = klasse;
        this.steuer = steuer;
    }

    public int getId() {
        return id;
    }

    public char getKlasse() {
        return klasse;
    }

    public float getSteuer() {
        return steuer;
    }
}
