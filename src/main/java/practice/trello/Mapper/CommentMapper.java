package practice.trello.Mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import practice.trello.DTO.CommentDTO;
import practice.trello.Entity.Comment;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "task", target = "task")
    CommentDTO toDTO(Comment comment);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "task", target = "task")
    Comment toEntity(CommentDTO commentDTO);

    List<CommentDTO> toDTOList(List<Comment> commentList);
}
