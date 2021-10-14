package dao.controle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dao.impl.ComentarioDAOImpl;
import dao.impl.CriticaDAOImpl;
import dao.impl.JogoDAOImpl;
import dao.impl.UsuarioDAOImpl;
import net.sf.cglib.proxy.Enhancer;

// A anota��o @Configuration indica que esta classe possui um ou mais m�todos anotados com @Bean.
@Configuration
public class FabricaDeDao {
	
	// @Bean diz ao Spring:
	// Aqui est� uma inst�ncia da classe UsuarioDAOImpl, por favor, 
	// guarde esta inst�ncia e me devolva quando eu a pedir.

	// @Autowired em UsuarioAppServiceImpl diz:
	// Por favor, me retorne uma inst�ncia do tipo UsuarioDAO, isto �,  
	// aquela que foi criada mais cedo com a anota��o @Bean.
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