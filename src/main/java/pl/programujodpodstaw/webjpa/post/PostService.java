package pl.programujodpodstaw.webjpa.post;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.programujodpodstaw.webjpa.user.User;
import pl.programujodpodstaw.webjpa.user.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public List<PostDto> getAllPosts() {
        Iterable<Post> posts = postRepository.findAll();
        return StreamSupport.stream(posts.spliterator(), false)
                .map(this::mapToPostDto)
                .collect(Collectors.toList());
    }

    public Optional<PostDto> getPost(Integer id) {
        return postRepository.findById(id)
                .map(this::mapToPostDto);

    }

    public PostDto addPost(PostRequestDto postRequestDto) {
        Post post = new Post();

        User user = userRepository.findById(postRequestDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        post.setBody(postRequestDto.getBodyPost());
        post.setUser(user);

        return mapToPostDto(postRepository.save(post));
    }

    public PostDto updatePost(Integer id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        User user = userRepository.findById(postRequestDto.getUserId())
                .orElseThrow(EntityNotFoundException::new);

        post.setBody(postRequestDto.getBodyPost());
        post.setUser(user);

        return mapToPostDto(postRepository.save(post));

    }

    public PostDto partiallyPost(Integer id, PostRequestDto postRequestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (postRequestDto.getBodyPost() != null) post.setBody(postRequestDto.getBodyPost());
        if (postRequestDto.getUserId() != null) {
            User user = userRepository.findById(postRequestDto.getUserId())
                    .orElseThrow(EntityNotFoundException::new);
            post.setUser(user);
        }
        return mapToPostDto(postRepository.save(post));
    }





    public void deletePost(Integer id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException();
        } else {
            postRepository.deleteById(id);
        }
    }
    private PostDto mapToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setBody(post.getBody());
        postDto.setLogin(post.getUser().getLogin());
        postDto.setDisplayName(post.getUser().getDisplayName());

        return postDto;
    }
}
