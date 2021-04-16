

package com.sleep.server.dto.mapper;
        import com.sleep.server.dao.entity.Album;
        import com.sleep.server.dto.AudioCreateRequestDto;
        import com.sleep.server.dao.entity.Audio;
        import org.mapstruct.Mapper;
        import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AlbumMapperConvert {
    AlbumMapperConvert INSTANCE = Mappers.getMapper(AlbumMapperConvert.class);

    Album toEntity(AlbumCreateRequestDto dto);
}
