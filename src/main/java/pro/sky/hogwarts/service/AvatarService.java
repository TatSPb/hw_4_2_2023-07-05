package pro.sky.hogwarts.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.hogwarts.dto.AvatarDto;
import pro.sky.hogwarts.entity.Avatar;
import pro.sky.hogwarts.entity.Student;
import pro.sky.hogwarts.exception.AvatarNotFoundException;
import pro.sky.hogwarts.exception.AvatarProcessingException;
import pro.sky.hogwarts.mapper.AvatarMapper;
import pro.sky.hogwarts.repository.AvatarRepository;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final Path pathToAvatarDir;
    private final AvatarMapper avatarMapper;

    public AvatarService(AvatarRepository avatarRepository,
                         @Value("${path.to.avatar.dir}") String pathToAvatarDir,
                         AvatarMapper avatarMapper) {
        this.avatarRepository = avatarRepository;
        this.pathToAvatarDir = Path.of(pathToAvatarDir);
        this.avatarMapper = avatarMapper;
    }

        public Avatar create(Student student, MultipartFile multipartFile) {
        try {
            String contentType = multipartFile.getContentType();
            String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
            byte[] data = multipartFile.getBytes();
            String fileName = UUID.randomUUID() + "." + extension;
            Path pathToAvatar = pathToAvatarDir.resolve(fileName);
            writeToFile(pathToAvatar, data);

            Avatar avatar = avatarRepository.findByStudent_Id(student.getId())
                    .orElse(new Avatar());

            if (avatar.getFilePath() != null) {
                Files.delete(Path.of(avatar.getFilePath()));
            }
            avatar.setMediaType(contentType);
            avatar.setFileSize(data.length);
            avatar.setData(data);
            avatar.setStudent(student);
            avatar.setFilePath(pathToAvatar.toString());
            return avatarRepository.save(avatar);
        } catch (IOException e) {
            throw new AvatarProcessingException();
        }
    }

    private void writeToFile(Path path, byte[] data) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path.toFile())) {
            fileOutputStream.write((data));
        } catch (IOException e) {
            throw new AvatarProcessingException();
        }
    }

    public Pair<byte[], String> getFromDb(long id) {
        Avatar avatar = avatarRepository.findById(id)
                .orElseThrow(() -> new AvatarNotFoundException(id));
        return Pair.of(avatar.getData(), avatar.getMediaType());
    }

    public Pair<byte[], String> getFromFs(long id) {
        Avatar avatar = avatarRepository.findById(id)
                .orElseThrow(() -> new AvatarNotFoundException(id));
        return Pair.of(read(Path.of(avatar.getFilePath())), avatar.getMediaType());
    }

    private byte[] read(Path path) {
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new AvatarProcessingException();
        }
    }

    public List<AvatarDto> getPage(int page, int size) {
        return avatarRepository.findAll(PageRequest.of(page, size)).stream()
                .map(avatarMapper::toDto)
                .collect(Collectors.toList());
    }
}


