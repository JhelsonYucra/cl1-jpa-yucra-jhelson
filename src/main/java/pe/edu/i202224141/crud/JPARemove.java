package pe.edu.i202224141.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import pe.edu.i202224141.entity.Country;

public class JPARemove {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("world_persistence");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar la transacción
            em.getTransaction().begin();

            // Encontrar el país imaginario por su código
            Country countryToDelete = em.find(Country.class, "XYZ");

            if (countryToDelete != null) {
                // Eliminar el país (se eliminarán ciudades y lenguajes en cascada)
                em.remove(countryToDelete);
                System.out.println("¡País eliminado correctamente junto con sus ciudades y lenguajes!");
            } else {
                System.out.println("El país con el código 'XYZ' no existe en la base de datos.");
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
