package at.fhj.swd14.pse.comment;

import at.fhj.swd14.pse.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {
    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Mock
    private CommentRepository commentRepository;

    private List<Comment> comments;

    @Before
    public void setup() {
        User user = new User();
        user.setId(1L);
        user.setMail("mail");

        comments = new ArrayList<>();
        Comment comment = new Comment(1L);
        comment.setText("test-comment");
        // TODO: check equals errors
        // comment.setAuthor(user);
        // comment.setParentMessage(new Message(1L));
        comments.add(comment);
    }

    @Test
    public void testFind() {
        Mockito.when(commentRepository.find(1L)).thenReturn(comments.get(0));
        CommentDto c = commentServiceImpl.find(1);

        CommentDtoTester.assertEquals(CommentConverter.convert(comments.get(0)), c);
    }

}
