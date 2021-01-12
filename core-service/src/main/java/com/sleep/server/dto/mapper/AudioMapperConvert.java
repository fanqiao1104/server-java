package com.sleep.server.dto.mapper;
import com.sleep.server.dto.AudioCreateRequestDto;
import com.sleep.server.dao.entity.Audio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AudioMapperConvert {
    AudioMapperConvert INSTANCE = Mappers.getMapper(AudioMapperConvert.class);

    Audio toEntity(AudioCreateRequestDto dto);
}
