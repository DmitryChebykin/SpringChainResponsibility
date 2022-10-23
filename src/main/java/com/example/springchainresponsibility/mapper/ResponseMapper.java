package com.example.springchainresponsibility.mapper;

import com.example.springchainresponsibility.payload.Response;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ResponseMapper {
    Response updateResponse(@MappingTarget Response response1, Response response2);

    default List<String> updateList(@MappingTarget List<String> target, List<String> source) {
        target.addAll(source);
        return target;
    }
}
