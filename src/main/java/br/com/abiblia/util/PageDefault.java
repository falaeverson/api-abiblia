package br.com.abiblia.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import br.com.twsoftware.alfred.object.Objeto;


public class PageDefault extends PageRequest {
	
    public static final int PAGE_DEFAULT = 0;
    
    public static final int LIMIT_DEFAULT = 50;
    
    private static final long serialVersionUID = -644380311337417661L;

    @SuppressWarnings("deprecation")
	private PageDefault(Integer page, Integer limit){
         super(page, limit);
    }

    @SuppressWarnings("deprecation")
	private PageDefault(Integer page, Integer limit, Sort sort){
         super(page, limit, sort);
    }

    /**
     * Método responsável por setar os parâmetros nescessários para a solicitação de uma paginação.
     * 
     * @param page <br/>
     * Indica qual página deverá ser retornada; <br/>
     * Caso não seja informado nenhum valor para esse parâmetro, será assumido como default 0. 
     * @param limit <br/>
     * Indica o limite de registros a ser exibido por página; <br/>
     * Caso não seja informado nenhum valor para esse parâmetro ou o valor informado seja maior que 50, será assumido como default 50.
     * 
     * @return {@link PageablePIER}
     * 
     */
    public static PageDefault setPageable(Integer page, Integer limit) {

         if (Objeto.isBlank(page)) {
              page = 0;
         }

         if (Objeto.isBlank(limit) || limit >= LIMIT_DEFAULT) {
              limit = LIMIT_DEFAULT;
         }

         return new PageDefault(page, limit);
    }
    
    public static PageDefault setPageable(Integer page, Integer limit, String campos, Sort.Direction order) {

        if (Objeto.isBlank(page)) {
             page = 0;
        }

        if (Objeto.isBlank(limit) || limit >= LIMIT_DEFAULT) {
             limit = LIMIT_DEFAULT;
        }
        
        if (Objeto.notBlank(campos) ){
            
        	return new PageDefault(page, limit, Sort.by(order, campos));
       }

        return new PageDefault(page, limit);
   }

}
