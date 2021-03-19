package danielos.model.core.managers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ManagerDAO
 */
@Stateless
@LocalBean
public class ManagerDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManagerDAO() {

	}

	/**
	 * finder generico que devuelve todas las entidades correspondientes a una
	 * tabla.
	 * 
	 * @param clase            La clase que se desea consultar. Ejemplo:
	 *                         <ul>
	 *                         <li>Usuario.class</li>
	 *                         </ul>
	 * @param propiedadOrderBy Indica la propiedad de la entidad por la que se desea
	 *                         ordenar la consulta. Por ejemplo:
	 *                         <ul>
	 *                         <li>nombre</li>
	 *                         <li>codigo</li>
	 *                         </ul>
	 *                         Puede aceptar null o una cadena vacia, en este caso
	 *                         no se ordena el resultado.
	 * @param ascendente       Si ordena el resultado en forma ascendente o no.
	 * @return Listado resultante
	 */
	public List findAll(Class clase, String propiedadOrderBy, boolean ascendente) {
		Query q;
		List listado;
		String sentenciaJPQL;
		if (propiedadOrderBy == null || propiedadOrderBy.length() == 0)
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o";
		else {
			if (ascendente)
				sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o ORDER BY o." + propiedadOrderBy + " asc";
			else
				sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o ORDER BY o." + propiedadOrderBy
						+ " desc";
		}
		q = em.createQuery(sentenciaJPQL);
		listado = q.getResultList();
		System.out.println("Consultados " + clase.getSimpleName() + ": " + listado.size() + " objetos.");
		return listado;

	}

	/**
	 * finder Generico que devuelve todas las entidades de una tabla
	 * 
	 * @param clase La clase que se desea consultar. Ejemplo:
	 *              <ul>
	 *              <li>Usuario.class</li>
	 *              </ul>
	 * @return Listado resultante.
	 */
	public List findAll(Class clase) {
		return findAll(clase, null, false);
	}

	/**
	 * finder Generico que devuelve todas las entidades de una tabla
	 * 
	 * @param clase            La clase que se desea consultar. Ejemplo:
	 *                         <ul>
	 *                         <li>Usuario.class</li>
	 *                         </ul>
	 * @param propiedadOrderBy Indica la propiedad de la entidad por la que se desea
	 *                         ordenar la consulta. Por ejemplo:
	 *                         <ul>
	 *                         <li>nombre</li>
	 *                         <li>codigo</li>
	 *                         </ul>
	 *                         Puede aceptar null o una cadena vacia, en este caso
	 *                         no se ordena el resultado.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findAll(Class clase, String propiedadOrderBy) {
		return findAll(clase, propiedadOrderBy, true);
	}

	/**
	 * Finder generico para buscar un objeto especifico.
	 * 
	 * @param clase La clase sobre la que se desea consultar, ejemplo: Usuario.class
	 * @param pID   Identificador (la clave primaria) que permitira la busqueda.
	 * @return El objeto solicitado (si existiera).
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object findById(Class clase, Object pID) throws Exception {
		if (pID == null)
			throw new Exception("Debe especificar el codigo para buscar el dato.");
		Object o;
		try {
			o = em.find(clase, pID);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al buscar la informacion especificada (" + pID + ") : " + e.getMessage());
		}
		return o;
	}
	

	/**
	 * Finder generico que permite aplicar clausulas where y order by.
	 * <b>Atencion</b>: este metodo puede ser atacado por el metodo de INYECCION
	 * SQL, por lo cual se considera inseguro. Para evitar este ataque debe utilizar
	 * el paso de parametros y el reemplazo mediante el metodo <b>setParameter</b>
	 * del objeto Query.
	 * 
	 * @param clase          La entidad sobre la que se desea consultar. Ej:
	 *                       Usuario.class
	 * @param pClausulaWhere Clausula where de tipo JPQL (sin la palabra reservada
	 *                       WHERE). Ejemplo:
	 *                       <ul>
	 *                       <li>o.nombre='Antonio'</li>
	 *                       <li>o.nombre='Antonio' and o.telefono='0444-434'</li>
	 *                       <li>o.nombre like 'Ant%'</li>
	 *                       </ul>
	 * @param pOrderBy       Clausula order by de tipo JPQL (sin la palabra
	 *                       reservada ORDER BY). Puede ser null para no ordenar.
	 *                       por ejemplo:
	 *                       <ul>
	 *                       <li>o.nombre</li>
	 *                       <li>o.codigo,o.nombre</li>
	 *                       </ul>
	 *                       Tanto para la clausula <b>where</b> como <b>order
	 *                       by</b> debe utilizarse el alias de entidad "o".
	 * @return Listado resultante.
	 */
	@SuppressWarnings("rawtypes")
	public List findWhere(Class clase, String pClausulaWhere, String pOrderBy) {
		Query q;
		List listado;
		String sentenciaJPQL;
		if (pOrderBy == null || pOrderBy.length() == 0)
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + pClausulaWhere;
		else
			sentenciaJPQL = "SELECT o FROM " + clase.getSimpleName() + " o WHERE " + pClausulaWhere + " ORDER BY "
					+ pOrderBy;
		q = em.createQuery(sentenciaJPQL);
		listado = q.getResultList();
		return listado;
	}

	/**
	 * Almacena un objeto (persistencia).
	 * 
	 * @param pObjeto El objeto a insertar.
	 * @throws Exception
	 */
	public void insertar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede insertar un objeto null.");
		try {
			em.persist(pObjeto);
		} catch (Exception e) {
			throw new Exception("No se pudo insertar el objeto especificado: " + e.getMessage());
		}
	}

	/**
	 * Elimina un objeto de la persistencia.
	 * 
	 * @param clase La clase correspondiente al objeto que se desea eliminar.
	 * @param pID   El identificador del objeto que se desea eliminar.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public void eliminar(Class clase, Object pID) throws Exception {
		if (pID == null) {
			throw new Exception("Debe especificar un identificador para eliminar el dato solicitado.");
		}
		Object o = findById(clase, pID);
		try {
			em.remove(o);
		} catch (Exception e) {
			throw new Exception("No se pudo eliminar el dato: " + e.getMessage());
		}
	}

	/**
	 * Actualiza la informacion de un objeto en la persistencia.
	 * 
	 * @param pObjeto Objeto que contiene la informacion que se debe actualizar.
	 * @throws Exception
	 */
	public void actualizar(Object pObjeto) throws Exception {
		if (pObjeto == null)
			throw new Exception("No se puede actualizar un dato null");
		try {
			em.merge(pObjeto);
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar el dato: " + e.getMessage());
		}
	}

	public EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Metodo generico que permite ejecutar sentencias JPQL.
	 * 
	 * @param pClausulaJPQL Sentencia JPQL que se va a ejecutar.
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List execJPQL(String pClausulaJPQL) {
		Query q;
		List listado;
		q = em.createQuery(pClausulaJPQL);
		listado = q.getResultList();

		return listado;
	}

	public Long obtenerSecuenciaPostgresql(String nombreSecuencia) throws Exception {
		String sentenciaSQL;
		Query q;
		BigInteger valSeq;
		Long valorSeq = null;
		try {
			sentenciaSQL = "SELECT nextval('" + nombreSecuencia + "')";
			q = em.createNativeQuery(sentenciaSQL);
			valSeq = (BigInteger) q.getSingleResult();
			valorSeq = valSeq.longValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor de secuencia (" + nombreSecuencia + ") : " + e.getMessage());
		}
		return valorSeq;
	}

	/**
	 * Obtiene el valor maximo de una propiedad correspondiente a una entidad.
	 * 
	 * @param clase           La clase sobre la que se quiere consultar.
	 * @param nombrePropiedad El nombre de la propiedad sobre la que se quiere
	 *                        obtener el valor maximo.
	 * @return El valor maximo.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public long obtenerValorMax(Class clase, String nombrePropiedad) throws Exception {
		Query q;
		String sentenciaSQL;
		BigDecimal valorMax;
		try {
			sentenciaSQL = "SELECT MAX(o." + nombrePropiedad + ") FROM " + clase.getSimpleName() + " o";
			q = em.createQuery(sentenciaSQL);
			valorMax = (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: " + clase.getCanonicalName() + "[" + nombrePropiedad
					+ "]. " + e.getMessage());
		}
		if (valorMax == null)
			return 0;
		return valorMax.longValue();
	}

	/**
	 * Obtiene el valor maximo de una propiedad correspondiente a una entidad.
	 * 
	 * @param clase           La clase sobre la que se quiere consultar.
	 * @param nombrePropiedad El nombre de la propiedad sobre la que se quiere
	 *                        obtener el valor maximo.
	 * @param pClausulaWhere  Clausula where.
	 * @return El valor maximo.
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public long obtenerValorMaxWhere(Class clase, String nombrePropiedad, String pClausulaWhere) throws Exception {
		Query q;
		String sentenciaSQL;
		BigDecimal valorMax;
		try {
			sentenciaSQL = "SELECT MAX(o." + nombrePropiedad + ") FROM " + clase.getSimpleName() + " o" + " WHERE "
					+ pClausulaWhere;
			q = em.createQuery(sentenciaSQL);
			valorMax = (BigDecimal) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al obtener valor max: " + clase.getCanonicalName() + "[" + nombrePropiedad
					+ "]. " + pClausulaWhere + e.getMessage());
		}
		if (valorMax == null)
			return 0;
		return valorMax.longValue();
	}

}
