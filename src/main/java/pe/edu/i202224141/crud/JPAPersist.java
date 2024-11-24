package pe.edu.i202224141.crud;

import pe.edu.i202224141.entity.City;
import pe.edu.i202224141.entity.Country;
import pe.edu.i202224141.entity.CountryLanguage;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAPersist {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world_persistence");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Crear un país imaginario
            Country newCountry = new Country(
                    "XYZ", "Imaginaria", "Antarctica", "Fantasy Region", 12345.67,
                    2023, 1500000, 85.0, 100000.0, 95000.0,
                    "Imaginaria", "Democracy", "Queen Imaginia", null, "XY"
            );

            // Crear las ciudades asociadas al país
            City city1 = new City("Imaginopolis", newCountry, "Central District", 500000);
            City city2 = new City("Dreamland", newCountry, "Northern District", 300000);
            City city3 = new City("Fantasy Town", newCountry, "Southern District", 200000);

            newCountry.addCity(city1);
            newCountry.addCity(city2);
            newCountry.addCity(city3);

            // Crear los lenguajes asociados al país
            CountryLanguage language1 = new CountryLanguage("Imaginese", newCountry, "T", 80.0);
            CountryLanguage language2 = new CountryLanguage("Dreamish", newCountry, "F", 20.0);

            newCountry.addLanguage(language1);
            newCountry.addLanguage(language2);

            // Persistir el país (junto con ciudades y lenguajes en cascada)
            em.persist(newCountry);

            // Confirmar la transacción
            em.getTransaction().commit();

            System.out.println("¡País, ciudades y lenguajes registrados exitosamente!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
