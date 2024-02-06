package hibernateConfiguration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import model.Casilla;
import model.Fitxa;
import model.Jugador;
import model.Partida;

public class HibernateUtil {
	
	private static EntityManagerFactory entityManagerFactory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/parchismanuelmarc");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "1234");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                
                settings.put(Environment.HBM2DDL_AUTO, "validate");

                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");

                settings.put("hibernate.connection.serverTimezone", "UTC");

                configuration.addAnnotatedClass(Jugador.class);
                configuration.addAnnotatedClass(Partida.class);
                configuration.addAnnotatedClass(Casilla.class);
                configuration.addAnnotatedClass(Fitxa.class);
                
                configuration.setProperties(settings);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                entityManagerFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return entityManagerFactory;
    }
}
