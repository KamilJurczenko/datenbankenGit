
package com.mycompany.kuechenstudio.model;

import java.util.stream.IntStream;

/**
 *
 * @author cpaun
 */
public class Product {
        
        private static int idCounter;
    	private int id;    
	private String name;    
	private float preis;
	private String beschreibung;
        private int stueckZahl;
        private int reservedStueckZahl; // Reservierte Stückzahl falls im Warenkorb
        
        private int[] arr;
        
        public Product(){           
        }
        
        // Copy Constructor
        public Product(Product product) {
            this.name = product.name;
            this.preis = product.preis;
            this.beschreibung = product.beschreibung;
            this.stueckZahl = product.stueckZahl;   
            this.arr = product.arr;
            this.reservedStueckZahl = product.reservedStueckZahl;
            this.id = product.id;
        }
        
        public Product(String name, float preis, String beschreibung,
        int stueckzahl) {
            id = idCounter;
            idCounter++;
            this.name = name;
            this.preis = preis;
            this.beschreibung = beschreibung;
            this.stueckZahl = stueckzahl;      
            arr = new int[stueckzahl];
            arr = IntStream.rangeClosed(0, stueckzahl).toArray();
	}

    public int getReservedStueckZahl() {
        return reservedStueckZahl;
    }

    public void setReservedStueckZahl(int reservedStueckZahl) {
        this.reservedStueckZahl = reservedStueckZahl;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
        
        public float getPreis() {
            return preis;
        }

    public int getStueckZahl() {
        return stueckZahl;
    }

    public void setStueckZahl(int stueckZahl) {
        this.stueckZahl = stueckZahl;
    }

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public float getpreis() {
		return preis;
	}
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPreis(float preis) {
		this.preis = preis;
	}
}
