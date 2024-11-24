package pe.edu.i202224141.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.i202224141.entity.City;
import pe.edu.i202224141.entity.Country;

import java.util.List;

public class JPAFind {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world_persistence");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Buscar el país con el código "PER" usando find
            Country peru = em.find(Country.class, "PER");

            if (peru != null) {
                // Obtener las ciudades y filtrar con población > 700,000 usando lambda
                List<City> filteredCities = peru.getCities().stream()
                        .filter(city -> city.getPopulation() > 700000)
                        .toList();

                // Imprimir los nombres de las ciudades filtradas
                if (!filteredCities.isEmpty()) {
                    System.out.println("Ciudades peruanas con población mayor a 700,000:");
                    filteredCities.forEach(city -> System.out.println("- " + city.getName()));
                } else {
                    System.out.println("No se encontraron ciudades peruanas con población mayor a 700,000.");
                }
            } else {
                System.out.println("El país con el código 'PER' no existe en la base de datos.");
            }

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e) {
            // En caso de error, revertir la transacción
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y la fábrica
            em.close();
            emf.close();
        }
    }
}
