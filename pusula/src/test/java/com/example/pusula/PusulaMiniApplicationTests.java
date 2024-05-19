package com.example.pusula;

import com.example.pusula.DTO.CommentDTO;
import com.example.pusula.Service.CommentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PusulaMiniApplicationTests {


	private CommentService commentService;

	@Autowired
	public PusulaMiniApplicationTests(CommentService commentService) {
		this.commentService = commentService;
	}

	@Test
	@Transactional
	public void testSaveComment() {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setBody("This is a test comment");
		commentDTO.setUserId(1); // Assume user with ID 1 exists
		commentDTO.setArticleId(1); // Assume article with ID 1 exists
		commentDTO.setVisible(true);

		commentService.saveComment(commentDTO);


		// Additional assertions to verify the comment was saved can be added here
	}
}
