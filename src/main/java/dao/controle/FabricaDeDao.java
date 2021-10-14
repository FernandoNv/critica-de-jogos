package dao.controle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.impl.ComentarioDAOImpl;
import dao.impl.CriticaDAOImpl;
import dao.impl.JogoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import net.sf.cglib.proxy.Enhancer;

// A anotação @Configuration indica que esta classe possui um ou mais métodos anotados com @Bean.
@Configuration
public class FabricaDeDao {
	
	// @Bean diz ao Spring:
	// Aqui está uma instância da classe UsuarioDAOImpl, por favor, 
	// guarde esta instância e me devolva quando eu a pedir.

	// @Autowired em UsuarioAppServiceImpl diz:
	// Por favor, me retorne uma instância do tipo UsuarioDAO, isto é,  
	// aquela que foi criada mais cedo com a anotação @Bean.
	@Bean
	public static UsuarioDAOImpl getUsuarioDao() throws Exception {
		return getDao(dao.impl.UsuarioDAOImpl.class);
	}

	@Bean
	public static JogoDAOImpl getJogoDao() throws Exception {
		return getDao(dao.impl.JogoDAOImpl.class);
	}
	
	@Bean
	public static CriticaDAOImpl getCriticaDao() throws Exception{
		return getDao(dao.impl.CriticaDAOImpl.class);
	}
	
	@Bean
	public static ComentarioDAOImpl getComentarioDao() throws Exception{
		return getDao(dao.impl.ComentarioDAOImpl.class);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getDao(Class<T> classeDoDao) throws Exception {
		return (T) Enhancer.create(classeDoDao, new InterceptadorDeDAO());
	}
}