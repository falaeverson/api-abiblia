package br.com.abiblia.util;

//import static br.com.abiblia.util.ConversoresPIER.buildPagePIER;
import static br.com.abiblia.util.Conversores.buildPage;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

//import br.com.conductor.pier.util.PagePIER;

public class GenericConvert {

    public static <E, T> E convertModelMapper(T source, Class<E> typeDestination, MatchingStrategy strategy) {
        E model = null;
        if (source != null && typeDestination != null) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(strategy);
             model = modelMapper.map(source, typeDestination);
        }

        return model;
   }

   public static <E, T> E convertModelMapper(T source, Class<E> typeDestination) {
        return convertModelMapper(source, typeDestination, MatchingStrategies.STRICT);
   }

   public static <E, T> E convertModelMapper(T source, Type destinationType) {

        E model = null;
        if (source != null && destinationType != null) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             model = modelMapper.map(source, destinationType);
        }

        return model;
   }

   public static <E, T> void convertModelMapper(T source, E destination) {

        if (source != null && destination != null) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             modelMapper.map(source, destination);
        }
   }
   
   public static <E, T> List<E> convertModelMapper(List<T> source, Type destinationType) {
        return convertModelMapper(source, destinationType, MatchingStrategies.STRICT);
   }

   public static <E, T> List<E> convertModelMapper(List<T> source, Type destinationType, MatchingStrategy strategy) {

        List<E> model = null;
        if (source != null && destinationType != null) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(strategy);
             model = modelMapper.map(source, destinationType);
        }

        return model;
   }

   public static <T, E> PageDefault convertModelMapperToPagePIER(Page<T> page, Type typeDestination) {

        if (page != null && typeDestination != null) {

             List<E> arrayList = convertModelMapper(page.getContent(), typeDestination);
             Pageable pageable = new PageRequest(page.getNumber(), page.getSize());
             Page<E> pageResponse = new PageImpl<>(arrayList, pageable, page.getTotalElements());
             return buildPage(pageResponse);
        }

        return null;
   }
   public static <T, E> PageDefault convertModelMapperToPagePIER(Page<T> page, Type typeDestination, MatchingStrategy strategy) {

        if (page != null && typeDestination != null) {

             List<E> arrayList = convertModelMapper(page.getContent(), typeDestination, strategy);
             Pageable pageable = new PageRequest(page.getNumber(), page.getSize());
             Page<E> pageResponse = new PageImpl<>(arrayList, pageable, page.getTotalElements());
             return buildPage(pageResponse);
        }

        return null;
   }

   public static <E, T> List<E> convertWithMapping(List<T> source, Type destinationType, PropertyMap<T, E> mapping) {

        List<E> model = null;
        if (source != null && destinationType != null) {

             ModelMapper modelMapper = new ModelMapper();

             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             modelMapper.addMappings(mapping);
             model = modelMapper.map(source, destinationType);
        }

        return model;
   }

   public static <T, E> void convertWithMapping(T source, E destination, PropertyMap<T, E> mapping) {

        if (source != null && destination != null) {
             
             ModelMapper modelMapper = new ModelMapper();
             
             modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
             modelMapper.addMappings(mapping);
             modelMapper.map(source, destination);
        }

   }
   
   public static <T, E> PageDefault convertWithMappingToPagePIER(Page<T> page, Type typeDestination, PropertyMap<T, E> mapping) {

        if (page != null && typeDestination != null) {

             List<E> arrayList = convertWithMapping(page.getContent(), typeDestination, mapping);
             Pageable pageable = new PageRequest(page.getNumber(), page.getSize());
             Page<E> pageResponse = new PageImpl<>(arrayList, pageable, page.getTotalElements());
             return buildPage(pageResponse);
        }

        return null;
   }
   
   public static <T, E> E convertWithMapping(T source, Class<E> typeDestination, PropertyMap<T, E> mapping) {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addMappings(mapping);

        return modelMapper.map(source, typeDestination);
   }
   
//   public static <T, E> PageDefault convertModelMapperToPagePIER(br.com.conductor.pier.groovy.util.page.PagePIERCustom<T> page, Type typeDestination) {
//
//        if (page != null && typeDestination != null) {
//
//             List<E> arrayList = convertModelMapper(page.getContent(), typeDestination);
//             Pageable pageable = new PageRequest(page.getNumber(), page.getSize());
//             Page<E> pageResponse = new PageImpl<>(arrayList, pageable, page.getTotalElements());
//             return buildPage(pageResponse);
//        }
//
//        return null;
//   }
}
