package at.fhj.swd14.pse.news;

import at.fhj.swd14.pse.user.UserDto;

import java.io.Serializable;

public class NewsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String message;
    private UserDto author;

}
