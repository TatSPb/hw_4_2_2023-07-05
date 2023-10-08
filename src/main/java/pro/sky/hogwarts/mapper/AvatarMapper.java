package pro.sky.hogwarts.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import pro.sky.hogwarts.controller.AvatarController;
import pro.sky.hogwarts.dto.AvatarDto;
import pro.sky.hogwarts.entity.Avatar;

@Component
public class AvatarMapper {

    private final Integer port;

    public AvatarMapper(@Value("${server.port}") Integer port) {
        this.port = port;
    }

    public AvatarDto toDto(Avatar avatar) {
        AvatarDto avatarDto = new AvatarDto();
        avatarDto.setId(avatar.getId());
        avatarDto.setFileSize(avatar.getFileSize());
        avatarDto.setMediaType(avatar.getMediaType());

        avatarDto.setAvatarUrl(
                UriComponentsBuilder.newInstance()
                        .scheme("http")
                        .host("localhost")
                        .port(port)
                        .pathSegment(AvatarController.BASE_PATH, avatar.getId().toString(), "from-db")
                        .toUriString()
        );

        //avatarDto.setAvatarUrl("http://localhost:8088/avatars" + avatar.getId() + "/from-db");
        return avatarDto;
    }
}
